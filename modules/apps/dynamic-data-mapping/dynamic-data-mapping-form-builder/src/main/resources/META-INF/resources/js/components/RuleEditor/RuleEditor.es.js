import '../Calculator/Calculator.es';
import '../Page/PageRenderer.es';
import 'clay-alert';
import 'clay-button';
import 'clay-modal';

import Component from 'metal-component';
import Soy from 'metal-soy';
import templates from './RuleEditor.soy.js';
import {Config} from 'metal-state';
import {getFieldProperty} from '../LayoutProvider/util/fields.es';
import {makeFetch} from '../../util/fetch.es';
import {maxPageIndex, pageOptions} from '../../util/pageSupport.es';
import {PagesVisitor} from '../../util/visitors.es';

const fieldOptionStructure = Config.shapeOf(
	{
		dataType: Config.string(),
		name: Config.string(),
		options: Config.arrayOf(
			Config.shapeOf(
				{
					label: Config.string(),
					name: Config.string(),
					value: Config.string()
				}
			)
		),
		type: Config.string(),
		value: Config.string()
	}
);

/**
 * RuleEditor.
 * @extends Component
 */

class RuleEditor extends Component {
	static STATE = {
		actions: Config.arrayOf(
			Config.shapeOf(
				{
					action: Config.string(),
					calculatorFields: Config.arrayOf(fieldOptionStructure).value([]),
					expression: Config.string(),
					hasRequiredInputs: Config.bool(),
					inputs: Config.object(),
					label: Config.string(),
					outputs: Config.object(),
					target: Config.string()
				}
			)
		).internal().setter('_setActions').value([]),

		actionTypes: Config.arrayOf(
			Config.shapeOf(
				{
					label: Config.string(),
					value: Config.string()
				}
			)
		).internal().value(
			[
				{
					label: Liferay.Language.get('show'),
					value: 'show'
				},
				{
					label: Liferay.Language.get('enable'),
					value: 'enable'
				},
				{
					label: Liferay.Language.get('require'),
					value: 'require'
				},
				{
					label: Liferay.Language.get('autofill'),
					value: 'auto-fill'
				},
				{
					label: Liferay.Language.get('calculate'),
					value: 'calculate'
				},
				{
					label: Liferay.Language.get('jump-to-page'),
					value: 'jump-to-page'
				}
			]
		),

		/**
		 * Used for tracking which action we are currently focused on
		 * when trying to delete an action.
		 * @default 0
		 * @instance
		 * @memberof RuleEditor
		 * @type {Number}
		 */
		activeActionIndex: Config.number().value(-1),

		/**
		 * Used for tracking which condition we are currently focused on
		 * when trying to delete a condition.
		 * @default 0
		 * @instance
		 * @memberof RuleEditor
		 * @type {Number}
		 */

		activeConditionIndex: Config.number().value(-1),

		calculatorFunctions: Config.arrayOf(
			Config.shapeOf(
				{
					label: Config.string(),
					tooltip: Config.string(),
					value: Config.string()
				}
			)
		).internal().value([]),

		calculatorResultOptions: Config.arrayOf(fieldOptionStructure).internal().valueFn('_calculatorResultOptionsValueFn'),

		/**
		 * @default 0
		 * @instance
		 * @memberof RuleEditor
		 * @type {?array}
		 */

		conditions: Config.arrayOf(
			Config.shapeOf(
				{
					operands: Config.arrayOf(
						Config.shapeOf(
							{
								dataType: Config.string(),
								label: Config.string(),
								repeatable: Config.bool(),
								type: Config.string(),
								value: Config.string()
							}
						)
					),
					operator: Config.string()
				}
			)
		).internal().setter('_setConditions').value([]),

		dataProvider: Config.arrayOf(
			Config.shapeOf(
				{
					id: Config.string(),
					name: Config.string(),
					uuid: Config.string()
				}
			)
		).internal(),

		dataProviderInstanceParameterSettingsURL: Config.string().required(),

		dataProviderInstancesURL: Config.string().required(),

		deletedFields: Config.arrayOf(Config.string()).value([]),

		fieldOptions: Config.arrayOf(
			Config.shapeOf(
				{
					dataType: Config.string(),
					name: Config.string(),
					options: Config.arrayOf(
						Config.shapeOf(
							{
								label: Config.string(),
								name: Config.string(),
								value: Config.string()
							}
						)
					),
					type: Config.string(),
					value: Config.string()
				}
			)
		).internal().valueFn('_fieldOptionsValueFn'),

		fixedOptions: Config.arrayOf(
			fieldOptionStructure
		).value(
			[
				{
					dataType: 'user',
					label: Liferay.Language.get('user'),
					name: 'user',
					value: 'user'
				}
			]
		),

		functionsMetadata: Config.shapeOf(
			{
				number: Config.arrayOf(
					Config.shapeOf(
						{
							label: Config.string(),
							name: Config.string(),
							parameterTypes: Config.array(),
							returnType: Config.string()
						}
					)
				),
				text: Config.arrayOf(
					Config.shapeOf(
						{
							label: Config.string(),
							name: Config.string(),
							parameterTypes: Config.array(),
							returnType: Config.string()
						}
					)
				),
				user: Config.arrayOf(
					Config.shapeOf(
						{
							label: Config.string(),
							name: Config.string(),
							parameterTypes: Config.array(),
							returnType: Config.string()
						}
					)
				)
			}
		),

		functionsURL: Config.string(),

		invalidRule: Config.bool().value(true),

		loadingDataProviderOptions: Config.bool(),

		logicalOperator: Config.string().internal().value('or'),

		pageOptions: Config.arrayOf(
			Config.shapeOf(
				{
					dataType: Config.string(),
					name: Config.string(),
					options: Config.arrayOf(
						Config.shapeOf(
							{
								label: Config.string(),
								name: Config.string(),
								value: Config.string()
							}
						)
					),
					type: Config.string(),
					value: Config.string()
				}
			)
		).internal().value([]),

		pages: Config.array().required(),

		readOnly: Config.bool().value(false),

		roles: Config.arrayOf(
			Config.shapeOf(
				{
					id: Config.string(),
					name: Config.string()
				}
			)
		).valueFn('_rolesValueFn'),

		/**
		 * @default 0
		 * @instance
		 * @memberof RuleEditor
		 * @type {?array}
		 */

		rule: Config.shapeOf(
			{
				actions: Config.arrayOf(
					Config.shapeOf(
						{
							action: Config.string(),
							calculatorFields: Config.arrayOf(fieldOptionStructure).value([]),
							ddmDataProviderInstanceUUID: Config.string(),
							expression: Config.string(),
							inputs: Config.object(),
							label: Config.string(),
							outputs: Config.object(),
							target: Config.string()
						}
					)
				),
				conditions: Config.arrayOf(
					Config.shapeOf(
						{
							operands: Config.arrayOf(
								Config.shapeOf(
									{
										label: Config.string(),
										repeatable: Config.bool(),
										type: Config.string(),
										value: Config.string()
									}
								)
							),
							operator: Config.string()
						}
					)
				),
				['logical-operator']: Config.string()
			}
		),

		ruleEditedIndex: Config.number(),

		secondOperandList: Config.arrayOf(
			Config.shapeOf(
				{
					name: Config.string(),
					value: Config.string()
				}
			)
		).value(
			[
				{
					value: Liferay.Language.get('value')
				},
				{
					name: 'field',
					value: Liferay.Language.get('other-field')
				}
			]
		),

		/**
		 * @default undefined
		 * @instance
		 * @memberof RuleEditor
		 * @type {!string}
		 */

		spritemap: Config.string().required()
	}

	convertAutoFillDataToArray(action, type) {
		const data = action[type];
		const originalData = action[`${type}Data`];

		return Object.keys(data).map(
			(key, index) => {
				const {label, name, required, type} = originalData[index];

				const fieldsTypes = this.getTypesByFieldType(type);

				const fieldOptions = this.getFieldsByTypes(this.fieldOptions, fieldsTypes);

				return {
					fieldOptions,
					label,
					name,
					required,
					type,
					value: data[key]
				};
			}
		);
	}

	created() {
		if (this.rule) {
			this._prepareRuleEditor();
		}

		this._fetchFunctionsURL();
		this._setDataProviderTarget();
		this._setActionsInputsOutputs();
	}

	disposed() {
		this.setState(
			{
				actions: [],
				conditions: [],
				logicalOperator: ''
			}
		);
	}

	formatDataProviderParameter(actionParameters, parameters) {
		return parameters.reduce(
			(result, {name, value}, index) => ({
				...result,
				[name]: Object.keys(actionParameters).indexOf(name) !== -1 ? actionParameters[name] : value
			}),
			{}
		);
	}

	getDataProviderOptions(id, index) {
		let promise;

		if (id) {
			promise = this._fetchDataProviderParameters(id, index)
				.then(
					({inputs, outputs}) => {
						let actions = this.actions;

						if (!this.isDisposed()) {
							actions = actions.map(
								(action, currentIndex) => {
									return index == currentIndex ? ({
										...action,
										ddmDataProviderInstanceUUID: this.dataProvider.find(
											data => {
												return data.id == id;
											}
										).uuid,
										hasRequiredInputs: inputs.some(
											input => input.required
										),
										inputs: this.formatDataProviderParameter(action.inputs, inputs),
										inputsData: inputs,
										outputs: this.formatDataProviderParameter(action.outputs, outputs),
										outputsData: outputs
									}) : action;
								}
							);
						}

						return actions[index];
					}
				).catch(
					error => {
						throw new Error(error);
					}
				);
		}
		else {
			promise = Promise.resolve(this.actions[index]);
		}

		return promise;
	}

	getFieldOptions(fieldName) {
		let options = [];
		const visitor = new PagesVisitor(this.pages);

		visitor.mapFields(
			field => {
				if (field.fieldName === fieldName) {
					options = field.options;
				}
			}
		);

		return options;
	}

	getFieldsByTypes(fields, types) {
		return fields.filter(field => types.some(fieldType => field.type == fieldType));
	}

	getTypesByFieldType(fieldType) {
		let list = [];

		if (fieldType == 'list') {
			list = ['select', 'checkbox_multiple', 'radio'];
		}
		else if (fieldType == 'text') {
			list = ['select', 'checkbox_multiple', 'radio', 'text', 'numeric'];
		}
		else if (fieldType == 'number') {
			list = ['numeric'];
		}

		return list;
	}

	isValueOperand({type}) {
		return type !== 'field' && type !== 'user';
	}

	populateActionTargetValue(id, index) {
		const {actions, pageOptions} = this;

		const previousTarget = actions[index].target;

		actions[index].target = id;
		actions[index].label = id;

		if (actions[index].action == 'jump-to-page') {
			const selectedOption = pageOptions.find(
				option => {
					return option.value == id;
				}
			);

			actions[index].label = selectedOption.label;
		}

		if (id === undefined) {
			actions[index].target = '';
		}
		else if (id === '') {
			actions[index].inputs = {};
			actions[index].outputs = {};
			actions[index].hasRequiredInputs = false;
		}
		else if (previousTarget !== id && actions[index].action == 'calculate') {
			actions[index].calculatorFields = this._updateCalculatorFields(this.actions[index], id);
		}

		this.setState(
			{
				actions
			}
		);
	}

	populateDataProviderOptions(id, index) {
		const {actions} = this;

		actions[index].target = id;
		actions[index].label = id;

		this.getDataProviderOptions(id, index).then(
			actionData => {
				if (!this.isDisposed()) {
					this.setState(
						{
							actions: actions.map(
								(action, currentIndex) => {
									return index == currentIndex ? actionData : action;
								}
							)
						}
					);
				}
			}
		).catch(
			error => {
				throw new Error(error);
			}
		);
	}

	prepareStateForRender(state) {
		const {pages} = this;
		const actions = state.loadingDataProviderOptions ? [] : state.actions.map(
			action => ({
				...action,
				inputs: action.inputs ? this.convertAutoFillDataToArray(action, 'inputs') : [],
				outputs: action.outputs ? this.convertAutoFillDataToArray(action, 'outputs') : []
			})
		);

		const conditions = state.conditions.map(
			condition => {
				const fieldName = condition.operands[0].value;
				let firstOperandOptions = [];
				let operators = [];

				if (fieldName) {
					const {dataType} = this._getFieldTypeByFieldName(fieldName);

					operators = this._getOperatorsByFieldType(dataType);

					firstOperandOptions = this.getFieldOptions(fieldName);
				}

				return {
					...condition,
					binaryOperator: this._isBinary(condition.operator),
					firstOperandOptions,
					operands: condition.operands.map(
						(operand, index) => {
							if (index === 1 && this.isValueOperand(operand)) {
								operand = {
									...operand,
									dataType: getFieldProperty(pages, condition.operands[0].value, 'dataType'),
									type: getFieldProperty(pages, condition.operands[0].value, 'type')
								};
							}

							return operand;
						}
					),
					operators
				};
			}
		);

		let {actionTypes} = this;

		if (pages.length < 3) {
			actionTypes = this.actionTypes.filter(
				obj => {
					return obj.value !== 'jump-to-page';
				}
			);
		}

		return {
			...state,
			actions,
			actionTypes,
			conditions
		};
	}

	syncPages(pages) {
		const {actions} = this;
		let {conditions} = this;

		const visitor = new PagesVisitor(pages);

		conditions.forEach(
			(condition, index) => {
				let firstOperandFieldExists = false;
				const secondOperand = condition.operands[1];
				let secondOperandFieldExists = false;

				visitor.mapFields(
					({fieldName}) => {
						if (condition.operands[0].value === fieldName) {
							firstOperandFieldExists = true;
						}

						if (secondOperand && secondOperand.value === fieldName) {
							secondOperandFieldExists = true;
						}
					}
				);

				if (condition.operands[0].value === 'user') {
					firstOperandFieldExists = true;
				}

				if (!firstOperandFieldExists) {
					conditions = this._clearAllConditionFieldValues(index);
				}

				if (!secondOperandFieldExists && secondOperand && secondOperand.type == 'field') {
					conditions = this._clearSecondOperandValue(conditions, index);
				}
			}
		);

		const maxPage = maxPageIndex(conditions, pages);

		this.setState(
			{
				actions: this._syncActions(actions),
				calculatorResultOptions: this._calculatorResultOptionsValueFn(),
				conditions,
				deletedFields: this._getDeletedFields(visitor),
				fieldOptions: this._fieldOptionsValueFn(),
				pageOptions: pageOptions(pages, maxPage),
				roles: this._rolesValueFn()
			}
		);
	}

	syncVisible(visible) {
		const addButton = document.querySelector('#addFieldButton');

		super.syncVisible(visible);

		if (addButton && visible) {
			addButton.classList.add('hide');
		}
	}

	willUpdate() {
		this.setState(
			{
				invalidRule: !this._validateConditionsFilling() || !this._validateActionsFilling()
			}
		);
	}

	_calculatorResultOptionsValueFn() {
		const {pages} = this;
		const fields = [];
		const visitor = new PagesVisitor(pages);

		visitor.mapFields(
			field => {
				if (field.type == 'numeric') {
					fields.push(
						{
							...field,
							options: field.options ? field.options : [],
							value: field.fieldName
						}
					);
				}
			}
		);

		return fields;
	}

	_clearAction(index) {
		const {actions} = this;

		return actions.map(
			(action, currentIndex) => {
				return currentIndex === index ? {
					action: '',
					calculatorFields: [],
					expression: '',
					inputs: {},
					label: '',
					outputs: {},
					target: ''
				} : action;
			}
		);
	}

	_clearAllConditionFieldValues(index) {
		const {secondOperandSelectedList} = this;
		let {conditions} = this;

		conditions = this._clearFirstOperandValue(conditions, index);
		conditions = this._clearOperatorValue(conditions, index);
		conditions = this._clearSecondOperandValue(conditions, index);

		this.setState(
			{
				conditions,
				secondOperandSelectedList
			}
		);

		return conditions;
	}

	_clearFirstOperandValue(conditions, index) {
		if (conditions[index] && conditions[index].operands[0]) {
			conditions[index].operands[0].type = '';
			conditions[index].operands[0].value = '';
		}

		return conditions;
	}

	_clearOperatorValue(conditions, index) {
		if (conditions[index]) {
			conditions[index].operator = '';
		}

		return conditions;
	}

	_clearSecondOperandValue(conditions, index) {
		if (conditions[index] && conditions[index].operands[1]) {
			conditions[index].operands = [conditions[index].operands[0]];
		}

		return conditions;
	}

	_clearSelectedSecondOperand(secondOperandSelectedList, index) {
		return secondOperandSelectedList;
	}

	_fetchDataProviderParameters(id) {
		const {dataProviderInstanceParameterSettingsURL} = this;

		return makeFetch(
			{
				method: 'GET',
				url: `${dataProviderInstanceParameterSettingsURL}?ddmDataProviderInstanceId=${id}`
			}
		).catch(
			error => {
				throw new Error(error);
			}
		);
	}

	_fetchFunctionsURL() {
		const {functionsURL} = this;

		makeFetch(
			{
				method: 'GET',
				url: functionsURL
			}
		).then(
			responseData => {
				if (!this.isDisposed()) {
					this.setState(
						{
							calculatorFunctions: responseData
						}
					);
				}
			}
		).catch(
			error => {
				throw new Error(error);
			}
		);
	}

	_fieldOptionsValueFn() {
		const {pages} = this;
		const fields = [];
		const visitor = new PagesVisitor(pages);

		visitor.mapFields(
			field => {
				fields.push(
					{
						...field,
						options: field.options ? field.options : [],
						value: field.fieldName
					}
				);
			}
		);

		return fields;
	}

	_getDeletedFields(visitor) {
		const existentFields = [];
		const {fieldOptions} = this;
		let deletedFields = [];

		fieldOptions.forEach(
			field => {
				visitor.mapFields(
					({fieldName}) => {
						if (field.fieldName === fieldName) {
							existentFields.push(fieldName);
						}
					}
				);
			}
		);

		const oldFields = fieldOptions.map(field => field.fieldName);

		deletedFields = oldFields.filter(
			field => {
				return existentFields.indexOf(field) > -1 ? false : field;
			}
		);

		return deletedFields;
	}

	_getFieldLabel(fieldName) {
		const pages = this.pages;

		let fieldLabel;

		if (pages) {
			for (let page = 0; page < pages.length; page++) {
				const rows = pages[page].rows;

				for (let row = 0; row < rows.length; row++) {
					const cols = rows[row].columns;

					for (let col = 0; col < cols.length; col++) {
						const fields = cols[col].fields;

						for (let field = 0; field < fields.length; field++) {
							if (pages[page].rows[row].columns[col].fields[field].fieldName === fieldName) {
								fieldLabel = pages[page].rows[row].columns[col].fields[field].label;
								break;
							}
						}
					}
				}
			}
		}

		return fieldLabel;
	}

	_getFieldTypeByFieldName(fieldName) {
		let dataType = '';
		let repeatable = false;
		let type = '';

		if (fieldName === 'user') {
			dataType = 'user';
		}
		else {
			const selectedField = this.fieldOptions.find(
				field => field.value === fieldName
			);

			if (selectedField) {
				dataType = selectedField.dataType;
				repeatable = selectedField.repeatable;
				type = selectedField.type;
			}
		}

		return {dataType, repeatable, type};
	}

	_getIndex(fieldInstance, fieldClass) {
		const firstOperand = fieldInstance.element.closest(fieldClass);

		return firstOperand.getAttribute(`${fieldClass.substring(1)}-index`);
	}

	_getOperatorsByFieldType(fieldType) {
		if (fieldType === 'integer' || fieldType === 'double') {
			fieldType = 'number';
		}

		if (!this.functionsMetadata.hasOwnProperty(fieldType)) {
			fieldType = 'text';
		}

		return this.functionsMetadata[fieldType].map(
			metadata => {
				return {
					...metadata,
					value: metadata.name
				};
			}
		);
	}

	_handleActionAdded() {
		const {actions} = this;
		const newAction = {action: '', calculatorFields: [], expression: '', inputs: {}, label: '', outputs: {}, target: ''};

		if (actions.length == 0) {
			actions.push(newAction);
		}

		actions.push(newAction);

		this.setState(
			{
				actions,
				invalidRule: true
			}
		);
	}

	_handleActionSelection(event) {
		const {fieldInstance, value} = event;
		const index = parseInt(this._getIndex(fieldInstance, '.action-type'), 10);

		const {actions, conditions} = this;

		let newActions = [...actions];

		if (value && value.length > 0 && value[0]) {
			const fieldName = value[0];

			if (actions.length > 0) {
				const previousAction = actions[index].action;

				if (fieldName !== previousAction) {
					newActions = newActions.map(
						(action, currentIndex) => {
							return currentIndex === index ? {
								...action,
								action: fieldName,
								calculatorFields: [],
								label: '',
								source: conditions[0].operands[0].source,
								target: ''
							} : action;
						}
					);
				}
			}
			else {
				newActions.push({action: fieldName});
			}
		}
		else {
			newActions = this._clearAction(index);
		}

		this.setState(
			{
				actions: newActions
			}
		);
	}

	_handleCancelRule(event) {
		this.emit(
			'ruleCancel',
			{}
		);
	}

	_handleConditionAdded() {
		const {conditions} = this;

		conditions.push(
			{
				operands: [
					{
						type: '',
						value: ''
					}
				],
				operator: ''
			}
		);

		this.setState(
			{
				conditions
			}
		);
	}

	_handleDataProviderInputEdited(event) {
		const {fieldInstance, value} = event;
		const {actions} = this;
		const actionIndex = this._getIndex(fieldInstance, '.action');
		const inputIndex = this._getIndex(fieldInstance, '.container-input-field');

		const editedInput = Object.keys(actions[actionIndex].inputs)[inputIndex];

		actions[actionIndex].inputs[editedInput] = value[0];

		this.setState(
			{
				actions
			}
		);
	}

	_handleDataProviderOutputEdited(event) {
		const {fieldInstance, value} = event;
		const actionIndex = this._getIndex(fieldInstance, '.action');
		const outputIndex = this._getIndex(fieldInstance, '.container-output-field');
		const {actions} = this;

		const editedOutput = Object.keys(actions[actionIndex].outputs)[outputIndex];

		actions[actionIndex].outputs[editedOutput] = value[0];

		this.setState(
			{
				actions
			}
		);
	}

	_handleDeleteAction(event) {
		const {currentTarget} = event;
		const index = currentTarget.getAttribute('data-index');

		this.refs.confirmationModalAction.show();
		this.setState(
			{
				activeActionIndex: parseInt(index, 10)
			}
		);
	}

	_handleDeleteCondition(event) {
		const {currentTarget} = event;
		const index = currentTarget.getAttribute('data-index');

		this.refs.confirmationModalCondition.show();
		this.setState(
			{
				activeConditionIndex: parseInt(index, 10)
			}
		);
	}

	_handleEditExpression({expression, index}) {
		const {actions} = this;

		actions[index].expression = expression;
		this.setState({actions});
	}

	_handleFirstOperandFieldEdited(event) {
		const {fieldInstance, value} = event;
		const index = this._getIndex(fieldInstance, '.condition-if');
		const {actions, pages} = this;
		let {conditions} = this;

		if (value && value.length > 0 && value[0]) {
			const fieldName = value[0];
			const {dataType, repeatable} = this._getFieldTypeByFieldName(fieldName);

			const firstOperand = {
				label: this._getFieldLabel(fieldName),
				repeatable,
				type: dataType == 'user' ? 'user' : 'field',
				value: fieldName
			};

			if (conditions.length === 0) {
				const operands = [firstOperand];

				conditions.push({operands});
			}
			else {
				if (fieldName !== conditions[index].operands[0].value) {
					conditions[index].operator = '';
					this._clearSecondOperandValue(conditions, index);
				}

				conditions[index].operands[0] = firstOperand;
			}
		}
		else {
			conditions = this._clearAllConditionFieldValues(index);
		}

		let maxPageIndex;

		const visitor = new PagesVisitor(pages);

		if (conditions[index].operands[0].value != '') {
			visitor.mapFields(
				(field, fieldIndex, columnIndex, rowIndex, pageIndex) => {
					if (field.fieldName === conditions[index].operands[0].value) {
						maxPageIndex = pageIndex;

						conditions[index].operands[0].source = pageIndex;
					}
				}
			);
		}

		if (actions && actions[0].action != '') {
			actions.map(
				action => {
					if (action.action == 'jump-to-page') {
						action.source = conditions[index].operands[0].source;
					}

					return {
						action
					};
				}
			);
		}

		this.setState(
			{
				actions,
				conditions,
				pageOptions: pageOptions(pages, maxPageIndex)
			}
		);
	}

	_handleLogicalOperationChange(event) {
		const {target} = event;
		const {value} = target.dataset;

		if (value !== this.logicalOperator) {
			this.setState(
				{
					logicalOperator: value
				}
			);
		}
	}

	_handleModalButtonClicked(event) {
		event.stopPropagation();

		if (!event.target.classList.contains('close-modal')) {
			const activeActionIndex = this.activeActionIndex;
			const activeConditionIndex = this.activeConditionIndex;

			const {actions, conditions} = this;

			if (activeConditionIndex > -1) {
				conditions.splice(activeConditionIndex, 1);
			}

			if (activeActionIndex > -1) {
				actions.splice(activeActionIndex, 1);
			}

			if (this.refs.confirmationModalAction.visible) {
				this.refs.confirmationModalAction.emit('hide');
			}

			if (this.refs.confirmationModalCondition.visible) {
				this.refs.confirmationModalCondition.emit('hide');
			}

			this.setState(
				{
					actions,
					activeActionIndex: -1,
					activeConditionIndex: -1,
					conditions
				}
			);

		}
	}

	_handleOperatorEdited(event) {
		const {fieldInstance, value} = event;
		let {conditions} = this;
		let operatorValue = '';

		if (value && value.length > 0 && value[0]) {
			operatorValue = value[0];
		}

		const index = this._getIndex(fieldInstance, '.condition-operator');

		if (!operatorValue || !this._isBinary(operatorValue)) {
			conditions = this._clearSecondOperandValue(conditions, index);

			conditions[index].operator = operatorValue;
		}
		else {
			conditions[index].operator = operatorValue;
		}

		this.setState(
			{
				conditions
			}
		);
	}

	_handleRuleAdded(event) {
		const actions = this._removeActionInternalProperties();
		const conditions = this._removeConditionInternalProperties();
		const {ruleEditedIndex} = this;

		this.emit(
			'ruleAdded',
			{
				actions,
				conditions,
				['logical-operator']: this.logicalOperator,
				ruleEditedIndex
			}
		);
	}

	_handleSecondOperandFieldEdited(event) {
		const {conditions} = this;
		const {fieldInstance, value} = event;
		let fieldValue = '';

		if (value && typeof (value) == 'object' && value[0]) {
			fieldValue = value[0];
		}
		else if (value && typeof (value) == 'string') {
			fieldValue = value;
		}

		let index;

		if (fieldInstance.element.closest('.condition-type-value')) {
			index = this._getIndex(fieldInstance, '.condition-type-value');
		}

		let secondOperand = conditions[index].operands[1];

		if (!secondOperand) {
			secondOperand = {
				dataType: fieldInstance.dataType,
				type: fieldInstance.type
			};
		}

		let userType = '';

		if (conditions[index].operands[0].type === 'user') {
			userType = conditions[index].operands[0].type;
		}

		conditions[index].operands[1] = {
			...secondOperand,
			dataType: fieldInstance.dataType,
			label: fieldValue,
			type: userType ? userType : fieldInstance.type,
			value: fieldValue
		};

		this.setState(
			{
				conditions
			}
		);
	}

	_handleSecondOperandTypeEdited(event) {
		let {conditions} = this;
		const {fieldInstance, value} = event;
		const index = this._getIndex(fieldInstance, '.condition-type');
		const {operands} = conditions[index];
		const secondOperand = operands[1];

		let secondOperandType = 'field';
		let valueType = 'field';
		if (value[0] == 'value') {
			valueType = 'string';
			secondOperandType = this._getFieldTypeByFieldName(operands[0].value).dataType;
		}

		if (secondOperand && ((secondOperand.type === secondOperandType)) && value[0] !== '') {
			return;
		}

		if ((value[0] == '')) {
			conditions = this._clearSecondOperandValue(conditions, index);
		}
		else if (secondOperand && secondOperand.dataType != valueType) {
			conditions[index].operands[1].type = '';
			conditions[index].operands[1].value = '';
		}

		if (secondOperand) {
			secondOperand.type = secondOperandType;
		}
		else if ((value[0] !== '')) {
			conditions[index].operands.push(
				{
					type: secondOperandType,
					value: ''
				}
			);
		}

		this.setState(
			{
				conditions
			}
		);
	}

	_handleSecondOperandValueEdited(event) {
		const {conditions} = this;
		const {fieldInstance, value} = event;
		const index = this._getIndex(fieldInstance, '.condition-type-value');
		const secondOperandValue = Array.isArray(value) ? value[0] : value;

		this.setState(
			{
				conditions: conditions.map(
					(condition, conditionIndex) => {
						const operands = [...condition.operands];

						if (index == conditionIndex) {
							operands[1] = {
								...operands[1],
								value: secondOperandValue
							};
						}

						return {
							...condition,
							operands
						};
					}
				)
			}
		);
	}

	_handleTargetSelection(event) {
		const {fieldInstance, value} = event;
		const {actions} = this;
		const id = value[0];
		const index = this._getIndex(fieldInstance, '.target-action');

		const previousTarget = actions[index].target;

		if (previousTarget !== id && actions[index].action == 'auto-fill') {
			this.populateDataProviderOptions(id, index);
		}
		else {
			this.populateActionTargetValue(id, index);
		}
	}

	_isBinary(value) {
		return (
			value === 'equals-to' ||
			value === 'not-equals-to' ||
			value === 'contains' ||
			value === 'not-contains' ||
			value === 'belongs-to' ||
			value === 'greater-than' ||
			value === 'greater-than-equals' ||
			value === 'less-than' ||
			value === 'less-than-equals'
		);
	}

	_isFieldAction(fieldName) {
		return (fieldName == 'enable' || fieldName == 'show' || fieldName == 'require');
	}

	_prepareAutofillOutputs(action) {
		if (Array.isArray(action.outputs)) {
			action.outputs.forEach(
				output => {
					delete output.fieldOptions;
					delete output.name;
					delete output.type;
				}
			);
		}

		return action.outputs;
	}

	_prepareRuleEditor() {
		const {rule} = this;

		const newRule = rule;

		let newActions = rule.actions.map(
			action => {
				const newAction = {...action};

				if (action.action == 'jump-to-page') {
					newAction.target = (parseInt(action.target, 10) + 1).toString();
				}

				return newAction;
			}
		);

		newActions = this._syncActions(newActions);

		newRule.actions = newActions;

		this.setState(
			{
				actions: newActions,
				conditions: rule.conditions,
				logicalOperator: rule['logical-operator'],
				rule: newRule
			}
		);
	}

	_removeActionInternalProperties() {
		const {actions} = this;

		return actions.map(
			action => {
				const {
					action: actionType,
					ddmDataProviderInstanceUUID,
					expression,
					inputs,
					label,
					outputs,
					source,
					target
				} = action;

				let newAction = {
					action: actionType,
					label,
					target
				};

				if (actionType == 'auto-fill') {
					newAction = {
						...newAction,
						ddmDataProviderInstanceUUID,
						inputs,
						outputs
					};
				}
				else if (actionType == 'calculate') {
					newAction = {
						...newAction,
						expression
					};
				}
				else if (actionType == 'jump-to-page') {
					newAction = {
						...newAction,
						source: `${source}`,
						target: `${parseInt(target, 10) - 1}`
					};
				}

				return newAction;
			}
		);
	}

	_removeConditionInternalProperties() {
		const {conditions} = this;

		conditions.forEach(
			condition => {
				if (condition.operands[0].type == 'user') {
					condition.operands[0].label = condition.operands[0].value;
					condition.operands[1].type = 'list';
				}

				if (condition.operands[1]) {
					condition.operands[1].label = condition.operands[1].value;
				}
			}
		);

		return conditions;
	}

	_rolesValueFn() {
		const {roles} = this;

		return roles.map(
			role => {
				return {
					...role,
					value: role.label
				};
			}
		);
	}

	_setActions(actions) {
		if (actions.length == 0) {
			actions.push(
				{
					action: '',
					calculatorFields: [],
					expression: '',
					hasRequiredInputs: false,
					inputs: {},
					label: '',
					outputs: {},
					target: ''
				}
			);
		}

		return actions;
	}

	_setActionsInputsOutputs() {
		const {rule} = this;

		if (rule) {
			this.setState(
				{
					loadingDataProviderOptions: true
				}
			);

			Promise.all(
				rule.actions.map(
					(action, index) => {
						let newAction = {...action};

						if (action.ddmDataProviderInstanceUUID) {
							const {id} = this.dataProvider.find(
								dataProvider => {
									let dataProviderId;

									if (dataProvider.uuid === action.ddmDataProviderInstanceUUID) {
										dataProviderId = dataProvider.id;
									}

									return dataProviderId;
								}
							);

							newAction = this.getDataProviderOptions(id, index);
						}

						newAction.calculatorFields = this._updateCalculatorFields(newAction, newAction.target);

						return newAction;
					}
				)
			).then(
				actions => {
					this.setState(
						{
							actions,
							loadingDataProviderOptions: false
						}
					);
				}
			).catch(
				error => {
					throw new Error(error);
				}
			);
		}
	}

	_setConditions(conditions) {
		if (conditions.length === 0) {
			conditions.push(
				{
					operands: [
						{
							type: '',
							value: ''
						}
					],
					operator: ''
				}
			);
		}

		return conditions;
	}

	_setDataProviderTarget() {
		const {dataProvider, rule} = this;

		if (!rule) {
			return;
		}

		this.setState(
			{
				actions: rule.actions.map(
					action => {
						if (action.action == 'auto-fill') {
							const {id} = dataProvider.find(
								({uuid}) => uuid === action.ddmDataProviderInstanceUUID
							);

							action.target = id;
						}

						return action;
					}
				)
			}
		);
	}

	_syncActions(actions) {
		const {pages, rule} = this;

		const visitor = new PagesVisitor(pages);

		actions.forEach(
			(action, index) => {
				let targetFieldExists = false;

				visitor.mapFields(
					({fieldName}) => {
						if (action.target === fieldName) {
							targetFieldExists = true;
						}
					}
				);

				action.calculatorFields = this._updateCalculatorFields(action, action.target);

				if (
					action.action !== 'auto-fill' &&
					action.action !== 'jump-to-page' &&
					!targetFieldExists
				) {
					action.target = '';
				}
				else if (action.action == 'auto-fill') {
					action = {
						...rule.actions[index],
						calculatorFields: []
					};
				}
			}
		);

		return actions;
	}

	_updateCalculatorFields(action, id) {
		const {calculatorResultOptions} = this;

		return calculatorResultOptions.reduce(
			(prev, option) => {
				return (option.fieldName === id) ? prev : [
					...prev,
					{
						...option,
						title: option.fieldName,
						type: 'item'
					}
				];
			},
			[]
		);
	}

	_validateActionsAutoFill(autoFillActions, type) {
		return autoFillActions.every(
			action => {
				const parameterKeys = Object.keys(action[type]);

				let validation = parameterKeys.every(key => action[type][key]);

				if (type === 'inputs' && !parameterKeys.length) {
					validation = Object.keys(action.outputs).length;
				}

				return validation;
			}
		);
	}

	_validateActionsCalculateFilling(calculateActions) {
		let allFieldsFilled = true;

		calculateActions.forEach(
			({expression}) => {
				if (expression && expression.length == 0) {
					allFieldsFilled = false;
				}
			}
		);

		return allFieldsFilled;
	}

	_validateActionsFilling() {
		const {actions} = this;

		let allFieldsFilled = true;

		const autofillActions = actions.filter(
			action => {
				return action.action == 'auto-fill';
			}
		);

		const calculateActions = actions.filter(
			action => {
				return action.action == 'calculate';
			}
		);

		if (actions) {
			actions.forEach(
				currentAction => {
					const {action, target} = currentAction;

					if (action == '') {
						allFieldsFilled = false;
					}
					else if (target == '') {
						allFieldsFilled = false;
					}
				}
			);

			if (allFieldsFilled) {
				if (autofillActions && autofillActions.length > 0 && calculateActions && calculateActions.length > 0) {
					allFieldsFilled = this._validateInputOutputs(autofillActions) && this._validateActionsCalculateFilling(calculateActions);
				}
				else if (autofillActions && autofillActions.length > 0) {
					allFieldsFilled = this._validateActionsAutoFill(autofillActions, 'inputs') &&
							this._validateActionsAutoFill(autofillActions, 'outputs');
				}
				else if (calculateActions && calculateActions.length > 0) {
					allFieldsFilled = this._validateActionsCalculateFilling(calculateActions);
				}
			}
		}

		return allFieldsFilled;
	}

	_validateConditionsFilling() {
		const {conditions} = this;

		let allFieldsFilled = true;

		for (const condition of conditions) {
			const {operands, operator} = condition;

			if (operands[0].value == '') {
				allFieldsFilled = false;
				break;
			}
			else if (!operator) {
				allFieldsFilled = false;
				break;
			}
			else if (operator && this._isBinary(operator)) {
				allFieldsFilled = operands[1] && !!operands[1].value && operands[1].value != '';
				if (!allFieldsFilled) {
					break;
				}
			}
		}

		return allFieldsFilled;
	}

	_validateInputOutputs(autofillActions) {
		return this._validateActionsAutoFill(autofillActions, 'inputs') &&
			this._validateActionsAutoFill(autofillActions, 'outputs');
	}
}

Soy.register(RuleEditor, templates);

export default RuleEditor;
import {convertToSearchParams, makeFetch} from './fetch.es';
import {debounce} from 'metal-debounce';
import {PagesVisitor} from './visitors.es';

const EVALUATOR_URL = '/o/dynamic-data-mapping-form-context-provider/';

const doEvaluate = debounce((fieldName, evaluatorContext, callback) => {
	const {
		defaultLanguageId,
		editingLanguageId,
		pages,
		portletNamespace
	} = evaluatorContext;

	makeFetch({
		body: convertToSearchParams({
			languageId: editingLanguageId,
			p_auth: Liferay.authToken,
			portletNamespace,
			serializedFormContext: JSON.stringify({
				...evaluatorContext,
				groupId: themeDisplay.getScopeGroupId(),
				portletNamespace
			}),
			trigger: fieldName
		}),
		url: EVALUATOR_URL
	}).then(newPages => {
		const mergedPages = mergePages(
			defaultLanguageId,
			editingLanguageId,
			newPages,
			pages
		);

		callback(mergedPages);
	});
}, 300);

export const evaluate = (fieldName, evaluatorContext) => {
	return new Promise(resolve => {
		doEvaluate(fieldName, evaluatorContext, pages => resolve(pages));
	});
};

export const mergeFieldOptions = (field, newField) => {
	let newValue = {...newField.value};

	for (const languageId in newValue) {
		newValue = {
			...newValue,
			[languageId]: newValue[languageId].map(option => {
				const existingOption = field.value[languageId].find(
					({value}) => value === option.value
				);

				return {
					...option,
					edited: existingOption && existingOption.edited
				};
			})
		};
	}

	return newValue;
};

export const mergePages = (
	defaultLanguageId,
	editingLanguageId,
	newPages,
	sourcePages
) => {
	const visitor = new PagesVisitor(newPages);

	return visitor.mapFields(
		(field, fieldIndex, columnIndex, rowIndex, pageIndex) => {
			const sourceField =
				sourcePages[pageIndex].rows[rowIndex].columns[columnIndex]
					.fields[fieldIndex];

			let newField = {
				...sourceField,
				...field,
				defaultLanguageId,
				editingLanguageId,
				valid: field.valid !== false
			};

			if (sourceField.nestedFields && newField.nestedFields) {
				newField = {
					...newField,
					nestedFields: sourceField.nestedFields.map(nestedField => {
						return {
							...nestedField,
							...(newField.nestedFields.find(({fieldName}) => {
								return fieldName === nestedField.fieldName;
							}) || {})
						};
					})
				};
			}

			if (newField.type === 'options') {
				newField = {
					...newField,
					value: mergeFieldOptions(sourceField, newField)
				};
			}

			if (field.localizable) {
				newField = {
					...newField,
					localizedValue: {
						...sourceField.localizedValue,
						...(newField.localizedValue || {})
					}
				};
			}

			return newField;
		}
	);
};

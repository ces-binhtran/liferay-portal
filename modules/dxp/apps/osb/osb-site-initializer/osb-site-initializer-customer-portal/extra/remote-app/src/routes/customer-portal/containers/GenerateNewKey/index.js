/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {useState} from 'react';
import GenerateNewKeySkeleton from './Skeleton';
import RequiredInformation from './pages/RequiredInformation';
import SelectSubscription from './pages/SelectSubscription';
import {STEP_TYPES} from './utils/constants/stepType';

const ACTIVATION_ROOT_ROUTER = 'activation';

const GenerateNewKey = ({accountKey, productGroupName, sessionId}) => {
	const [infoSelectedKey, setInfoSelectedKey] = useState();
	const [step, setStep] = useState(STEP_TYPES.selectDescriptions);

	const urlPreviousPage = `/${accountKey}/${ACTIVATION_ROOT_ROUTER}/${productGroupName.toLowerCase()}`;

	const StepLayout = {
		[STEP_TYPES.generateKeys]: (
			<RequiredInformation
				accountKey={accountKey}
				infoSelectedKey={infoSelectedKey}
				sessionId={sessionId}
				setStep={setStep}
				urlPreviousPage={urlPreviousPage}
			/>
		),
		[STEP_TYPES.selectDescriptions]: (
			<SelectSubscription
				accountKey={accountKey}
				infoSelectedKey={infoSelectedKey}
				productGroupName={productGroupName}
				sessionId={sessionId}
				setInfoSelectedKey={setInfoSelectedKey}
				setStep={setStep}
				urlPreviousPage={urlPreviousPage}
			/>
		),
	};

	return StepLayout[step];
};

GenerateNewKey.Skeleton = GenerateNewKeySkeleton;

export default GenerateNewKey;

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.multi.factor.authentication.spi.checker.composite;

import aQute.bnd.annotation.ProviderType;

import com.liferay.multi.factor.authentication.spi.checker.MFAChecker;
import com.liferay.multi.factor.authentication.spi.checker.visitor.MFACheckerVisitor;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.util.List;
import java.util.Locale;

/**
 * @author Carlos Sierra Andrés
 */
@ProviderType
public abstract class BaseCompositeMFAChecker implements MFAChecker {

	public BaseCompositeMFAChecker(List<MFAChecker> mfaCheckers) {
		this.mfaCheckers = mfaCheckers;
	}

	@Override
	public <T> T accept(MFACheckerVisitor<T> mfaCheckerVisitor) {
		return mfaCheckerVisitor.visit(this);
	}

	@Override
	public String getLabel(Locale locale) {
		if (mfaCheckers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(mfaCheckers.size() * 2 - 1);

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}

			sb.append(mfaChecker.getLabel(locale));
		}

		return sb.toString();
	}

	public List<MFAChecker> getMfaCheckers() {
		return mfaCheckers;
	}

	@Override
	public String getName() {
		if (mfaCheckers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(mfaCheckers.size() * 2 - 1);

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}

			sb.append(mfaChecker.getName());
		}

		return sb.toString();
	}

	@Override
	public boolean isEnabled() {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.isEnabled()) {
				return false;
			}
		}

		return true;
	}

	protected List<MFAChecker> mfaCheckers;

}
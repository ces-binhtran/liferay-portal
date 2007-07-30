/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.util;

import com.liferay.util.GetterUtil;

import java.text.DateFormat;

import java.util.Date;

/**
 * <a href="ReleaseInfo.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReleaseInfo {

	static String name = "Liferay Enterprise Portal";

	static String version = "4.3.0";

	static String codeName = "Owen";

	static String build = "4392";

	static String date = "July 30, 2007";

	static String releaseInfo =
		name + " " + version + " (" + codeName + " / Build " + build + " / " +
			date + ")";

	static String serverInfo = name + " / " + version;

	public static int RELEASE_4_2_0_BUILD_NUMBER = 3500;

	public static int RELEASE_4_2_1_BUILD_NUMBER = 3501;

	public static int RELEASE_4_2_2_BUILD_NUMBER = 3502;

	public static int RELEASE_4_3_0_BUILD_NUMBER = 4300;

	public static int RELEASE_4_3_1_BUILD_NUMBER = 4301;

	public static final String getVersion() {
		return version;
	}

	public static final String getCodeName() {
		return codeName;
	}

	public static final int getBuildNumber() {
		return Integer.parseInt(build);
	}

	public static final Date getBuildDate() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

		return GetterUtil.getDate(date, df);
	}

	public static final String getReleaseInfo() {
		return releaseInfo;
	}

	public static final String getServerInfo() {
		return serverInfo;
	}

}
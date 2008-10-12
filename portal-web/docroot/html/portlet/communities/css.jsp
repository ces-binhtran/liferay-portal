<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-communities .lfr-theme-list h3 {
	background: #D3DADD;
	padding: 0.5em;
}

.portlet-communities .lfr-theme-list li {
	float: left;
	margin: 0 1.3em 1.3em 0;
	text-align: center;
}

.portlet-communities .theme-title {
	font-weight: bold;
	margin: 0;
	padding: 2px;
}

.portlet-communities .lfr-current-theme {
	background: #F0F5F7;
	border: 1px solid #828F95;
	margin-bottom: 1em;
	padding: 3px 3px 1em;
}

.portlet-communities .lfr-current-theme h3 {
	margin: 0 0 0.5em;
}

.portlet-communities .lfr-current-theme .theme-title {
	border-bottom: 1px solid #828F95;
	font-size: 1.4em;
	margin-bottom: 0.5em;
	padding-left: 0;
	padding-top: 0;
}

.portlet-communities .lfr-current-theme .theme-details {
	padding: 0 2px 0 170px;
}

.portlet-communities .theme-entry {
	height: 96px;
	text-decoration: none;
	width: 120px;
}

.portlet-communities .theme-entry, .portlet-communities .lfr-current-theme .theme-screenshot {
	border: 1px solid #AEB8BC;
	color: #fff;
	display: block;
	padding: 1px;
}

.portlet-communities .lfr-current-theme .theme-screenshot {
	float: left;
	height: 120px;
	margin: 0 0.5em;
	width: 150px;
}

.portlet-communities .theme-entry .theme-title {
	background: #878F93;
	display: block;
	font-size: 1em;
	margin-bottom: 3px;
}

.portlet-communities .lfr-theme-list .theme-entry:hover {
	background: #DFF4FF;
	border-color: #A7CEDF;
	color: #9ebac8;
	position: relative;
	text-decoration: underline;
}

.portlet-communities .theme-entry:hover .theme-thumbnail {
	border: 1px solid #A7CEDF;
	display: block;
	height: 120px;
	left: -17px;
	padding: 1px;
	position: absolute;
	top: 2em;
	width: 150px;
	z-index: 100;
}

.portlet-communities .theme-entry:hover .theme-title {
	background: #1E2529;
}

.portlet-communities .theme-thumbnail {
	height: 68px;
	width: 85px;
}

.portlet-communities .lfr-available-themes h3 {
	margin: 0;
	overflow: hidden;
}

.portlet-communities .lfr-available-themes .lfr-theme-list {
	margin-top: 0.7em;
}

.portlet-communities .lfr-available-themes .header-title {
	float: left;
}

.portlet-communities .lfr-available-themes .download-themes {
	float: right;
	font-size: 11px;
}

.portlet-communities .theme-details dl, .portlet-communities .lfr-theme-list .theme-details dd {
	margin: 0;
}

.portlet-communities .theme-details dl {
	margin-bottom: 1em;
}

.portlet-communities .theme-details dt {
	font-weight: bold;
}

.portlet-communities .selected-color-scheme .theme-entry {
	border: 3px solid #369;
}

.portlet-communities .selected-color-scheme .theme-entry .theme-title {
	background: #1E2529;
}
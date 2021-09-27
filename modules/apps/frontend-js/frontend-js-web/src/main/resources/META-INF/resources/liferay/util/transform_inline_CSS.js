/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

export default function transformInlineCSS(data) {
	const parser = new DOMParser();
	const htmlDoc = parser.parseFromString(data, 'text/html');
	const imgTags = htmlDoc.getElementsByTagName('img');

	for (let i = 0; i < imgTags.length; i++) {
		const imgTag = imgTags[i];
		if (
			imgTag.getAttribute('width') !== null &&
			imgTag.getAttribute('height') !== null
		) {
			continue;
		}
		const style = imgTag.getAttribute('style');

		if (style === null || style === '') {
			continue;
		}

		const styleHeight = imgTag.style.removeProperty('height');
		if (styleHeight) {
			imgTag.setAttribute('height', styleHeight.replace('px', ''));
		}

		const styleWidth = imgTag.style.removeProperty('width');
		if (styleWidth) {
			imgTag.setAttribute('width', styleWidth.replace('px', ''));
		}
	}

	return htmlDoc.body.innerHTML;
}

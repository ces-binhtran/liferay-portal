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

import classNames from 'classnames';
import {Link} from 'react-router-dom';

import i18n from '../../i18n';
import TestrayIcons from '../Icons/TestrayIcon';
import Tooltip from '../Tooltip';

type SidebarItemProps = {
	active?: boolean;
	className?: string;
	expanded?: boolean;
	icon: string;
	label: string;
	path?: string;
};

const SidebarItem: React.FC<SidebarItemProps> = ({
	active,
	expanded,
	icon,
	label,
	path,
}) => {
	const LinkComponent = path
		? Link
		: ({children, ...props}: any) => <div {...props}>{children}</div>;

	return (
		<LinkComponent
			className={classNames('tr-sidebar__content__list__item', {
				'tr-sidebar__content__list__item--active': active,
			})}
			to={path as string}
		>
			<Tooltip
				className="align-items-center d-flex"
				position="right"
				title={expanded ? undefined : i18n.translate(label)}
			>
				<TestrayIcons
					className="tr-sidebar__content__list__item__icon"
					fill={active ? 'white' : '#8b8db2'}
					size={35}
					symbol={icon}
				/>

				<span
					className={classNames(
						'tr-sidebar__content__list__item__text',
						{
							'tr-sidebar__content__list__item__text--expanded': expanded,
						}
					)}
				>
					{label}
				</span>
			</Tooltip>
		</LinkComponent>
	);
};

export default SidebarItem;

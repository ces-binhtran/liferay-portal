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

import {Align} from '@clayui/drop-down';
import classNames from 'classnames';
import {useContext} from 'react';

import {TestrayContext} from '../../context/TestrayContext';
import i18n from '../../i18n';
import {ForwardIcon} from '../../images';
import {Liferay} from '../../services/liferay';
import Avatar from '../Avatar';
import DropDown from '../DropDown';
import TestrayIcons from '../Icons/TestrayIcon';
import Tooltip from '../Tooltip';
import useSidebarActions from './useSidebarActions';

type SidebarProps = {
	expanded: boolean;
	onClick: () => void;
};

const SidebarFooter: React.FC<SidebarProps> = ({expanded, onClick}) => {
	const [{myUserAccount}] = useContext(TestrayContext);
	const MANAGE_DROPDOWN = useSidebarActions();

	const loggedUserName = myUserAccount
		? `${myUserAccount?.givenName} ${myUserAccount?.familyName}`
		: Liferay.ThemeDisplay.getUserName();

	return (
		<div className="tr-sidebar__content__footer">
			<div className="d-flex justify-content-end">
				<Tooltip
					position="right"
					title={expanded ? undefined : i18n.translate('expand')}
				>
					<div onClick={onClick}>
						<ForwardIcon
							className={classNames(
								'tr-sidebar__content__footer__forwardicon',
								{
									'tr-sidebar__content__footer__forwardicon--expand': expanded,
								}
							)}
						/>
					</div>
				</Tooltip>
			</div>

			<DropDown
				items={MANAGE_DROPDOWN}
				position={Align.RightBottom}
				trigger={
					<div>
						<Tooltip
							position="right"
							title={
								expanded ? undefined : i18n.translate('manage')
							}
						>
							<div
								className={classNames(
									'tr-sidebar__content__list__item',
									{
										'tr-sidebar__content__list__item--expand': expanded,
										'tr-sidebar__content__list__item--normal': !expanded,
									}
								)}
							>
								<TestrayIcons
									className="tr-sidebar__content__list__item__icon"
									fill="#8b8db2"
									size={30}
									symbol="cog"
								/>

								<span
									className={classNames(
										'tr-sidebar__content__list__item__text',
										{
											'tr-sidebar__content__list__item__text--expanded': expanded,
										}
									)}
								>
									{i18n.translate('manage')}
								</span>
							</div>
						</Tooltip>
					</div>
				}
			/>

			<div className="tr-sidebar__content__divider" />

			<DropDown
				items={[
					{
						items: [
							{
								icon: 'user',
								label: i18n.translate('manage-account'),
								path: '/manage/user/me',
							},
							{
								icon: 'logout',
								label: i18n.translate('sign-out'),
								path: `${window.location.origin}/c/portal/logout`,
							},
						],
						title: '',
					},
				]}
				position={Align.RightBottom}
				trigger={
					<div>
						<Tooltip
							position="right"
							title={expanded ? undefined : loggedUserName}
						>
							<div className="tr-sidebar__content__list__item">
								<Avatar
									className={classNames(
										'tr-sidebar__content__footer__avatar',
										{
											'tr-sidebar__content__footer__avatar--expanded': expanded,
										}
									)}
									displayName
									displayTooltip={false}
									expanded={expanded}
									name={loggedUserName}
									url={myUserAccount?.image}
								/>
							</div>
						</Tooltip>
					</div>
				}
			/>
		</div>
	);
};

export default SidebarFooter;

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

import '@testing-library/jest-dom/extend-expect';
import {cleanup, render, wait, within} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import React from 'react';

import Detail from '../../../src/main/resources/META-INF/resources/js/components/Detail';
import {ChartStateContextProvider} from '../../../src/main/resources/META-INF/resources/js/context/ChartStateContext';

const mockOnCurrentPageChange = jest.fn(() => Promise.resolve({view: 'main'}));

const mockOnTrafficSourceNameChange = jest.fn(() => Promise.resolve(''));

const mockTrafficShareDataProvider = jest.fn(() => Promise.resolve(90));

const mockTrafficVolumeDataProvider = jest.fn(() => Promise.resolve(278256));

const mockCurrentPageOrganic = {
	data: {
		countryKeywords: [
			{
				countryCode: 'us',
				countryName: 'United States',
				keywords: [
					{
						keyword: 'commerce',
						position: 1,
						searchVolume: 12300,
						traffic: 90000,
					},
					{
						keyword: 'e-commerce',
						position: 2,
						searchVolume: 9800,
						traffic: 14800,
					},
					{
						keyword: 'what is commerce',
						position: 3,
						searchVolume: 9500,
						traffic: 14000,
					},
					{
						keyword: 'what is e-commerce',
						position: 4,
						searchVolume: 8700,
						traffic: 12100,
					},
					{
						keyword:
							'commerce definition for new business strategy',
						position: 5,
						searchVolume: 7100,
						traffic: 10100,
					},
				],
			},
			{
				countryCode: 'es',
				countryName: 'Spain',
				keywords: [
					{
						keyword: 'commerce',
						position: 1,
						searchVolume: 12300,
						traffic: 90000,
					},
					{
						keyword: 'e-commerce',
						position: 2,
						searchVolume: 9800,
						traffic: 14800,
					},
					{
						keyword: 'what is commerce',
						position: 3,
						searchVolume: 9500,
						traffic: 14000,
					},
					{
						keyword: 'what is e-commerce',
						position: 4,
						searchVolume: 8700,
						traffic: 12100,
					},
					{
						keyword:
							'commerce definition for new business strategy',
						position: 5,
						searchVolume: 7100,
						traffic: 10100,
					},
				],
			},
		],
		helpMessage:
			'This number refers to the volume of people that find your page through a search engine.',
		name: 'organic',
		share: 90,
		title: 'Organic',
		value: 278256,
	},
	view: 'organic',
};

const mockCurrentPageReferral = {
	data: {
		helpMessage:
			'This is the number of page views generated by people coming to your page from other sites which are not search engine pages or social sites.',
		name: 'referral',
		referringDomains: [
			{
				trafficAmount: 17985230,
				url: 'http://youtube.com/',
			},
			{
				trafficAmount: 12218030,
				url: 'http://www.google.com/',
			},
			{
				trafficAmount: 9062949,
				url: 'http://microsoft.com/',
			},
			{
				trafficAmount: 4601453,
				url: 'http://linkedin.com/',
			},
			{
				trafficAmount: 253399,
				url: 'https://www.liferay.com',
			},
		],
		referringPages: [
			{
				trafficAmount: 125461,
				url:
					'https://www.liferay.com/resources/ebooks/Becoming+a+Digital+Business-4+Common+Enterprise+Challenges+Conquered',
			},
			{
				trafficAmount: 85485,
				url:
					'https://www.liferay.com/resources/whitepapers/B2B+E-Commerce+RFP+Kit',
			},
			{
				trafficAmount: 84564,
				url:
					'https://www.liferay.com/resources/whitepapers/6+Tactics+to+Modernize+Your+Intranet',
			},
			{
				trafficAmount: 5846,
				url:
					'https://www.liferay.com/resources/case-studies/materion-case-study',
			},
			{
				trafficAmount: 3521,
				url: 'https://www.liferay.com/web/l/a1-hrvatska-case-study',
			},
			{
				trafficAmount: 2513,
				url:
					'https://www.liferay.com/resources/case-studies/excellus-case-study',
			},
			{
				trafficAmount: 2200,
				url:
					'https://www.liferay.com/resources/case-studies/terres-inovia-case-study',
			},
			{
				trafficAmount: 1230,
				url:
					'https://www.liferay.com/resources/case-studies/vodafone-business',
			},
			{
				trafficAmount: 1100,
				url:
					'https://www.liferay.com/web/guest/resources/case-studies/agia',
			},
			{
				trafficAmount: 100,
				url:
					'https://www.liferay.com/resources/case-studies/vitality-case-study',
			},
		],
		share: 90,
		title: 'Referral',
		value: 278256,
	},
	view: 'referral',
};

const mockCurrentPageSocial = {
	data: {
		helpMessage:
			'This is the number of page views generated by people coming to your page from social sites.',
		name: 'social',
		referringSocialMedia: [
			{
				name: 'facebook',
				title: 'Facebook',
				trafficAmount: 1729,
			},
			{
				name: 'linkedin',
				title: 'LinkedIn',
				trafficAmount: 1256,
			},
			{
				name: 'pinterest',
				title: 'Pinterest',
				trafficAmount: 771,
			},
			{
				name: 'others',
				title: 'Others',
				trafficAmount: 30,
			},
		],
		share: 90,
		title: 'Social',
		value: 278256,
	},
	view: 'social',
};

const mockTimeSpanOptions = [
	{
		key: 'last-30-days',
		label: 'Last 30 Days',
	},
	{
		key: 'last-7-days',
		label: 'Last 7 Days',
	},
	{
		key: 'last-24-hours',
		label: 'Last 24 Hours',
	},
];

describe('Detail', () => {
	afterEach(() => {
		jest.clearAllMocks();
		cleanup();
	});

	describe('Organic Detail', () => {
		it('displays the organic detail according to API', async () => {
			const {getByText} = render(
				<Detail
					currentPage={mockCurrentPageOrganic}
					languageTag={'en-US'}
					onCurrentPageChange={mockOnCurrentPageChange}
					onTrafficSourceNameChange={mockOnTrafficSourceNameChange}
					timeSpanOptions={mockTimeSpanOptions}
					trafficShareDataProvider={mockTrafficShareDataProvider}
					trafficVolumeDataProvider={mockTrafficVolumeDataProvider}
				/>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			expect(getByText('Organic')).toBeInTheDocument();
			expect(getByText('90%')).toBeInTheDocument();
			expect(getByText('278,256')).toBeInTheDocument();

			expect(mockTrafficShareDataProvider).toHaveBeenCalledTimes(1);
			expect(mockTrafficVolumeDataProvider).toHaveBeenCalledTimes(1);
		});

		it('displays the top five relevant keywords by country sorted by traffic', async () => {
			const {getByText} = render(
				<Detail
					currentPage={mockCurrentPageOrganic}
					languageTag={'en-US'}
					onCurrentPageChange={mockOnCurrentPageChange}
					onTrafficSourceNameChange={mockOnTrafficSourceNameChange}
					timeSpanOptions={mockTimeSpanOptions}
					trafficShareDataProvider={mockTrafficShareDataProvider}
					trafficVolumeDataProvider={mockTrafficVolumeDataProvider}
				/>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			expect(getByText('United States')).toBeInTheDocument();
			expect(getByText('Spain')).toBeInTheDocument();

			expect(getByText('commerce')).toBeInTheDocument();
			expect(getByText('90,000')).toBeInTheDocument();

			expect(getByText('e-commerce')).toBeInTheDocument();
			expect(getByText('14,800')).toBeInTheDocument();

			expect(getByText('what is commerce')).toBeInTheDocument();
			expect(getByText('14,000')).toBeInTheDocument();

			expect(getByText('what is e-commerce')).toBeInTheDocument();
			expect(getByText('12,100')).toBeInTheDocument();

			expect(
				getByText('commerce definition for new business strategy')
			).toBeInTheDocument();
			expect(getByText('10,100')).toBeInTheDocument();
		});

		it('displays a tooltip with info on hover tooltip signs', async () => {
			const {getByText} = render(
				<Detail
					currentPage={mockCurrentPageOrganic}
					languageTag={'en-US'}
					onCurrentPageChange={mockOnCurrentPageChange}
					onTrafficSourceNameChange={mockOnTrafficSourceNameChange}
					timeSpanOptions={mockTimeSpanOptions}
					trafficShareDataProvider={mockTrafficShareDataProvider}
					trafficVolumeDataProvider={mockTrafficVolumeDataProvider}
				/>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			const bestKeywordLabel = getByText('best-keyword');
			const questionCircleIcon = within(bestKeywordLabel).getByRole(
				'presentation'
			);
			userEvent.click(questionCircleIcon);
			expect(getByText('best-keyword-help')).toBeInTheDocument();
		});

		it('displays a dropdown with options to display in the keywords table', async () => {
			const {getAllByText, getByText} = render(
				<Detail
					currentPage={mockCurrentPageOrganic}
					languageTag={'en-US'}
					onCurrentPageChange={mockOnCurrentPageChange}
					onTrafficSourceNameChange={mockOnTrafficSourceNameChange}
					timeSpanOptions={mockTimeSpanOptions}
					trafficShareDataProvider={mockTrafficShareDataProvider}
					trafficVolumeDataProvider={mockTrafficVolumeDataProvider}
				/>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			const trafficDropdown = getAllByText('traffic')[0];
			userEvent.click(trafficDropdown);
			expect(getByText('search-volume')).toBeInTheDocument();
			expect(getByText('position')).toBeInTheDocument();
		});

		it('displays the top five relevant keywords sorted by search volume when user clicks on the dropdown option search volume', async () => {
			const {getAllByText, getByText} = render(
				<Detail
					currentPage={mockCurrentPageOrganic}
					languageTag={'en-US'}
					onCurrentPageChange={mockOnCurrentPageChange}
					onTrafficSourceNameChange={mockOnTrafficSourceNameChange}
					timeSpanOptions={mockTimeSpanOptions}
					trafficShareDataProvider={mockTrafficShareDataProvider}
					trafficVolumeDataProvider={mockTrafficVolumeDataProvider}
				/>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			const trafficDropdown = getAllByText('traffic')[0];
			userEvent.click(trafficDropdown);
			const searchVolumeLabel = getByText('search-volume');
			userEvent.click(searchVolumeLabel);

			expect(getByText('commerce')).toBeInTheDocument();
			expect(getByText('12,300')).toBeInTheDocument();

			expect(getByText('e-commerce')).toBeInTheDocument();
			expect(getByText('9,800')).toBeInTheDocument();

			expect(getByText('what is commerce')).toBeInTheDocument();
			expect(getByText('9,500')).toBeInTheDocument();

			expect(getByText('what is e-commerce')).toBeInTheDocument();
			expect(getByText('8,700')).toBeInTheDocument();

			expect(
				getByText('commerce definition for new business strategy')
			).toBeInTheDocument();
			expect(getByText('7,100')).toBeInTheDocument();
		});

		it('displays the top five relevant keywords sorted by position when user clicks on the dropdown option position', async () => {
			const {getAllByText, getByText} = render(
				<Detail
					currentPage={mockCurrentPageOrganic}
					languageTag={'en-US'}
					onCurrentPageChange={mockOnCurrentPageChange}
					onTrafficSourceNameChange={mockOnTrafficSourceNameChange}
					timeSpanOptions={mockTimeSpanOptions}
					trafficShareDataProvider={mockTrafficShareDataProvider}
					trafficVolumeDataProvider={mockTrafficVolumeDataProvider}
				/>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			const trafficDropdown = getAllByText('traffic')[0];
			userEvent.click(trafficDropdown);
			const searchVolumeLabel = getByText('position');
			userEvent.click(searchVolumeLabel);

			expect(getByText('commerce')).toBeInTheDocument();
			expect(getByText('1')).toBeInTheDocument();

			expect(getByText('e-commerce')).toBeInTheDocument();
			expect(getByText('2')).toBeInTheDocument();

			expect(getByText('what is commerce')).toBeInTheDocument();
			expect(getByText('3')).toBeInTheDocument();

			expect(getByText('what is e-commerce')).toBeInTheDocument();
			expect(getByText('4')).toBeInTheDocument();

			expect(
				getByText('commerce definition for new business strategy')
			).toBeInTheDocument();
			expect(getByText('5')).toBeInTheDocument();
		});
	});

	describe('Referral Detail', () => {
		it('displays the referral detail according to API', async () => {
			const testProps = {
				pagePublishDate: 'Thu Aug 10 08:17:57 GMT 2020',
				timeRange: {endDate: '2020-01-27', startDate: '2020-02-02'},
				timeSpanKey: 'last-7-days',
			};

			const {getByText} = render(
				<ChartStateContextProvider
					publishDate={testProps.publishDate}
					timeRange={testProps.timeRange}
					timeSpanKey={testProps.timeSpanKey}
				>
					<Detail
						currentPage={mockCurrentPageReferral}
						languageTag={'en-US'}
						onCurrentPageChange={mockOnCurrentPageChange}
						onTrafficSourceNameChange={
							mockOnTrafficSourceNameChange
						}
						timeSpanOptions={mockTimeSpanOptions}
						trafficShareDataProvider={mockTrafficShareDataProvider}
						trafficVolumeDataProvider={
							mockTrafficVolumeDataProvider
						}
					/>
				</ChartStateContextProvider>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			expect(getByText('Referral')).toBeInTheDocument();
			expect(getByText('90%')).toBeInTheDocument();
			expect(getByText('278,256')).toBeInTheDocument();

			expect(getByText('top-referring-pages')).toBeInTheDocument();
			expect(
				getByText(
					'https://www.liferay.com/resources/ebooks/Becoming+a+Digital+Business-4+Common+Enterprise+Challenges+Conquered'
				)
			).toBeInTheDocument();
			expect(
				getByText(
					'https://www.liferay.com/resources/whitepapers/B2B+E-Commerce+RFP+Kit'
				)
			).toBeInTheDocument();
			expect(
				getByText(
					'https://www.liferay.com/resources/whitepapers/6+Tactics+to+Modernize+Your+Intranet'
				)
			).toBeInTheDocument();
			expect(
				getByText(
					'https://www.liferay.com/resources/case-studies/materion-case-study'
				)
			).toBeInTheDocument();
			expect(
				getByText(
					'https://www.liferay.com/web/l/a1-hrvatska-case-study'
				)
			).toBeInTheDocument();

			expect(getByText('view-more')).toBeInTheDocument();

			expect(getByText('top-referring-domains')).toBeInTheDocument();
			expect(getByText('http://youtube.com/')).toBeInTheDocument();
			expect(getByText('http://www.google.com/')).toBeInTheDocument();
			expect(getByText('http://microsoft.com/')).toBeInTheDocument();
			expect(getByText('http://linkedin.com/')).toBeInTheDocument();
			expect(getByText('https://www.liferay.com')).toBeInTheDocument();

			expect(mockTrafficShareDataProvider).toHaveBeenCalledTimes(1);
			expect(mockTrafficVolumeDataProvider).toHaveBeenCalledTimes(1);
		});
	});

	describe('Social Detail', () => {
		it('displays the social detail according to API', async () => {
			const testProps = {
				pagePublishDate: 'Thu Aug 10 08:17:57 GMT 2020',
				timeRange: {endDate: '2020-01-27', startDate: '2020-02-02'},
				timeSpanKey: 'last-7-days',
			};

			const {getByText} = render(
				<ChartStateContextProvider
					publishDate={testProps.publishDate}
					timeRange={testProps.timeRange}
					timeSpanKey={testProps.timeSpanKey}
				>
					<Detail
						currentPage={mockCurrentPageSocial}
						languageTag={'en-US'}
						onCurrentPageChange={mockOnCurrentPageChange}
						onTrafficSourceNameChange={
							mockOnTrafficSourceNameChange
						}
						timeSpanOptions={mockTimeSpanOptions}
						trafficShareDataProvider={mockTrafficShareDataProvider}
						trafficVolumeDataProvider={
							mockTrafficVolumeDataProvider
						}
					/>
				</ChartStateContextProvider>
			);

			await wait(() =>
				expect(mockTrafficShareDataProvider).toHaveBeenCalled()
			);
			await wait(() =>
				expect(mockTrafficVolumeDataProvider).toHaveBeenCalled()
			);

			expect(getByText('Social')).toBeInTheDocument();
			expect(getByText('90%')).toBeInTheDocument();
			expect(getByText('278,256')).toBeInTheDocument();

			expect(getByText('top-referring-social-media')).toBeInTheDocument();

			expect(getByText('Facebook')).toBeInTheDocument();
			expect(getByText('1,729')).toBeInTheDocument();
			expect(getByText('LinkedIn')).toBeInTheDocument();
			expect(getByText('1,256')).toBeInTheDocument();
			expect(getByText('Pinterest')).toBeInTheDocument();
			expect(getByText('771')).toBeInTheDocument();
			expect(getByText('Others')).toBeInTheDocument();
			expect(getByText('30')).toBeInTheDocument();

			expect(mockTrafficShareDataProvider).toHaveBeenCalledTimes(1);
			expect(mockTrafficVolumeDataProvider).toHaveBeenCalledTimes(1);
		});
	});
});

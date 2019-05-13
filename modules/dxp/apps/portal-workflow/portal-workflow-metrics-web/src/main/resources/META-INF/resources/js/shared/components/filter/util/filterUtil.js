import { parse, stringify } from '../../router/queryString';
import pathToRegexp from 'path-to-regexp';

export function getFiltersParam(queryString) {
	const queryParams = parse(queryString);

	return queryParams.filters || {};
}

export function getSelectedItemsQuery(items, key, queryString) {
	const queryParams = parse(queryString);

	const filtersParam = queryParams.filters || {};

	queryParams.filters = {
		...filtersParam,
		[key]: items.filter(item => item.active).map(item => item.key)
	};

	return stringify(queryParams);
}

export function isSelected(filterKey, itemKey, queryString) {
	const filtersParam = getFiltersParam(queryString);

	const filterValues = filtersParam[filterKey] || [];

	return filterValues.includes(itemKey);
}

export function pushToHistory(filterQuery, routerProps) {
	const {
		history,
		location: { search },
		match: { params, path }
	} = routerProps;

	const pathname = pathToRegexp.compile(path)({ ...params, page: 1 });

	if (filterQuery !== search) {
		history.push({
			pathname,
			search: filterQuery
		});
	}
}

export function removeFilters(queryString) {
	const queryParams = parse(queryString);

	queryParams.filters = null;

	return stringify(queryParams);
}

export function removeItem(filter, itemToRemove, queryString) {
	const queryParams = parse(queryString);

	const filtersParam = queryParams.filters || {};

	const currentFilter = filtersParam[filter.key] || [];

	filtersParam[filter.key] = currentFilter.filter(
		itemKey => itemKey != itemToRemove.key
	);

	queryParams.filters = filtersParam;

	return stringify(queryParams);
}

export function verifySelectedItems(filter, filtersParam) {
	const filterValues = filtersParam[filter.key] || [];

	filter.items.forEach(item => {
		if (Array.isArray(filterValues)) {
			item.active = filterValues.includes(item.key);
		}
		else {
			item.active = filterValues === item.key;
		}
	});

	return filter;
}
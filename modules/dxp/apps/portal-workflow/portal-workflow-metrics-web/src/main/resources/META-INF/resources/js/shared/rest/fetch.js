import axios from 'axios';

/**
 * @module fetch
 * @description Basic alias of fetch client to perform consults in portal rest API.
 * @example
 * import fetch from '@/shared/rest/fetch';
 * fetch.get('/process').then(res => console.log(res));
 */
const restClient = axios.create({
	baseURL: '/o/portal-workflow-metrics/v1.0'
});

export default restClient;
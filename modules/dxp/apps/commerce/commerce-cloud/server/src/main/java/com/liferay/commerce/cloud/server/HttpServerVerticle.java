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

package com.liferay.commerce.cloud.server;

import com.liferay.commerce.cloud.server.constants.ContentTypes;
import com.liferay.commerce.cloud.server.handler.ActiveProjectAuthHandler;
import com.liferay.commerce.cloud.server.handler.GetForecastConfigurationHandler;
import com.liferay.commerce.cloud.server.handler.PostOrdersHandler;
import com.liferay.commerce.cloud.server.handler.PutForecastConfigurationHandler;
import com.liferay.commerce.cloud.server.service.ForecastConfigurationService;
import com.liferay.commerce.cloud.server.service.OrderService;
import com.liferay.commerce.cloud.server.service.ProjectService;
import com.liferay.commerce.cloud.server.util.CommerceCloudUtil;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author Andrea Di Giorgi
 */
public class HttpServerVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		ConfigRetriever configRetriever = ConfigRetriever.create(vertx);

		ConfigRetriever.getConfigAsFuture(
			configRetriever
		).compose(
			this::_startHttpServer
		).compose(
			httpServer -> {
				if (_logger.isInfoEnabled()) {
					_logger.info(
						"HTTP server started listening on port {0}",
						httpServer.actualPort());
				}

				startFuture.complete();
			},
			startFuture
		);
	}

	private void _addRouteGetForecastConfiguration(
		Router router, ActiveProjectAuthHandler activeProjectAuthHandler,
		ForecastConfigurationService forecastConfigurationService) {

		Route route = router.get("/projects/:projectId/configuration/");

		route.handler(activeProjectAuthHandler);

		route.handler(
			new GetForecastConfigurationHandler(forecastConfigurationService));

		route.produces(ContentTypes.APPLICATION_JSON);
	}

	private void _addRoutePostOrders(
		Router router, ActiveProjectAuthHandler activeProjectAuthHandler,
		OrderService orderService) {

		Route route = router.post("/projects/:projectId/orders/");

		route.handler(BodyHandler.create());

		route.handler(activeProjectAuthHandler);

		route.handler(new PostOrdersHandler(orderService));

		route.consumes(ContentTypes.APPLICATION_JSON);
	}

	private void _addRoutePutForecastConfiguration(
		Router router, ActiveProjectAuthHandler activeProjectAuthHandler,
		ForecastConfigurationService forecastConfigurationService) {

		Route route = router.put("/projects/:projectId/configuration/");

		route.handler(BodyHandler.create());

		route.handler(activeProjectAuthHandler);

		route.handler(
			new PutForecastConfigurationHandler(forecastConfigurationService));

		route.consumes(ContentTypes.APPLICATION_JSON);
	}

	private Future<HttpServer> _startHttpServer(JsonObject configJsonObject) {
		Future<HttpServer> future = Future.future();

		HttpServer httpServer = vertx.createHttpServer();

		Router router = Router.router(vertx);

		ForecastConfigurationService forecastConfigurationService =
			ForecastConfigurationService.createProxy(vertx);
		OrderService orderService = OrderService.createProxy(vertx);
		ProjectService projectService = ProjectService.createProxy(vertx);

		ActiveProjectAuthHandler activeProjectAuthHandler =
			new ActiveProjectAuthHandler(projectService);

		_addRouteGetForecastConfiguration(
			router, activeProjectAuthHandler, forecastConfigurationService);
		_addRoutePostOrders(router, activeProjectAuthHandler, orderService);
		_addRoutePutForecastConfiguration(
			router, activeProjectAuthHandler, forecastConfigurationService);

		httpServer.requestHandler(router::accept);

		int port = CommerceCloudUtil.getPort(configJsonObject);

		httpServer.listen(port, future.completer());

		return future;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		HttpServerVerticle.class);

}
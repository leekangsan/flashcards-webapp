package com.codingchili.flashcards.handlers;

import com.codingchili.core.context.CoreContext;
import com.codingchili.core.listener.CoreService;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Webserver to serve the web interface.
 */
public class Webserver implements CoreService {
    private CoreContext core;

    public void init(CoreContext core) {
        this.core = core;
    }

    public void start(Future start) {
        Router router = Router.router(core.vertx());
        router.route().handler(BodyHandler.create());
        router.route("/*").handler(StaticHandler.create());
        core.vertx().createHttpServer().requestHandler(router::accept)
                .listen(80, start);
    }

}

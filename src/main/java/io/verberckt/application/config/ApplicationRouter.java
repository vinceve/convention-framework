package io.verberckt.application.config;

import io.verberckt.framework.router.BaseRouter;
import io.verberckt.framework.router.RouterContainer;

public class ApplicationRouter extends BaseRouter {
    @Override
    public void setEntries(RouterContainer routes) {
        routes.add("GET", "/", "Home", "index");
        routes.addResource("notes");
    }
}

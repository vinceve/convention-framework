package io.verberckt.framework.router;

public abstract class BaseRouter implements Router {
    private RouterContainer routes = new RouterContainer();

    @Override
    public RouterContainer getRouter() {
        this.setEntries(routes);
        return routes;
    }
}

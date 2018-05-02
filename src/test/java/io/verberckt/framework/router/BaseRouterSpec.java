package io.verberckt.framework.router;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseRouterSpec {
    class ApplicationRouter extends BaseRouter {
        @Override
        public void setEntries(RouterContainer routes) {
            routes.add("GET", "/", "home", "index");
        }
    }

    @Test
    public void testRouteAdded() {
        var applicationRouter = new ApplicationRouter();
        assertEquals(1, applicationRouter.getRouter().getRoutes().size());
    }

}

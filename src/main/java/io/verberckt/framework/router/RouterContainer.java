package io.verberckt.framework.router;

import io.verberckt.framework.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RouterContainer {
    private List<Entry> routes = new ArrayList<>();

    public void add(String method, String url, String controller, String action) {
        this.routes.add(new Entry( method, url, controller, action));
    }

    public void addResource(String resourceName) {
        String controllerName = StringUtils.capitalize(resourceName);

        this.add("GET", "/" + resourceName, controllerName, "index");
        this.add("GET", "/" + resourceName + "/:id", controllerName, "get");
        this.add("POST", "/" + resourceName, controllerName, "create");
        this.add("PUT", "/" + resourceName + "/:id", controllerName, "update");
        this.add("DELETE", "/" + resourceName + "/:id", controllerName, "destroy");
    }

    public List<Entry> getRoutes() {
        return routes;
    }
}

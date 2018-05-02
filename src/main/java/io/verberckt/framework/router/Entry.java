package io.verberckt.framework.router;

public class Entry {
    private String method;
    private String url;
    private String controller;
    private String action;

    public Entry(String method, String url, String controller, String action) {
        this.method = method;
        this.url = url;
        this.controller = controller;
        this.action = action;
    }

    public String getMethod() {
        return method;
    }


    public String getUrl() {
        return url;
    }

    public String getController() {
        return controller;
    }

    public String getAction() {
        return action;
    }
}

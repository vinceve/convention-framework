package io.verberckt.framework.controllers;

public abstract class BaseController implements Controller {
    private String output;

    public String getOutput() {
        return output;
    }

    public void render(String output) {
        this.output = output;
    }
}

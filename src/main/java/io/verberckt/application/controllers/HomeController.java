package io.verberckt.application.controllers;

import io.verberckt.framework.controllers.BaseController;

public class HomeController extends BaseController {

    public void index() {
        this.render("this is the index page.");
    }

}

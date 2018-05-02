package io.verberckt.application.controllers;

import io.verberckt.framework.controllers.BaseController;

public class NotesController extends BaseController {

    public void index() {
        this.render("this is the notes list page.");
    }

}

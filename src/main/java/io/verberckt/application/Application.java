package io.verberckt.application;

import io.verberckt.application.config.ApplicationRouter;
import io.verberckt.framework.server.WebServer;
import io.verberckt.framework.router.Router;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Router router = new ApplicationRouter();

        try {
            var server = new WebServer("localhost", 8080, "io.verberckt.application");
            server.listen(router);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

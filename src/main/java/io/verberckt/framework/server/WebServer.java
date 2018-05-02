package io.verberckt.framework.server;

import fi.iki.elonen.NanoHTTPD;
import io.verberckt.framework.router.ActionRouter;
import io.verberckt.framework.router.Router;
import io.verberckt.framework.router.exceptions.RouteNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class WebServer extends NanoHTTPD {

    private ActionRouter router;
    private String namespace;

    public WebServer(String hostname, int port, String namespace) {
        super(hostname, port);
        this.namespace = namespace;
    }

    public void listen(Router applicationRoutes) throws IOException {
        this.router = new ActionRouter(applicationRoutes, this.namespace);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {

        try {
            var controller= this.router.run(session.getMethod().name(), session.getUri());
            return newFixedLengthResponse(controller.getOutput());
        } catch (RouteNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Define your controller.");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Action is not defined. " + e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Cannot start the controller. " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access the defined action. " + e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if(e.getCause() != null) {
                Throwable throwable = e.getCause();
                throwable.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }

        return newFixedLengthResponse("BOOM its broken");
    }
}

package io.verberckt.framework.router;

import io.verberckt.framework.controllers.Controller;
import io.verberckt.framework.router.exceptions.RouteNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class ActionRouter {
    private RouterContainer router;
    private final String namespace;

    public ActionRouter(Router router, final String namespace) {
        this.router = router.getRouter();
        this.namespace = namespace;
    }

    public Controller run(String method, String incomingRoute) throws RouteNotFoundException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Entry> routes = this.router.getRoutes();

        Optional<Entry> route = routes.stream().filter(entry ->
            entry.getUrl().equals(incomingRoute) && entry.getMethod().equalsIgnoreCase(method)
        ).findFirst();

        if(route.isPresent()) {
            Entry entry = route.get();

            Object controller = invokeController(entry);
            invokeMethod(controller, entry);

            return (Controller) controller;
        } else {
            throw new RouteNotFoundException("The following route could not be matched: " + incomingRoute);
        }
    }

    private Object invokeController(Entry entry) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(this.namespace + ".controllers." + entry.getController() + "Controller");
        Constructor<?> constructor = clazz.getConstructor();

        return constructor.newInstance();
    }

    private void invokeMethod(Object controller, Entry entry) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = controller.getClass().getMethod(entry.getAction(), (Class<?>[]) null);
        method.invoke(controller, (Object[]) null);
    }
}

package io.verberckt.framework.router;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RouterContainerSpec {

    private RouterContainer routes;

    @Before
    public void setup() {
        this.routes = new RouterContainer();
    }

    @Test
    public void testAddCheckUrl() {
        this.routes.add("GET", "/", "Tests", "index");

        List<Entry> entries = this.routes.getRoutes();

        assertEquals("The url is not correct", "/", entries.get(0).getUrl());
    }

    @Test
    public void testAddCheckMethod() {
        this.routes.add("GET", "/", "Tests", "index");

        List<Entry> entries = this.routes.getRoutes();

        assertEquals("The method is not correct", "GET", entries.get(0).getMethod());
    }

    @Test
    public void testAddCheckController() {
        this.routes.add("GET", "/", "Tests", "index");

        List<Entry> entries = this.routes.getRoutes();

        assertEquals("The controller is not correct", "Tests", entries.get(0).getController());
    }

    @Test
    public void testAddCheckAction() {
        this.routes.add("GET", "/", "Tests", "index");

        List<Entry> entries = this.routes.getRoutes();

        assertEquals("The action is not correct", "index", entries.get(0).getAction());
    }

    @Test
    public void testAddResource() {
        this.routes.addResource("tests");

        List<Entry> entries = this.routes.getRoutes();

        assertEquals(5, entries.size());
    }

    @Test
    public void testAddResourcePaths() {
        this.routes.addResource("tests");

        List<Entry> entries = this.routes.getRoutes();

        assertEquals("GET", entries.get(0).getMethod());
        assertEquals("GET", entries.get(1).getMethod());
        assertEquals("POST", entries.get(2).getMethod());
        assertEquals("PUT", entries.get(3).getMethod());
        assertEquals("DELETE", entries.get(4).getMethod());

        assertEquals("/tests", entries.get(0).getUrl());
        assertEquals("/tests/:id", entries.get(1).getUrl());
        assertEquals("/tests", entries.get(2).getUrl());
        assertEquals("/tests/:id", entries.get(3).getUrl());
        assertEquals("/tests/:id", entries.get(4).getUrl());

        assertEquals("Tests", entries.get(0).getController());
        assertEquals("Tests", entries.get(1).getController());
        assertEquals("Tests", entries.get(2).getController());
        assertEquals("Tests", entries.get(3).getController());
        assertEquals("Tests", entries.get(4).getController());

        assertEquals("index", entries.get(0).getAction());
        assertEquals("get", entries.get(1).getAction());
        assertEquals("create", entries.get(2).getAction());
        assertEquals("update", entries.get(3).getAction());
        assertEquals("destroy", entries.get(4).getAction());
    }
}

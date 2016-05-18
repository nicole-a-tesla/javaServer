package nmccabe;

import nmccabe.Handlers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class Router {
    protected HashMap<String, HashMap<String, Handler>> routes;
    private HashMap<String, Handler> getRoutes;
    private HashMap<String, Handler> optionsRoutes;
    private HashMap<String, Handler> postRoutes;
    private HashMap<String, Handler> putRoutes;
    private HashMap<String, Handler> headRoutes;



    public Router() {
        this.routes = new HashMap<>();
        this.getRoutes = new HashMap<>();
        this.optionsRoutes = new HashMap<>();
        this.postRoutes = new HashMap<>();
        this.putRoutes = new HashMap<>();
        this.headRoutes = new HashMap<>();

        loadGETRoutes();
        loadOPTIONSRoutes();
        loadPOSTRoutes();
        loadPUTRoutes();
        loadHEADRoutes();
        loadRoutes();
    }

    private void loadGETRoutes() {
        getRoutes.put("/tea", new TeapotHandler());
        getRoutes.put("/coffee", new TeapotHandler());

        getRoutes.put("resource_request", new ResourceHandler());

    }

    private void loadOPTIONSRoutes() {
        optionsRoutes.put("/method_options2", new MethodOptions2Handler());
        optionsRoutes.put("/method_options", new MethodOptionsHandler());
    }

    private void loadPOSTRoutes() {
        postRoutes.put("/form", new PostHandler());
    }

    private void loadPUTRoutes() {
        putRoutes.put("/form", new PutHandler());
    }


    private void loadHEADRoutes() {
        headRoutes.put("/", new HeadHandler());
    }

    private void loadRoutes() {
        routes.put("GET", getRoutes);
        routes.put("OPTIONS", optionsRoutes);
        routes.put("POST", postRoutes);
        routes.put("PUT", putRoutes);
        routes.put("HEAD", headRoutes);
    }

    public Handler getHandlerFor(Request request) {
        if (routes.keySet().contains(request.method)) {
            return fetchFromRoutes(request.method, request.route);
        } else {
            return new MethodNotAllowedHandler();
        }
    }

    private Handler fetchFromRoutes(String method, String route) {
        if (isResourceRequest(method, route)) {
            return getRoutes.get("resource_request");
        }

        if (Objects.equals(method, "HEAD")) {
            return headRoutes.get("/");
        }

        HashMap subRoutes = routes.getOrDefault(method, getRoutes);
        return (Handler) subRoutes.getOrDefault(route, new MethodNotAllowedHandler());
    }

    private boolean isResourceRequest(String method, String route) {
        return Objects.equals(method, "GET") && !getRoutes.keySet().contains(route);
    }

    public boolean isFile(String route) {
        File file = new File(System.getProperty("baseDir") + route);
        return file.exists();
    }
}

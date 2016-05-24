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
    private HashMap<String, Handler> deleteRoutes;
    private HashMap<String, Handler> patchRoutes;

    public Router() {
        this.routes = new HashMap<>();
        this.getRoutes = new HashMap<>();
        this.optionsRoutes = new HashMap<>();
        this.postRoutes = new HashMap<>();
        this.putRoutes = new HashMap<>();
        this.headRoutes = new HashMap<>();
        this.deleteRoutes = new HashMap<>();
        this.patchRoutes = new HashMap<>();

        loadGETRoutes();
        loadOPTIONSRoutes();
        loadPOSTRoutes();
        loadPUTRoutes();
        loadHEADRoutes();
        loadDELETERoutes();
        loadPATCHRoutes();
        loadRoutes();
    }

    private void loadGETRoutes() {
        getRoutes.put("/", new DirectoryHandler());

        getRoutes.put("/tea", new TeapotHandler());
        getRoutes.put("/coffee", new TeapotHandler());
        getRoutes.put("/redirect", new RedirectHandler());

        getRoutes.put("resource_request", new ResourceHandler());

    }

    private void loadOPTIONSRoutes() {
        optionsRoutes.put("/method_options2", new MethodOptions2Handler());
        optionsRoutes.put("/method_options", new MethodOptionsHandler());
    }

    private void loadPOSTRoutes() {
        postRoutes.put("/form", new AddingStuffHandler());
    }

    private void loadPUTRoutes() {
        putRoutes.put("/form", new AddingStuffHandler());
    }


    private void loadHEADRoutes() {
        headRoutes.put("/", new HeadHandler());
    }

    private void loadDELETERoutes() {
        deleteRoutes.put("/form", new DeleteHandler());
    }

    private void loadPATCHRoutes() {
        patchRoutes.put("/patch-content.txt", new AddingStuffHandler());
    }

    private void loadRoutes() {
        routes.put("GET", getRoutes);
        routes.put("OPTIONS", optionsRoutes);
        routes.put("POST", postRoutes);
        routes.put("PUT", putRoutes);
        routes.put("HEAD", headRoutes);
        routes.put("DELETE", deleteRoutes);
        routes.put("PATCH", patchRoutes);
    }

    public Handler getHandlerFor(Request request) {
        if (routes.keySet().contains(request.method)) {
            return fetchFromRoutes(request.method, request.route);
        } else {
            return new MethodNotAllowedHandler();
        }
    }

    private Handler fetchFromRoutes(String method, String route) {
        if (isResourceRequest(route) && Objects.equals(method, "GET")) {
            return getRoutes.get("resource_request");
        }

        if (Objects.equals(method, "DELETE")) {
            return deleteRoutes.get("/form");
        }


        if (Objects.equals(method, "HEAD")) {
            return headRoutes.get("/");
        }

        HashMap subRoutes = routes.getOrDefault(method, getRoutes);
        return (Handler) subRoutes.getOrDefault(route, new MethodNotAllowedHandler());
    }

    private boolean isResourceRequest(String route) {
        return !getRoutes.keySet().contains(route);
    }

    public boolean isFile(String route) {
        File file = new File(System.getProperty("baseDir") + route);
        return file.exists();
    }
}

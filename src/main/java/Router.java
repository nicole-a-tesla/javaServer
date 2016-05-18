import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class Router {
    protected HashMap<String, HashMap<String, Handler>> routes;
    private HashMap<String, Handler> getRoutes;
    private HashMap<String, Handler> optionsRoutes;
    private HashMap<String, Handler> postRoutes;
    private HashMap<String, Handler> putRoutes;


    public Router() {
        this.routes = new HashMap<>();
        this.getRoutes = new HashMap<>();
        this.optionsRoutes = new HashMap<>();
        this.postRoutes = new HashMap<>();
        this.putRoutes = new HashMap<>();

        loadGETRoutes();
        loadOPTIONSRoutes();
        loadPOSTRoutes();
        loadPUTRoutes();
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

    private void loadRoutes() {
        routes.put("GET", getRoutes);
        routes.put("OPTIONS", optionsRoutes);
        routes.put("POST", postRoutes);
        routes.put("PUT", putRoutes);
    }

    public Handler getHandlerFor(Request request) {
        return fetchFromRoutes(request.method, request.route);
    }

    private Handler fetchFromRoutes(String method, String route) {
        if (isFile(route) && Objects.equals(method, "GET")) {
            return getRoutes.get("resource_request");
        }

        HashMap subRoutes = routes.getOrDefault(method, getRoutes);
        return (Handler) subRoutes.getOrDefault(route, new Handler());
    }

    protected boolean isFile(String route) {
        File file = new File(System.getProperty("baseDir") + route);
        return file.exists();
    }
}

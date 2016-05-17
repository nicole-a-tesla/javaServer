import java.io.File;
import java.util.HashMap;

public class Router {
    protected HashMap<String, HashMap<String, Handler>> routes;
    private HashMap<String, Handler> getRoutes;
    private HashMap<String, Handler> optionsRoutes;

    public Router() {
        this.routes = new HashMap<>();
        this.getRoutes = new HashMap<>();
        this.optionsRoutes = new HashMap<>();

        loadGETRoutes();
        loadOPTIONSRoutes();
        loadRoutes();
    }

    private void loadGETRoutes() {
        getRoutes.put("/method_options", new MethodOptionsHandler());
        getRoutes.put("/method_options2", new MethodOptions2Handler());

        getRoutes.put("/tea", new TeapotHandler());
        getRoutes.put("/coffee", new TeapotHandler());

        getRoutes.put("resource_request", new ResourceHandler());

    }

    private void loadOPTIONSRoutes() {
        optionsRoutes.put("/method_options2", new MethodOptions2Handler());
    }

    private void loadRoutes() {
        routes.put("GET", getRoutes);
    }

    public Handler getHandlerFor(Request request) {
        return fetchFromRoutes(request.method, request.route);
    }

    private Handler fetchFromRoutes(String method, String route) {
        HashMap subRoutes = routes.getOrDefault(method, getRoutes);

        if (isFile(route)) {
            return (Handler) subRoutes.get("resource_request");
        } else {
            return (Handler) subRoutes.getOrDefault(route, new Handler());
        }
    }

    protected boolean isFile(String route) {
        File file = new File(System.getProperty("baseDir") + route);
        return file.exists();
    }
}

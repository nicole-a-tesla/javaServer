import java.io.File;
import java.util.HashMap;

public class Router {
    protected HashMap<String, HashMap<String, Handler>> routes;
    private HashMap<String, Handler> getRoutes;

    public Router() {
        this.routes = new HashMap<>();
        this.getRoutes = new HashMap<>();

        loadGETRoutes();
        loadRoutes();
    }

    private void loadGETRoutes() {
        getRoutes.put("/method_options", new MethodOptionsHandler());
        getRoutes.put("resource_request", new ResourceHandler());
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

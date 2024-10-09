package ro.webdata.humanities.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ro.webdata.humanities.server.commons.ENDPOINT;
import ro.webdata.humanities.server.endpoint.cho.CHOEndpoint;
import ro.webdata.humanities.server.endpoint.museum.MuseumEndpoint;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://mkyong.com/webservices/jax-rs/jersey-hello-world-example/
public class Server {
    // Base URI the Grizzly HTTP server will listen on
    private static final String BASE_URI = "http://%1$s:%2$s/";

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static HttpServer startServer(boolean local) {
        // Create a resource config that registers the MuseumEndpoint JAX-RS resource
        final ResourceConfig config = new ResourceConfig();

        // Registering like this will give warnings like:
        // WARNING: A provider com.example.controller.MuseumEndpoint registered in SERVER runtime does not implement any provider interfaces applicable in the SERVER runtime. Due to constraint configuration problems the provider
        // ro.webdata.humanities.server.museum.MuseumEndpoint will be ignored.
        // But it just works and according to stackoverflow this is a bug:
        // https://github.com/eclipse-ee4j/jersey/issues/3700
        config.register(CHOEndpoint.class);
        config.register(MuseumEndpoint.class);

        if (local) {
            config.register(new CORSFilter());
        }

        // Disable wadl because I never asked for this.
        config.property("jersey.config.server.wadl.disableWadl", true);

        LOGGER.info("Starting server...");

        URI uri = URI.create(String.format(BASE_URI, ENDPOINT.BASE_URI, ENDPOINT.BASE_PORT));

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }

    public static void main(String[] args) {
        try {
            final HttpServer httpServer = startServer(true);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    httpServer.shutdown();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    Logger.getLogger(MuseumEndpoint.class.getName()).log(Level.SEVERE, null, e);
                }
            }));

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            // block and wait shut down signal, like CTRL+C
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Logger.getLogger(MuseumEndpoint.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

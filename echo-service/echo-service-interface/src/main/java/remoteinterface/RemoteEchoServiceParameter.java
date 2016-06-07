package remoteinterface;

/**
 * Default parameter for set remote object {@link java.rmi.registry.Registry} with specified host and port
 *
 * @author hellnyk
 */
public class RemoteEchoServiceParameter {

    /**
     *default port for creating and exporting a {@link java.rmi.registry.Registry} instance
     */
    public static final int DEFAULT_SERVER_PORT = 8081;

    /**
     * default host for creating and exporting a {@link java.rmi.registry.Registry} instance
     */
    public static final String DEFAULT_SERVER_HOST = "localhost";
}

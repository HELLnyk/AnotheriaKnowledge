package constants;

/**
 * Client commands
 *
 * @author hellnyk
 */
public enum MainClientCommands {

    /**
     * get all values from the data storage
     */
    DIR,

    /**
     * add some value to the data storage on the server
     */
    PUT,

    /**
     * get some value from the server data storage by using <code>String</code> key
     */
    GET
}

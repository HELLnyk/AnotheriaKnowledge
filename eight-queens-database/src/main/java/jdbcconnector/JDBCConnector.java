package jdbcconnector;

import org.configureme.annotations.Configure;
import org.configureme.annotations.ConfigureMe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * connection for concrete database
 *
 * @author hellnyk
 */

@ConfigureMe(name = "databaseproperties")
public class JDBCConnector {

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCConnector.class);

    /**
     * {@link JDBCConnector} instance
     */
    private static final JDBCConnector INSTANCE = new JDBCConnector();

    /**
     *the driver, what used for connection to the database
     */
    @Configure
    private String driver;

    /**
     * the url of the database
     */
    @Configure
    private String url;

    /**
     * the user name of the database
     */
    @Configure
    private String username;

    /**
     * the password
     */
    @Configure
    private String password;

    /**
     * {@link Connection} instance for connect to the database
     */
    private Connection connection;

    /**
     * Get connection to the database
     *
     * @return
     *      {@link Connection} instance
     */
    public  Connection getConnection() {
        if (connection == null){
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                LOGGER.error("Error loading driver:" + e.toString());
            }catch (SQLException e){
                LOGGER.error("Error connecting to database:" + e.getMessage());
            }
        }
        return connection;
    }

    /**
     *  getters and setters for {@link JDBCConnector} instance parameters
     */

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static JDBCConnector getINSTANCE() {
        return INSTANCE;
    }
}

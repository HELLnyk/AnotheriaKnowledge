package dmexample;

import net.anotheria.anoprise.metafactory.Service;
import org.distributeme.annotation.DistributeMe;

/**
 * Remote interface for reverse string
 *
 * @author hellnyk
 */
@DistributeMe
public interface DistributeMeEchoServiceInterface extends Service {

    /**
     *
     * @param aString
     *      {@code String} instance for reverse
     * @return
     *      reversed {@code String} string
     */
    String echoString(String aString);
}

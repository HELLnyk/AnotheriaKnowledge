package com.hellnyk.echoservice.distributeme.echointerface;

import net.anotheria.anoprise.metafactory.Service;
import org.distributeme.annotation.DistributeMe;

/**
 * @author hellnyk
 */
@DistributeMe
public interface DistributeMeEchoServiceInterface extends Service {
    String echoString(String aString);
}

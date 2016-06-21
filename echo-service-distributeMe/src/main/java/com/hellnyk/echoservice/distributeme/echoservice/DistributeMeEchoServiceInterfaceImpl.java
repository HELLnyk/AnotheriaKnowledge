package com.hellnyk.echoservice.distributeme.echoservice;

import com.hellnyk.echoservice.distributeme.echointerface.DistributeMeEchoServiceInterface;

/**
 * @author hellnyk
 */
public class DistributeMeEchoServiceInterfaceImpl implements DistributeMeEchoServiceInterface {

    public String echoString(String aString) {
        printClientRequest(aString);
        StringBuilder stringBuilder = new StringBuilder();
        for (int indexOfLetter = aString.length() - 1; indexOfLetter >= 0; indexOfLetter--) {
            stringBuilder.append(aString.charAt(indexOfLetter));
        }
        return new String(stringBuilder);
    }

    private void printClientRequest(String clientString){
        System.out.println("String in client request: " + clientString);
    }
}

package service;

/**
 * Implementation of remote interface
 *
 * @author hellnyk
 */
public class RemoteEchoServiceImpl implements RemoteEchoServiceInterface {

    /**
     * default constructor
     */
    public RemoteEchoServiceImpl(){}


    @Override
    public String echoString(String aString){
        printClientRequest(aString);
        StringBuilder stringBuilder = new StringBuilder();
        for (int indexOfLetter = aString.length() - 1; indexOfLetter >= 0; indexOfLetter--) {
            stringBuilder.append(aString.charAt(indexOfLetter));
        }
        return new String(stringBuilder);
    }

    /**
     * print string, which was got from the client
     *
     * @param clientString
     *      string from the client
     */
    private void printClientRequest(String clientString){
        System.out.println("String in client request: " + clientString);
    }
}

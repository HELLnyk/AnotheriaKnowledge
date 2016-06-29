package dmexample;

/**
 * {@link DistributeMeEchoServiceInterface} implementation
 *
 * @author hellnyk
 */
public class DistributeMeEchoServiceInterfaceImpl implements DistributeMeEchoServiceInterface {

    @Override
    public String echoString(String aString) {
        printClientRequest(aString);
        StringBuilder stringBuilder = new StringBuilder();
        for (int indexOfLetter = aString.length() - 1; indexOfLetter >= 0; indexOfLetter--) {
            stringBuilder.append(aString.charAt(indexOfLetter));
        }
        return new String(stringBuilder);
    }

    /**
     * Output on the server side
     *
     * @param clientString
     *      parameter from the client
     */
    private void printClientRequest(String clientString){
        System.out.println("String in client request: " + clientString);
    }
}

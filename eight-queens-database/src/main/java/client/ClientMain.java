package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author hellnyk
 */
public abstract class ClientMain {

    /**
     * Default value for checking clients input
     */
    private static final int ZERO = 0;

    /**
     * default value which represents client choice of the operation which will be chosen first
     */
    private static final int FIRST_OPTION = 1;

    /**
     * default value which represents client choice of the operation which will be chosen second (it also means last)
     */
    private static final int SECOND_OPTION = 2;

    /**
     * {@link Logger} instance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);

    /**
     * {@link BufferedReader} instance, which reads the data received from the client
     */
    protected BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * get client response for writing or getting info from the database
     */
    protected void chooseTypeOfWork(){
        String dialogWithClient = "If you want calculate and write results into the database write 1, if" +
                "you want work with database - type 2";
        switch (enterOption(dialogWithClient)){
            case FIRST_OPTION:
                clientFillDatabase();
                break;
            case SECOND_OPTION:
                clientWorkDatabase();
                break;
        }
    }

    /**
     * get client`s choice between two variants
     *
     * @param forWhat
     *       name of options for the client
     * @return
     *      value of the selected option
     */
    protected int enterOption(String forWhat){
        int option;
        do{
            System.out.println(forWhat);
            option = correctValue();
            if(option != FIRST_OPTION && option != SECOND_OPTION){
                System.out.println("wrong input");
            }
        }while (option != FIRST_OPTION && option != SECOND_OPTION);

        return option;
    }

    /**
     * read client`s data ({@code int} value)
     *
     * @return
     *      value entered by the user, or {@code -1} if
     *      entered value does not contain a parsable integer.
     */
    private int correctValue(){
        int correctInteger;
        try {
            correctInteger = Integer.parseInt(bufReader.readLine());
        }catch (NumberFormatException e){
            correctInteger = -1;
        }catch (IOException e){
            LOGGER.error("error with process of reading value: " + e.getMessage());
            correctInteger = -1;
        }
        return correctInteger;
    }

    /**
     * read client`s value for request
     *
     * @param forWhat
     *      name of request
     * @return
     *      selected value
     */
    protected int enterValue(String forWhat){
        int value;
        do{
            System.out.println(forWhat);
            value = correctValue();
            if (value <= ZERO){
                System.out.println("Wrong input");
            }
        }while (value <= ZERO);
        return value;
    }

    /**
     * get client choice between operations with database
     */
    private void clientWorkDatabase(){
        String dialogClient = "Work with database, choice variant\n" +
                "1 - get all info from the database\n" +
                "2 - get info according to record in the database";

        switch (enterOption(dialogClient)){
            case FIRST_OPTION:
                getAll();
                break;
            case SECOND_OPTION:
                getIndex();
                break;
        }
    }

    /**
     * determines the client's wish to continue or quit
     */
    protected void setOptionContinueOfEnd(){
        String dialogClient = "Write 1 for continue and 2 for end";
        switch (enterOption(dialogClient)){
            case FIRST_OPTION:
                chooseTypeOfWork();
                break;
            case SECOND_OPTION:
                closeBuffer();
                break;
        }
    }

    /**
     * close {@link BufferedReader} instance
     */
    private void closeBuffer(){
        try {
            bufReader.close();
        } catch (IOException e) {
            LOGGER.error("Error with process of closing BufferReader instance: " + e.getMessage());
        }
    }

    /**
     *add info to the database
     */
    protected abstract void clientFillDatabase();

    /**
     * get all info from database
     */
    protected abstract void getAll();

    /**
     * get information(it means some entity) from the database using index of this record
     */
    protected abstract void getIndex();

    /**
     * set which type of connection will be used
     */
    protected abstract void initConnect();
}

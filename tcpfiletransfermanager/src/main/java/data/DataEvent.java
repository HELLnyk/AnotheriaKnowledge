package data;

import java.io.Serializable;

/**
 * Unit of data
 *
 * @author hellnyk
 */
public class DataEvent implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7408423763713307818L;

    /**
     * some parameters of POJO
     */
    private int id;
    private String name;

    /**
     * Default constructor for initialization parameters
     *
     * @param name
     * @param id
     */
    public DataEvent(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * getters and setters
     */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

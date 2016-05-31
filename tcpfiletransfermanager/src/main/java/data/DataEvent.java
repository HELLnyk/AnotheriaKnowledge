package data;

import java.io.Serializable;

/**
 * @author hellnyk
 */
public class DataEvent implements Serializable {


    private static final long serialVersionUID = -7408423763713307818L;

    private int id;
    private String name;

    public DataEvent(String name, int id) {
        this.name = name;
        this.id = id;
    }

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

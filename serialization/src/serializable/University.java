package serializable;

import java.io.Serializable;

public class University implements Serializable {

    private final String name;

    public University(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                '}';
    }

}

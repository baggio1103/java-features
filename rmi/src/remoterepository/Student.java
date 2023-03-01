package remoterepository;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;

    private Boolean registered;

    public Student(String name) {
        this.name = name;
        this.registered = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRegistered() {
        return registered;
    }

    public void isRegistered(Boolean registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", registered=" + registered +
                '}';
    }

}

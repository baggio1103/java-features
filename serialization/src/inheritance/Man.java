package inheritance;

import java.io.Serializable;

public class Man extends Person implements Serializable  {

    private static final long serialVersionUID = 1234L;

    private String id;

    private Gender gender;

    private String username;

    public Man(String id, Gender gender) {
        super(id, gender);
        this.id = id;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id='" + id + '\'' +
                ", gender=" + gender +
                '}';
    }

}

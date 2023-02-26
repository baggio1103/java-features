package inheritance;

public class Person {

    private String id;

    private Gender gender;


    public Person(String id, Gender gender) {
        this.id = id;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", gender=" + gender +
                '}';
    }
}

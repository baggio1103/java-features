package transactional;

public class Man implements Person {

    private final String name;

    private final Integer age;

    public Man(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String introduce(String name, Integer age) {
        return String.format("My name is %s and I am %d years old", name, age);
    }

    @Override
    public void greet(String name) {
        System.out.println("Hello " + name);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}

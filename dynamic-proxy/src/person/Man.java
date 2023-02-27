package person;

public class Man implements Person {

    private final String name;

    private final Integer age;

    public Man(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void introduce(String name, Integer age) {
        System.out.printf("My name is %s and I am %d years old%n", name, age);
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

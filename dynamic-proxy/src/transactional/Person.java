package transactional;

public interface Person {

    @TRANSACTIONAL
    String introduce(String name, Integer age);

    void greet(String name);

}

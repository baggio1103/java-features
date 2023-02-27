package transactional;

public interface Person {

    @TRANSACTIONAL
    void introduce(String name, Integer age);

    void greet(String name);

}

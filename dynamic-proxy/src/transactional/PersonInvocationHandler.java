package transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PersonInvocationHandler implements InvocationHandler {

    private final Person person;

    public PersonInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("Method with name: " + method.getName() + " is called");
        Arrays.stream(method.getDeclaringClass().getMethods()).filter(
                m -> m.getName().equals(method.getName())
        ).findAny().map(m -> method.getAnnotations()).map(Arrays::stream)
                .flatMap(annotationStream ->
                        annotationStream.filter(annotation ->
                                annotation.annotationType() == TRANSACTIONAL.class)
                                .findAny()).ifPresentOrElse(annotation -> {
                        runInTransaction(() -> {
                            try {
                                method.invoke(person, args);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });
                },
                () -> {
                    try {
                        System.out.println("Running in simple mode");
                        method.invoke(person, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        );
        System.out.println("--- Method: " + method.getName().toUpperCase() + " successfully executed ---");
        return proxy;
    }


    public  void runInTransaction(Task task) {
        System.out.println("Starting transaction...");
        try {
            task.run();
            System.out.println("Successfully executed");
        } catch (Exception exception) {
            System.out.println("Rolling back transaction");
        }
        System.out.println("Closing transaction...");
    }

}
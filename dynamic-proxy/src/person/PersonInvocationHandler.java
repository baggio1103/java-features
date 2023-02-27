package person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PersonInvocationHandler implements InvocationHandler {

    private final Person person;

    public PersonInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Method with name: " + method.getName() + " is called");
        method.invoke(person, args);
        System.out.println("--- Method " + method.getName() + " successfully executed ---");
        return proxy;
    }

}

package transactional;

import java.lang.reflect.Proxy;

public class Demo {

    public static void main(String[] args) {
        var man = new Man("Wayne", 22);
        var loader = man.getClass().getClassLoader();
        var interfaces = man.getClass().getInterfaces();
        var proxy = (Person) Proxy.newProxyInstance(loader, interfaces, new PersonInvocationHandler(man));
        proxy.introduce(man.getName(), man.getAge());
        proxy.greet("Tony");
    }

}

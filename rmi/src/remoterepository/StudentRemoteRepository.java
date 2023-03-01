package remoterepository;

import java.rmi.RemoteException;

public class StudentRemoteRepository implements Repository<Student> {

    @Override
    public Student persist(Student student) throws RemoteException {
        System.out.println("RMI object: student with name:" + student.getName() +
                " , registered: " + student.isRegistered());
        student.isRegistered(true);
        student.setName("@" + student.getName());
        System.out.println("Setting registered to True");
        return student;
    }

}

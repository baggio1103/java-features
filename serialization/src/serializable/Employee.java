package serializable;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 123L;

    public String name;

    private String email;

    private Integer age;

    private String username;

    private String position;

    private University university;

    public Employee() {

    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void mailCheck() {
        System.out.println("Mailing a check to " + name);
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", university=" + university +
                '}';
    }

}

# Serialization

---- 
### A sample project to illustrate what Serialization in Java

Serialization is the process of converting a Java object 
into a stream of bytes, so that it can be stored in disk, 
shared over network, etc. It has many use cases.
As soon as there is a need to use the object, we can easily 
restore the initial object from that stream of bytes that we converted
and stored in disk or received from the network. The process
of restoring an initial object from a stream of bytes is called
Deserialization.

A class to be serialized 
- needs to implement a **Serializable** interface  
  If a class does not implement a **Serializable** interface, the 
  program will throw **NotSerializableException**
  
- all the fields of a class must be Serializable. 
  For example, a class field is an instance of another class, that field 
  must be Serializable, otherwise an **NotSerializableException** 
  exception will be thrown. [Check out this example](/src/serializable/Employee.java)
  

There are cases when we don't want some fields to be
serialized. For example: 
- **static** fields are not serialized. Since static fields are 
initialized at the start of the execution, they are initialized
  before any instance of the class initialized.
  
- **transient** keyword is used to mark the fields 
  to be omitted while serialization. It tells the
  JVM that this field unnecessary to convert to bytes.

---- 

## Important notes

### SerialVersionUID
**SerialVersionUID** is a serial number that is given for 
each class during serialization. Each time when an object is
deserialized, the SerialVersionUIDs of the class 
and converted byte stream must match, otherwise
**InvalidClassException** exception is thrown. This is a common
scenario that happens, when we serialized some object and
overtime added a new field to the class. When deserialize the 
object, the SerialVersionUIDs mismatch and exception is thrown.

To overcome this issue, you need to define a variable - 
    serialVersionUID of type Long. After defining a serialVersionUID,
it is possible to add new fields to the class.

    public class Employee implements Serializable {

    private static final long serialVersionUID = 123L;

    public final String name;

    private String email;

    private Integer age;
    
    }

Then, when an object is deserialized,
the serialVersionUIDs of the class and the stream of bytes will match,
hence resulting in successful deserialization.

### Inheritance in Serialization

When a class with no superclasses is being serialized and deserialized, 
there is no limitation for the constructor.
It may have a default constructor and may not. It can have as many different
constructors as you wish. There are no issues with it.

However, there are issues when there is an inheritance.
If a class, that is to be serialized and deserialized,
has a superclass, and the superclass has some fields,
the superclass must have a default constructor, otherwise
an **InvalidClassException** exception will be thrown.



package Chapter07;

class Parent{

}

class Child extends Parent {

}


public class InstanceOfTest {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();

        System.out.println(parent instanceof Parent);       // true
        System.out.println(parent instanceof Child);        // false
        System.out.println(child instanceof Parent);        // true
        System.out.println(child instanceof Child);         // true
    }
}

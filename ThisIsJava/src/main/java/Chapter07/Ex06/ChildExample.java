package Chapter07.Ex06;

public class ChildExample {
    public static void main(String[] args) {
        Child child = new Child();

        /*
        출력 순서는 아래와 같음

        Parent(String nation) call
        Parent() call
        Child(String name) call
        Child() call
         */
    }
}

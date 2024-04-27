package Chapter13.BoxExample;

public class BoxExample {
    public static void main(String[] args) {
        Box<String> box1 = new Box<>();
        box1.set("Hello");
        String str = box1.get();

        Box<Integer> box2 = new Box<>();
        box2.set(6);                // 자동 Boxing
        int value = box2.get();     // 자동 Unboxing
    }
}

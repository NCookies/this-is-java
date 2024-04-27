package Chapter11.Object.DeepClone;

import java.util.Arrays;

public class Member implements Cloneable {
    public String id;
    public int age;
    public boolean adult;
    public int[] scores;
    public Car car;

    public Member(String id, int age, boolean adult, int[] scores, Car car) {
        this.id = id;
        this.age = age;
        this.adult = adult;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 얕은 복제 수행
        Member cloned = (Member) super.clone();

        // 깊은 복제 수행
        cloned.scores = Arrays.copyOf(this.scores, this.scores.length);
        cloned.car = new Car();

        // 깊은 복제된 Member 객체 리턴
        return cloned;
    }

    public Member getMember() {
        Member cloned = null;

        try {
            cloned = (Member) clone();  // 재정의된 clone() 메소드 호출
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return cloned;
    }
}

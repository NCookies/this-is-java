package Chapter11.Object.ThinClone;

public class Member implements Cloneable {
    public String id;
    public int age;
    public boolean adult;

    public Member(String id, int age, boolean adult) {
        this.id = id;
        this.age = age;
        this.adult = adult;
    }

    public Member getMember() {
        Member cloned = null;

        try {
            cloned = (Member) clone();  // clone() 메소드의 리턴 타입은 Object이므로 타입 캐스팅 해야함
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return cloned;
    }
}

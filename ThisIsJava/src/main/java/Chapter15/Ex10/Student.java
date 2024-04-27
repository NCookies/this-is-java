package Chapter15.Ex10;

import org.jetbrains.annotations.NotNull;

public class Student implements Comparable<Student> {
    public String id;
    public int score;

    public Student(String id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(@NotNull Student o) {
        return Integer.compare(score, o.score);
    }
}

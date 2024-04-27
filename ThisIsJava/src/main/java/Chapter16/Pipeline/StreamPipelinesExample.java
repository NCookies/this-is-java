package Chapter16.Pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPipelinesExample {
    public static void main(String[] args) {
        List<Member> list = Arrays.asList(
                new Member("홍길동", Member.MALE, 30),
                new Member("김나리", Member.FEMALE, 20),
                new Member("신용권", Member.MALE, 45),
                new Member("박수미", Member.FEMALE, 27)
        );

        Stream<Member> maleFemaleStream = list.stream();
        Stream<Member> maleStream = maleFemaleStream.filter(m -> m.getSex() == Member.MALE);
        IntStream ageStream = maleStream.mapToInt(Member::getAge);
        OptionalDouble optionalDouble = ageStream.average();
        double ageAvg_ = optionalDouble.getAsDouble();
        System.out.println("남자 평균 나이 : " + ageAvg_);

        double ageAvg = list.stream()
                .filter(m -> m.getSex() == Member.MALE)
                .mapToInt(Member::getAge)
                .average()
                .getAsDouble();

        System.out.println("남자 평균 나이 : " + ageAvg);
    }
}

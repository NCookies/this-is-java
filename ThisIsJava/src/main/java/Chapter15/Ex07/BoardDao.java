package Chapter15.Ex07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardDao {
    public List<Board> getBoardList() {
        List<Board> list = Arrays.asList(
                new Board("제목1", "내용1"),
                new Board("제목2", "내용2"),
                new Board("제목3", "내용3")
        );

        return list;
    }
}

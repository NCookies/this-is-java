package Chapter08.Ex04;

public class OracleDao implements DataAccessObject {
    private final String DB_NAME = "Oracle DB";

    @Override
    public void select() {
        System.out.println(DB_NAME + "에서 검색");
    }

    @Override
    public void insert() {
        System.out.println(DB_NAME + "에서 삽입");
    }

    @Override
    public void update() {
        System.out.println(DB_NAME + "에서 수정");
    }

    @Override
    public void delete() {
        System.out.println(DB_NAME + "에서 삭제");
    }
}

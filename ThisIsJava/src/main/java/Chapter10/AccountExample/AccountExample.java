package Chapter10.AccountExample;

public class AccountExample {
    public static void main(String[] args) {
        Account account = new Account();

        account.deposit(10000);
        System.out.println("예금액 : " + account.getBalance());

        try {
            account.withdraw(30000);    // 잔고가 출금액보다 적기 때문에 예외 발생
        } catch (BalanceInsufficientException e) {
            String message = e.getMessage();
            System.out.println(message);
            System.out.println("================================");
            e.printStackTrace();
        }
    }
}

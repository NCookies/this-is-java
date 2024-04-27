package Chapter06.Ex20;

import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

public class BankApplication {
    private static final int MAX_ACCOUNT_NUM = 100;
    private static final Account[] accountArray = new Account[MAX_ACCOUNT_NUM];
    private static final Scanner scanner = new Scanner(System.in);
    private static int accountNum = 0;

    public static void main(String[] args) {
        boolean run = true;
        
        while (run) {
            System.out.println("---------------------------------------------------");
            System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 종료");
            System.out.println("---------------------------------------------------");
            System.out.print("선택> ");

            int selectNo = scanner.nextInt();

            switch (selectNo) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    accountList();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    run = false;
                    break;
            }
        }

        System.out.println("프로그램 종료");
    }

    // 계좌 생성
    private static void createAccount() {
        String ano;
        String owner;
        int balance;

        if (accountNum >= MAX_ACCOUNT_NUM) {
            System.out.println("더 이상 계좌를 생성할 수 없습니다.");
            return;
        }

        System.out.println("------------------------");
        System.out.println("계좌생성");
        System.out.println("------------------------");

        scanner.nextLine();

        System.out.print("계좌번호 : ");
        ano = scanner.nextLine();
        System.out.print("계좌주 : ");
        owner = scanner.nextLine();
        System.out.print("초기입금액 : ");
        balance = scanner.nextInt();

        if (balance >= 0) {
            Account newAccount = new Account(ano, owner, balance);
            accountArray[accountNum] = newAccount;

            accountNum += 1;
        } else {
            System.out.println("게좌 생성에 실패했습니다.");
        }
    }
    
    // 계좌 목록보기
    private static void accountList() {
        scanner.nextLine();

        if (accountNum <= 0) {
            System.out.println("계좌가 존재하지 않습니다.");
            return;
        }

        for (int i = 0; i < accountNum; i++) {
            if (accountArray[i] == null) {
                break;
            }

            System.out.println("------------------------");
            System.out.println("계좌목록");
            System.out.println("------------------------");

            System.out.printf("%s\t %s\t %d\n",
                    accountArray[i].getAno(), accountArray[i].getOwner(), accountArray[i].getBalance());
        }
    }

    // 예금
    private static void deposit() {
        String depositAno;
        int depositMoney;

        System.out.println("------------------------");
        System.out.println("예금");
        System.out.println("------------------------");

        scanner.nextLine();

        System.out.print("계좌번호 : ");
        depositAno = scanner.nextLine();

        Account account = findAccount(depositAno);
        if (account == null) {
            System.out.println("계좌번호가 올바르지 않습니다.");
            return;
        }

        System.out.print("입금액 : ");
        depositMoney = scanner.nextInt();
        if (depositMoney <= 0) {
            System.out.println("예금액이 올바르지 않습니다.");
            return;
        }

        account.setBalance(account.getBalance() + depositMoney);
        System.out.println("결과: 예금이 성공되었습니다.");
    }

    // 출금
    private static void withdraw() {
        String withdrawAno;
        int withdrawMoney;

        scanner.nextLine();

        System.out.print("계좌번호 : ");
        withdrawAno = scanner.nextLine();

        Account account = findAccount(withdrawAno);
        if (account == null) {
            System.out.println("계좌번호가 올바르지 않습니다.");
            return;
        }
        
        System.out.print("출금액 : ");
        withdrawMoney = scanner.nextInt();
        if (withdrawMoney <= 0) {
            System.out.println("출금액이 올바르지 않습니다.");
            return;
        }

        account.setBalance(account.getBalance() - withdrawMoney);
        System.out.println("결과: 출금이 성공되었습니다.");
    }

    private static @Nullable Account findAccount(String no) {
        if (accountNum <= 0) {
            return null;
        }

        for (int i = 0; i < accountNum; i++) {
            if (accountArray[i] == null) {
                break;
            }
            if (no.equals(accountArray[i].getAno())) {
                return accountArray[i];
            }
        }

        return null;        // 계좌 못 찾으면
    }
}

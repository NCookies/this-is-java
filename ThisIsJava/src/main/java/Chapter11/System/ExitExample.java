package Chapter11.System;

public class ExitExample {
    public static void main(String[] args) {
        // 보안 관리자 설정
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                if (status != 5) {
                    throw new SecurityException();
                }
            }
        });

        for (int i = 0; i <= 5; i++) {
            System.out.println(i);

            try {
                // JVM 종료 요청
                System.exit(i);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}

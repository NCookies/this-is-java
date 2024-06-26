package Chapter12.ThreadPool.NoResult;

import java.util.concurrent.*;

public class NoResultExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i <= 10; i++) {
                    sum += i;
                }
                System.out.println("[처리 결과] " + sum);
            }
        };

        Future future = executorService.submit(runnable);

        try {
            future.get();
            System.out.println("[작업 처리 완료]");
        } catch (ExecutionException e) {
            System.out.println("[실행 예외 발생] " + e.getMessage());
            executorService.shutdown();
        } catch (InterruptedException e) {
            System.out.println();
        }
    }
}

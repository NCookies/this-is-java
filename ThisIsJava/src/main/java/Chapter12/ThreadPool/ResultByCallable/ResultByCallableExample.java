package Chapter12.ThreadPool.ResultByCallable;

import java.util.concurrent.*;

public class ResultByCallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");
        Callable<Integer> callable = new Callable() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i <= 10; i++) {
                    sum += i;
                }

                return sum;
            }
        };
        Future<Integer> future = executorService.submit(callable);

        try {
            int sum = future.get();
            System.out.println("[처리 결과] " + sum);
            System.out.println("[작업 처리 완료]");
        } catch (ExecutionException e) {
            System.out.println("[실행 예외 발생] " + e.getMessage());
            executorService.shutdown();
        } catch (InterruptedException e) {
            System.out.println();
        }
    }
}

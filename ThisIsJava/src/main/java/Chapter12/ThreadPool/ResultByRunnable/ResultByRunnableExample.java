package Chapter12.ThreadPool.ResultByRunnable;

import java.util.concurrent.*;

public class ResultByRunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");
        // 작업 정의
        class Task implements Runnable {
            Result result;
            Task(Result result) {
                this.result = result;
            }

            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i <= 10; i++) {
                    sum += i;
                }
                result.addValue(sum);
            }
        }

        // 두 가지 작업 처리를 요청
        Result result = new Result();
        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);
        Future<Result> future1 = executorService.submit(task1, result);
        Future<Result> future2 = executorService.submit(task2, result);

        // 두 가지 작업 결과를 취합
        try {
            result = future1.get();
            result = future2.get();

            System.out.println("[처리 결과] " + result.accumValue);
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[실행 예외 발생] " + e.getMessage());
        }

        executorService.shutdown();
    }
}

// 처리 결과를 저장하는 Result 클래스
class Result {
    int accumValue;
    synchronized void addValue(int value) {
        accumValue += value;
    }
}
package Chapter12.ControlState.WaitNotifyExample;

public class WorkObject {
    public synchronized void methodA() {
        System.out.println("ThreadA의 methodA 작업 실행");
//        System.out.println(Thread.currentThread().getName());
        notify();

        try {
            wait();
        } catch (InterruptedException e) {

        }
    }

    public synchronized void methodB() {
        System.out.println("ThreadB의 methodB 작업 실행");
        notify();

        try {
            wait();
        } catch (InterruptedException e) {

        }
    }
}

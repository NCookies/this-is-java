package Chapter12.ThreadGroup.ThreadInfo;

import java.util.Map;
import java.util.Set;

public class ThreadInfoExample {
    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setName("AutoSaveThread");
        autoSaveThread.setDaemon(true);     // 데몬 스레드 설정
        autoSaveThread.start();

        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        Set<Thread> threads = map.keySet();
        for (Thread thread : threads) {
            System.out.println("Name : " + thread.getName() + ((thread.isDaemon()) ? "(데몬)" : "(주)"));
            System.out.println("\t" + "소속그룹 : " + thread.getThreadGroup().getName());       // 스레드 그룹 이름 출력
            System.out.println();
        }
    }
}

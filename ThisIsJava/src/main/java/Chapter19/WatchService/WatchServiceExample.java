package Chapter19.WatchService;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class WatchServiceExample {
    static class WatchServiceThread extends Thread {
        @Override
        public void run() {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path directory = Paths.get("C:\\Temp");
                directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, 
                        StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                
                while (true) {
                    WatchKey watchKey = watchService.take();    // 블로킹(Watchkey가 큐에 들어올 때까지)
                    List<WatchEvent<?>> list = watchKey.pollEvents();   // WatchEvent 목록 얻기

                    for (WatchEvent watchEvent : list) {
                        // 이벤트 종류 얻기
                        WatchEvent.Kind kind = watchEvent.kind();
                        // 감지된 Path 얻기
                        Path path = (Path) watchEvent.context();
                        if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                            System.out.println("파일 생성됨 -> " + path.getFileName());
                        } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                            System.out.println("피일 삭제됨 -> " + path.getFileName());
                        } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                            System.out.println("피일 변경됨 -> " + path.getFileName());
                        } else if (kind == StandardWatchEventKinds.OVERFLOW) {

                        }

                        boolean valid = watchKey.reset();
                        if (!valid) {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        WatchServiceThread wst = new WatchServiceThread();
        wst.start();
    }
}

package threadcoreknowledge.createthreads;

/**
 * @author: wjy
 * @date: 2020/2/18
 * @description: 创建100个线程，用任务管理器可以看到Java线程数量的变化。
 */
public class Create100Threads {
    
    public static void main(String[] args) {
        // 新建100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 睡眠10s
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

package threadcoreknowledge.stopthread;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 如果while里面放try/catch，会导致中断失效。
 * sleep函数：一旦响应中断便会将线程的标记位清除，所以for循环的判断条件中检查不到程序被中断的信号，程序无法停止。
 */
public class CantInterrupt implements Runnable {
    
    @Override
    public void run() {
        // 即便检查了程序是否已经被中断，程序还是停止不了。
        for (int i = 0; i <= 10000 && !Thread.currentThread().isInterrupted(); i++) {
            if (i % 100 == 0) {
                System.out.println(i + "是100的倍数");
            }
            // 抛出的异常被catch住，又开始进行下一轮循环。
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new CantInterrupt());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

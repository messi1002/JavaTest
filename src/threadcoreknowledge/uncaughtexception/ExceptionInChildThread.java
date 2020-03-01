package threadcoreknowledge.uncaughtexception;

/**
 * @author: wjy
 * @date: 2020/2/29
 * @description: 单线程情况下，抛出异常时会打印出异常堆栈(抛出异常，程序停止运行)。
 * 多线程情况下，子线程如果发生异常，会有什么不同？(虽然子线程也会抛出异常，但是主线程丝毫不受影响，很难发现子线程的异常信息)
 */
public class ExceptionInChildThread implements Runnable {
    
    @Override
    public void run() {
        throw new RuntimeException();
    }
    
    public static void main(String[] args) {
        Thread thread = new Thread(new ExceptionInChildThread());
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}

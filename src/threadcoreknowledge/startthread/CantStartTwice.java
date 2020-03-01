package threadcoreknowledge.startthread;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 演不能两次调用start方法，否则会抛出异常。
 */
public class CantStartTwice implements Runnable {
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new CantStartTwice());
        thread.start();
        thread.start();
    }
}

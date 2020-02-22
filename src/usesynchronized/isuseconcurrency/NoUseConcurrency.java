package usesynchronized.isuseconcurrency;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 不使用并发手段，建立两个线程一起a++。
 */
public class NoUseConcurrency implements Runnable {
    static NoUseConcurrency instance = new NoUseConcurrency();
    static int a = 0;
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        // 确保主线程等待两个子线程都执行完成后再输出a。
        t1.join();
        t2.join();
        System.out.println(a);
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            a++;
        }
    }
}

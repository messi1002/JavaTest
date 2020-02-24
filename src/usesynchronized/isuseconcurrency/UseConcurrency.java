package usesynchronized.isuseconcurrency;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 使用并发手段，建立两个线程一起执行a++。
 */
public class UseConcurrency implements Runnable {
    static UseConcurrency instance = new UseConcurrency();
    static int a = 0;
    
    public static void main(String[] args) throws InterruptedException {
        // 建立两个线程，利用同一个实例，共用实例中的方法。
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        // 启动两个线程
        t1.start();
        t2.start();
        // 让主线程等待两个子线程都执行完成
        t1.join();
        t2.join();
        System.out.println(a);
    }
    
    // 对象锁-方法锁
    // 将关键字加在普通方法上
    @Override
    public synchronized void run() {
        for (int i = 0; i < 100000; i++) {
            a++;
        }
    }
}

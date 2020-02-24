package usesynchronized.isuseconcurrency;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 不使用并发手段，建立两个线程一起执行a++。
 */
public class NoUseConcurrency implements Runnable {
    // 创建类的实例
    static NoUseConcurrency instance = new NoUseConcurrency();
    // 初始化a的值
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
    
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            a++;
        }
    }
}

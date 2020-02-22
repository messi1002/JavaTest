package usesynchronized.isuseconcurrency;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description:
 */
public class UseConcurrency implements Runnable {
    static UseConcurrency instance = new UseConcurrency();
    static int a = 0;
    
//    方法锁
//    @Override
//    public synchronized void run() {
//        for (int i = 0; i < 10000; i++) {
//            a++;
//        }
//    }

//    同步代码块锁
//    @Override
//    public void run() {
//        synchronized (this) {
//            for (int i = 0; i < 10000; i++) {
//                a++;
//            }
//        }
//    }

//    Class对象锁
//    @Override
//    public void run() {
//        synchronized (UseConcurrency.class) {
//            for (int i = 0; i < 100000; i++) {
//                a++;
//            }
//        }
//    }
    
    // 重写的方法不能加static关键字
    @Override
    public void run() {
        method();
    }
    
    // 静态锁
    private synchronized void method() {
        for (int i = 0; i < 100000; i++) {
            a++;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a);
    }
}

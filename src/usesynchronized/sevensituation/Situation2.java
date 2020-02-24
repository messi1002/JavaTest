package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 两个线程访问的是两个对象的同步方法
 */
public class Situation2 implements Runnable {
    static Situation2 instance1 = new Situation2();
    static Situation2 instance2 = new Situation2();
    
    @Override
    public void run() {
        method();
    }
    
    private synchronized void method() {
        System.out.println(Thread.currentThread().getName() + "开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行");
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("Situation2.class finished");
    }
}

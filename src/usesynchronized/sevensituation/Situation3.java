package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 两个线程访问的是synchronized的静态方法
 */
public class Situation3 implements Runnable {
    static Situation3 instance1 = new Situation3();
    static Situation3 instance2 = new Situation3();
    
    @Override
    public void run() {
        method();
    }
    
    private static synchronized void method() {
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
        System.out.println("Situation3.class finished");
    }
}

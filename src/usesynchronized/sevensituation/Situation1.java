package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 两个线程同时访问一个对象的同步方法
 */
public class Situation1 implements Runnable {
    static Situation1 instance = new Situation1();
    
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
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("Situation1.class finished");
    }
}

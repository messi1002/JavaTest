package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 方法抛出异常后会释放锁
 * 展示不抛出异常前和抛出异常后的对比: 一旦第一个线程抛出异常，第二个线程会立刻进入同步方法，意味着锁已经释放。
 */
public class Situation7 implements Runnable {
    static Situation7 instance = new Situation7();
    
    @Override
    public void run() {
        method();
    }
    
    public synchronized void method() {
        System.out.println(Thread.currentThread().getName() + "开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 方法抛出异常后，JVM帮我们释放了锁，不需要手动释放锁。
        // 抛出运行时异常，不强制要求捕获。
        throw new RuntimeException();
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("Situation7.class finished");
    }
}

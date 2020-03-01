package threadcoreknowledge.createthreads;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 同时使用Runnable和Thread两种创建线程的方式
 */
public class BothRunnableThread {
    
    public static void main(String[] args) {
        // 创建匿名内部类
        // 传入了Runnable对象
        new Thread(new Runnable() {
            // Thread类中本身的run方法被覆盖，无法运行。
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            // 在匿名内部类中重写了run方法，即重写了Thread类的方法。
            // 并且在创建Thread类的同时传入了Runnable对象，覆盖了Thread类中本身的run方法。
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
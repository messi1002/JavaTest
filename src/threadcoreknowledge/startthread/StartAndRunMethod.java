package threadcoreknowledge.startthread;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 对比start和run两种启动线程的方式
 */
public class StartAndRunMethod implements Runnable {
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
    
    public static void main(String[] args) {
        StartAndRunMethod s = new StartAndRunMethod();
        // 主线程执行run方法
        s.run();
        // 新建子线程执行run方法
        new Thread(s).start();
    }
}

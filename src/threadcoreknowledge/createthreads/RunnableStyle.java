package threadcoreknowledge.createthreads;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 用Runnable方式创建线程
 */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("用Runnable方法创建线程");
    }
    
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}

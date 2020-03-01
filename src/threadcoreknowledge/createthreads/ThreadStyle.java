package threadcoreknowledge.createthreads;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 用Thread方式创建线程
 */
public class ThreadStyle extends Thread {
    
    @Override
    public void run() {
        System.out.println("用Thread方式创建线程");
    }
    
    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        threadStyle.start();
    }
}

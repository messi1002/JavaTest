package thread;

/**
 * @author: wjy
 * @date: 2020/2/18
 * @description: 即使在代码中不显式创建线程，在运行main函数时，JVM也会自动地启动其他的线程。
 * 并且这些线程都有特定的含义和作用。
 */
public class JavaAndThreads {
    
    public static void main(String[] args) {
        System.out.println("Hello Threads!");
    }
}

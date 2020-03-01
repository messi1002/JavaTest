package threadcoreknowledge.wrongways;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: lambda表达式创建线程
 */
public class LambdaDemo {
    
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}

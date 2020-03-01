package threadcoreknowledge.wrongways;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 用匿名内部类创建线程
 */
public class AnonymousInnerClassDemo {
    
    public static void main(String[] args) {
        // 第一种方式
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        
        // 第二种方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}

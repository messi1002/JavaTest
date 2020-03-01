package threadcoreknowledge.stopthread;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 最佳实践1: catch了InterruptedException之后优先选择在方法签名中抛出异常。
 * 那么在run方法中就会对异常强制try/catch。
 * run无法抛出checked Exception，只能用try/catch。
 * 因为它是重写了别的方法，它的父类方法没有做任何异常抛出，所以重写不能改变它对异常的相关定义。
 */
public class RightWayStopThreadInProd1 implements Runnable {

    @Override
    public void run() {
//        // 即便每次判断线程是否被中断，我们也无法得到正确信息，因为sleep会清除标记位。
//        while (true && !Thread.currentThread().isInterrupted()) {
//            System.out.println("go");
//            // 相当于这个方法把中断吞了(因为它仅仅打印了中断信息。没有做其他的处理)。
//            // 这个方法应该把中断信息上报给我们。
//            throwInMethod();
//        }
    
        try {
            while (true) {
                System.out.println("go");
                throwInMethod();
            }
        } catch (InterruptedException e) {
            // 保存日志、停止程序
            e.printStackTrace();
        }
    }
    
    private void throwInMethod() throws InterruptedException {
        // 不好的做法: 直接try-catch
//        // 把异常处理在很低级的层次，即子方法中，而run函数是较高的层次。
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.sleep(2000);
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd1());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

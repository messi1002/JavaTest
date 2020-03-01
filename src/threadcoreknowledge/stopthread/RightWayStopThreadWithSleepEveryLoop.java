package threadcoreknowledge.stopthread;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 如果在执行过程中，每次循环都会调用sleep或wait等方法，那么则不需要每次迭代都检测是否已经中断，因为sleep过程中会响应中断。
 */
public class RightWayStopThreadWithSleepEveryLoop implements Runnable {

    @Override
    public void run() {
        // 对于每次迭代都会有让线程进入阻塞一段时间的情况，不需要在每次迭代的判断条件中判断线程是否被中断。
        // 因为我们会在中断过程中检测到中断状态并且抛出异常。
        try {
            for (int i = 0; i <= 10000; i++) {
                if (i % 100 == 0) {
                    System.out.println(i + "是100的倍数");
                }
                // 因为sleep过程中会响应中断。
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithSleepEveryLoop());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

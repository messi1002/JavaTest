package threadcoreknowledge.threadsafety;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 初始化未完毕，就this赋值。
 */
public class ThreadError32 {
    static Point point;
    
    public static void main(String[] args) throws InterruptedException {
        // 在后台初始化point
        new PointMaker().start();
        // 打印出(1,0)
        Thread.sleep(20);
        // 打印出(1,1)
//        Thread.sleep(200);
        if (point != null) {
            System.out.println(point);
        }
    }
}

class Point {
    private final int x, y;
    
    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        // this是一个point实例
        // 过早地把point发布出来了，导致不同时刻的x,y值是不一样的。
        // point一旦发布，就应该是一个固定的对象。
        ThreadError32.point = this;
        Thread.sleep(100);
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

class PointMaker extends Thread {
    
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
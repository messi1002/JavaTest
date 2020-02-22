package usesynchronized.reentrant;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 可重入粒度测试1——递归调用本方法
 */
public class ReentrantGranularityTest1 {
    int a = 0;
    
    public static void main(String[] args) {
        ReentrantGranularityTest1 test1 = new ReentrantGranularityTest1();
        test1.method();
    }
    
    private synchronized void method() {
        System.out.println("a = " + a);
        if (a == 0) {
            a++;
            method();
        }
    }
}

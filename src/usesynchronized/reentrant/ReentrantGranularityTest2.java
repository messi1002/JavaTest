package usesynchronized.reentrant;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 可重入粒度测试2——调用类内其他的方法
 */
public class ReentrantGranularityTest2 {
    
    
    private synchronized void method1() {
        System.out.println("我是方法一");
        method2();
    }
    private synchronized void method2() {
        System.out.println("我是方法二");
    }
    public static void main(String[] args) {
        ReentrantGranularityTest2 test2 = new ReentrantGranularityTest2();
        test2.method1();
    }
}

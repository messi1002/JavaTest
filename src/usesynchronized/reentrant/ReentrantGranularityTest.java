package usesynchronized.reentrant;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 可重入粒度测试3——父类
 */
public class ReentrantGranularityTest {
    public synchronized void doSomething() {
        System.out.println("我是父类方法");
    }
}

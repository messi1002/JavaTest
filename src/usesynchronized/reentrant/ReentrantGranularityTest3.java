package usesynchronized.reentrant;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 可重入粒度测试3——调用父类的方法
 */
public class ReentrantGranularityTest3 extends ReentrantGranularityTest {
    
    /**
     * 功能描述: 重写父类方法
     *
     * @param: []
     * @return: void
     * @auther: wjy
     * @date: 2020/2/20 22:30
     */
    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类方法");
        // 调用父类方法
        super.doSomething();
    }
    
    public static void main(String[] args) {
        ReentrantGranularityTest3 test3 = new ReentrantGranularityTest3();
        // 执行子类方法
        test3.doSomething();
    }
}

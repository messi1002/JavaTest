package threadcoreknowledge.threadsafety;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 在构造函数中新建线程
 * 很难避免，比如在构造函数中要拿到一个数据库的连接池或者线程池的引用。
 * 而创建连接池进行连接时，往往都是在后台新开线程的，但是我们察觉不到，以为只是调用它的构造函数。
 */
public class ThreadError34 {
    
    private Map<String, String> map;
    
    public ThreadError34() {
        // 在子线程的操作需要时间完成，而一旦构造函数执行完start之后，就认为对象已经创建好了，而不管其中的子线程有没有执行完毕。
        // 可能会报空指针异常
        // 不应该在构造函数中用新开线程的方式进行初始化的工作。
        new Thread(new Runnable() {
            @Override
            public void run() {
                map = new HashMap<>();
                map.put("1", "周一");
                map.put("2", "周二");
                map.put("3", "周三");
                map.put("4", "周四");
            }
        }).start();
    }
    
    public static void main(String[] args) {
        ThreadError34 threadError34 = new ThreadError34();
        // 空指针异常
        System.out.println(threadError34.map.get("1"));
    }
}

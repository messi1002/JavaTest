package threadcoreknowledge.threadsafety;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 发布逸出
 */
public class ThreadError31 {
    
    // 只想被读取，不想被外部修改。
    private Map<String, String> map;
    
    public ThreadError31() {
        map = new HashMap<>();
        map.put("1", "周一");
        map.put("2", "周二");
        map.put("3", "周三");
        map.put("4", "周四");
    }
    
    // 逸出
    public Map<String, String> getMap() {
        // 不小心把map发布出去了，导致外部可以修改。
        return map;
    }
    
    // 解决这个问题(返回副本)
    public Map<String, String> getMapImproved() {
        return new HashMap<>(map);
    }
    
    public static void main(String[] args) {
        ThreadError31 threadError3 = new ThreadError31();
//        Map<String, String> map = threadError3.getMap();
//        System.out.println(map.get("1"));
//        map.remove("1");
//        System.out.println(map.get("1"));
    
        System.out.println(threadError3.getMapImproved().get("1"));
        threadError3.getMapImproved().remove("1");
        System.out.println(threadError3.getMapImproved().get("1"));
    }
}

/**
 * 懒汉式
 */
public class Demo1 {
    private final static Demo1 demo1 = new Demo1();
    public static Demo1 getInstance(){
        return demo1;
    }
    private Demo1(){
        System.out.println("init");
    }
}

/**
 * 饿汉式
 */
class Demo2{
    private static Demo2 demo2;
    public static synchronized Demo2 getInstance(){
        return demo2==null?demo2=new Demo2():demo2;
    }
    private Demo2(){}
}

/**
 * 静态内部类
 */
class Demo3{
    private Demo3(){}
    private static class Instance{
       private static final Demo3 DEMO_3 = new Demo3();
    }
    public static Demo3 getInstance(){
        return Instance.DEMO_3;
    }
}
/**
 * 枚举
 */
enum Demo4{
    INSTANCE;
    public void whateverMethod() {
    }
}

/**
 * 双重校验锁
 */
class Demo5{
    private Demo5(){}
    private static volatile Demo5 DEMO_5 ;
    public static Demo5 getInstance(){
        if(DEMO_5==null){
            synchronized (Demo5.class){
                if(DEMO_5==null){
                    DEMO_5=new Demo5();
                }
            }
        }
        return DEMO_5;
    }
}
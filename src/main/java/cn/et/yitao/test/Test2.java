package cn.et.yitao.test;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年11月05日 上午 10:03
 */
public class Test2 {
    static class demo{
        //静态内部类中final修饰的变量(Integer类型)
        static final Integer DEMO_AS = 1;
        //静态内部类中的静态代码块
        static {
            System.out.println("demo" + DEMO_AS);
        }
    }
    //外部类中的静态代码块
    static {
        System.out.println("test" + demo.DEMO_AS);
    }
    public static void main(String[] args) {

    }
}

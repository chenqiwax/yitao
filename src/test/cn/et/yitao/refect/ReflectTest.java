package cn.et.yitao.refect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import cn.et.yitao.user.bean.Student;
/**
 * @ClassName: ReflectTest
 * @author   刘双平
 * @date 2018年5月17日 下午3:14:43
 * @Description: TODO(反射测试类)
 */
public class ReflectTest {
	public static void main(String[] args) {
		try {
			//获取Student类的反射类对象 Class 对象
			Class c=Class.forName("com.lsp.tools.Student");
			ReflectTest rt=new ReflectTest();
//			rt.getConstructor(c);
			rt.updateField(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 获取构造方法
	 */
	public void getConstructor(Class c) throws Exception{
		//获取类的构造方法
//		   Constructor[]ct= c.getConstructors();
//		   for(Constructor ct2:ct){
//			   System.out.println(ct2.toString());
//		   }
//		   //通过反射得到要反射的类的对象
//		   Student stu=(Student)c.newInstance();
//		   stu.eat();
//		   stu.goToClass("张三");


		//通过无参构造方法创建类
//		   Constructor constructor=c.getConstructor();
//	       Student stu=(Student)c.newInstance();
//	       System.out.println(stu.toString());

		//通过有参的构造方法创建对象
/*		Constructor constructor2=c.getConstructor(new Class[]{String.class,int.class,String.class});
		Student stu2=(Student) constructor2.newInstance(new Object[]{"zhangsan",22,"008"});
		stu2.goToClass(stu2.name);
		System.out.println(stu2.name);*/
	}

	public void updateField(Class c) throws Exception{
		/*Constructor constructor2=c.getConstructor(new Class[]{String.class,int.class,String.class});

		Student stu2=(Student) constructor2.newInstance(new Object[]{"zhangsan",22,"008"});
		stu2.goToClass(stu2.name);
		stu2.setHeight(188);
		System.out.println(stu2.getHeight());
		System.out.println("========");
		//通过属性名得到属性对象
		Field f=c.getDeclaredField("height");
		//设置属性的访问权限
		f.setAccessible(true);
		//设置属性的值
		f.set(stu2, 199);
		//获取属性的值参数的获取的那个对象的属性值
		System.out.println(f.get(stu2));
		System.out.println(stu2.getHeight());*/
		//通过调用私有属性的公共方法来获取属性值
//	      System.out.println(stu2.getS());
	}
}

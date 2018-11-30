package cn.et.yitao.refect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

import cn.et.yitao.user.bean.Student;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Main {
	static int a = 1;
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document read = reader.read(new File("F:\\woker\\chenqi\\work\\web\\yitao\\src\\test\\cn\\et\\yitao\\refect\\studentlist.xml"));
		Element rootElement = read.getRootElement();
		// 打印根节点的名称
		System.out.println(rootElement.getName());
		Iterator a = rootElement.elementIterator();
		ArrayList<Student> students = new ArrayList<>();
		while (a.hasNext()) {
			Element e = (Element) a.next();
			System.out.println(e.getName());
			Iterator b = e.elementIterator();
			while (b.hasNext()) {
				Element et = (Element) b.next();
				System.out.println(et.getName());
				String value = et.getStringValue();
				System.out.println(">>>"+value);
				Class forName = Class.forName(value);
				Constructor constructor;
				constructor = forName.getConstructor(new Class[] { String.class, Integer.class, String.class,
						String.class, String.class, String.class });
				Student newInstance = (Student) constructor
						.newInstance(new Object[] { "张三", 15, "男", "01", "广东.深圳", "1802" });
				System.out.println(newInstance);
				students.add(newInstance);
			}
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("students.txt")));
		oos.writeObject(students);
		oos.close();
	}
}

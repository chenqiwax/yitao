package cn.et.yitao.refect;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @ClassName: Dom4jTest
 * @author 刘双平
 * @date 2018年5月19日 下午4:12:51
 * @Description: TODO(这里用一句话描述这个类的作用)
 */
public class Dom4jTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// 接通读取文件的管道
		SAXReader reader = new SAXReader();
		// Document是文档对象
		Document document = reader.read(new File("F:\\javaWorkspace\\Demo001\\src\\com\\lsp\\d1703\\vehicles.xml"));
		// 获取文档的根节点
		Element rootElement = document.getRootElement();
		// 打印根节点的名称
		System.out.println("根节点名称为：" + rootElement.getName());
		// 获取根节点下的所有子节点
		Iterator i = rootElement.elementIterator();
		// 循环获取子节点
		for (; i.hasNext();) {
			// 得到每一个子节点Element
			Element element = (Element) i.next();
			// 获取当前节点的名字
			System.out.println(element.getName());
			// 获取当前节点的所有属性，得到属性迭代器
			Iterator j = element.attributeIterator();
			for (; j.hasNext();) {
				Attribute attribute = (Attribute) j.next();
				System.out.println(attribute.getName() + "-" + attribute.getValue());
			}
			// 获取当前节点的子节点
			Iterator z = element.elementIterator();
			for (; z.hasNext();) {
				Element pe = (Element) z.next();
				System.out.println(pe.getName());
				// 获取当前节点的属性
				Iterator k = pe.attributeIterator();
				for (; k.hasNext();) {
					Attribute pa = (Attribute) k.next();
					System.out.println(pa.getName() + "==" + pa.getValue());
				}
				// 获取一对节点中间的文本值
				System.out.println("获取属性的值" + pe.getStringValue());
			}

		}
	}

}
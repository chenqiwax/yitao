package cn.et.yitao.refect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("students.txt")));

	}
}

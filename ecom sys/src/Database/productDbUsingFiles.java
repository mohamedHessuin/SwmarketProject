package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import Database.*;
import Datatypes.*;
import SystemBehavioralPatterns.databaseIterator;
import Controllers.*;
import SystemBehavioralPatterns.*;

public class productDbUsingFiles implements productDatabase {

	private int count = 0;
	private String filePath = "C:\\Users\\lenovo\\Desktop\\ecom2\\fileDatabase\\product.txt";
	private String tmp = "C:\\Users\\lenovo\\Desktop\\ecom2\\fileDatabase\\tmp.txt";
	private databaseIterator baseIter;

	private static productDbUsingFiles singletonObj;

	private productDbUsingFiles() {
	}

	public static productDbUsingFiles getInstance() {
		if (singletonObj == null) {
			singletonObj = new productDbUsingFiles();
		}
		return singletonObj;
	}

	public boolean checkExistence(Object ob) {
		product us = (product) ob;
		product ad1 = new product();
		String category = us.getType();
		String brandname = us.getBrandname();
		baseIter = new databaseIterator();
		count = baseIter.setcounter(filePath);
		baseIter = new databaseIterator(filePath, count);
		while (baseIter.hasNext()) {
			ad1 = (product) baseIter.next();
			String currproducttype = ad1.getType();
			String currbrandname = ad1.getBrandname();
			if (currproducttype.equals(category) && currbrandname.equals(brandname)) {
				return true;
			}

		}

		return false;

	}

	public void printfortest() {
		product ad1 = new product();
		String nam;
		String brandname;
		int count = 1;
		baseIter = new databaseIterator(filePath, count);
		while (baseIter.hasNext()) {
			ad1 = (product) baseIter.next();
			nam = ad1.getName();
			brandname = ad1.getBrandname();
			System.out.println(count + "one.........................");
			System.out.println(nam);
			System.out.println(brandname);
			count++;
		}

	}

	public void addToDatabase(Object ob) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath, true);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ob);
			oos.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void ObjectToFile(Object serObj, String loc) {
		FileOutputStream fos;
		try {

			fos = new FileOutputStream(loc, true);
			ObjectOutputStream objectOut = new ObjectOutputStream(fos);
			objectOut.writeObject(serObj);
			objectOut.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void clear(String loc) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(loc);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getIndexOfObjectInFile(Object ob) {
		int i = 0;
		baseIter = new databaseIterator();
		count = baseIter.setcounter(filePath);

		product a = new product();
		baseIter = new databaseIterator(filePath, count);
		while (baseIter.hasNext()) {
			a = (product) baseIter.next();
			i++;

		}
		return i;
	}

	public void removeFromDatabase(int index) {
		int j = 1;
		product ss = new product();
		baseIter = new databaseIterator();
		count = baseIter.setcounter(filePath);
		baseIter = new databaseIterator(filePath, count);
		while (baseIter.hasNext()) {
			ss = (product) baseIter.next();
			if (j != index) {
				this.ObjectToFile(ss, tmp);
			}
			j++;
		}
		count = baseIter.setcounter(filePath);
		this.clear(filePath);
		baseIter = new databaseIterator(tmp, count);
		while (baseIter.hasNext()) {
			ss = (product) baseIter.next();
			ObjectToFile(ss, filePath);
		}

		this.clear(tmp);

	}

	public void ModifyObjectInDatabase(int index, Object p) {
		int j = 1;
		product ss = new product();
		baseIter = new databaseIterator();
		count = baseIter.setcounter(filePath);
		baseIter = new databaseIterator(filePath, count);
		while (baseIter.hasNext()) {
			ss = (product) baseIter.next();
			if (j == index) {
				this.ObjectToFile((Admin) p, tmp);
			} else {
				this.ObjectToFile(ss, tmp);
			}
			j++;
		}
		baseIter = new databaseIterator();
		count = baseIter.setcounter(filePath);
		this.clear(filePath);
		baseIter = new databaseIterator(tmp, count);
		while (baseIter.hasNext()) {
			ss = (product) baseIter.next();
			ObjectToFile(ss, filePath);
		}

		this.clear(tmp);

	}

}

package com.wayne.io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest {

	public static void main(String[] args) {
		String path = "D:/test.txt";
//		System.out.println(path.indexOf('\u0000') < 0);
		
		File file = new File(path);
		try {
			
			System.out.println("absolutePath:"+file.getAbsolutePath());
			System.out.println("name:"+file.getName());
			System.out.println("canExecute:"+file.canExecute());
			System.out.println("canRead:"+file.canRead());
			System.out.println("canWrite:"+file.canWrite());
			System.out.println("isAbsolute:"+file.isAbsolute());
			System.out.println("lastModified:"+file.lastModified());
			System.out.println("canonicalPath:"+file.getCanonicalPath());
			System.out.println("freeSpace:"+file.getFreeSpace());
			System.out.println("parent:"+file.getParent());
			System.out.println("path:"+file.getPath());
			System.out.println("totalSpace:"+file.getTotalSpace());
			System.out.println("usableSpace:"+file.getUsableSpace());
			System.out.println("length:"+file.length());
			System.out.println("exists:"+file.exists());
			System.out.println("setLastModified:"+file.setLastModified(new Date().getTime()));
			System.out.println("isHidden:"+file.isHidden());
			System.out.println("URI:"+file.toURI());
			
//			System.out.println(file.createNewFile());
//			file = new File("D:/test/test");
//			System.out.println(file.mkdir());
//			file = new File("D:/test/test");
//			System.out.println(file.mkdirs());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}

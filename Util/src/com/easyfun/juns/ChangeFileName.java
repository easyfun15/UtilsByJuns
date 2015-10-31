package com.easyfun.juns;

import java.io.File;
import java.util.Scanner;

/***
 * 
 * class ChangeFileName used to handle change file name, name of automatic
 * sorting.Need to enter two parameters Parameters 1 used to represent the name
 * of the prefix, 2 used to modify the file path
 * 
 * @author juns
 * 
 * @time 2015-10-31
 */
public class ChangeFileName {

	public static String prefix;
	public static String filePathName;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		prefix = scanner.next();
		filePathName = scanner.next();

		File file = new File(filePathName);
		if (file.exists()) {
			if (file.isDirectory()) {
				changeFolderName(file);
			} else {
				changeFileName(file, 0);
			}
		}

		scanner.close();
	}

	public static void changeFolderName(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					changeFolderName(files[i]);
				} else {
					changeFileName(files[i], i);
				}
			}
		}
	}

	public static void changeFileName(File file, int i) {
		if (file.isFile()) {
			String oldName = file.getName();
			String suffix = "";

			if (oldName.lastIndexOf(".") >= 0)
				suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
			if (oldName.indexOf(".") >= 0) {
				oldName = oldName.substring(0, oldName.indexOf("."));
			}

			i++;
			String newName = prefix + "_" + i + "." + suffix;
			String path = file.getParent();
			File newFile = new File(path + "/" + newName);

			file.renameTo(newFile);
		}
	}
}

package com.filesystem;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Directory root = new Directory("root");
		File file1 = new File("document.txt", 1000);
		File file2 = new File("image.png", 5000);
		File file3 = new File("video.mp4", 15000);

		root.add(file1);
		root.add(file2);
		root.add(file3);

		Directory subDir = new Directory("subdir");
		File file4 = new File("notes.txt", 2000);
		subDir.add(file4);
		root.add(subDir);

		System.out.println("********************Root Directory********************");
		root.ls();

		// Search by name
		Filter nameFilter = new NameFilter("txt");
		List<File> nameFilteredFiles = root.search(nameFilter);
		System.out.println("********************Name Filtered Files********************");
		System.out.println("Files with 'txt' in name:");
		for (File file : nameFilteredFiles) {
			file.ls();
		}

		// Search by size
		Filter sizeFilter = new SizeFilter(1000, 6000);
		List<File> sizeFilteredFiles = root.search(sizeFilter);
		System.out.println("********************Size Filtered Files********************");
		System.out.println("Files between 1000 and 6000 bytes:");
		for (File file : sizeFilteredFiles) {
			file.ls();
		}
	}
}
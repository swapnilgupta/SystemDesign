package com.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {
	private final String name;
	List<FileSystem> fileSystemList;

	public Directory(String name) {
		this.name = name;
		fileSystemList = new ArrayList<>();
	}

	public void add(FileSystem fileSystemObj) {
		fileSystemList.add(fileSystemObj);
	}

	public void remove(FileSystem fileSystemObj) {
		fileSystemList.remove(fileSystemObj);
	}

	public void createFile(String fileName) {
		System.out.println("Creating file: " + fileName);
		FileSystem file = new File(fileName);
		fileSystemList.add(file);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void ls() {
		System.out.println("Directory: " + name);
		for (FileSystem obj : fileSystemList) {
			obj.ls();
		}
	}

	@Override
	public List<FileSystem> search(String fileName) {
		List<FileSystem> result = new ArrayList<>();
		for (FileSystem obj : fileSystemList) {
			result.addAll(obj.search(fileName));
		}
		return result;
	}

	public List<File> filterFiles(FileFilter fileFilter) {
		List<File> result = new ArrayList<>();
		for (FileSystem obj : fileSystemList) {
			if (obj instanceof File && fileFilter.apply(obj)) {
				result.add((File) obj);
			}
		}
		return result;
	}


}
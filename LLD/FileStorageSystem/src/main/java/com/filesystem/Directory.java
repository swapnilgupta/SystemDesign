package com.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {
	private final String name;
	private final List<FileSystem> fileSystemList;

	public Directory(String name) {
		this.name = name;
		this.fileSystemList = new ArrayList<>();
	}

	public void add(FileSystem fileSystemObj) {
		fileSystemList.add(fileSystemObj);
	}

	public void remove(FileSystem fileSystemObj) {
		fileSystemList.remove(fileSystemObj);
	}

	@Override
	public void ls() {
		System.out.println("Directory: " + name);
		for (FileSystem obj : fileSystemList) {
			obj.ls();
		}
	}

	@Override
	public List<File> search(Filter filter) {
		List<File> result = new ArrayList<>();
		for (FileSystem obj : fileSystemList) {
			result.addAll(obj.search(filter));
		}
		return result;
	}
}
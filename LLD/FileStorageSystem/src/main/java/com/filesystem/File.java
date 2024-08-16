package com.filesystem;

import java.util.ArrayList;
import java.util.List;

public class File implements FileSystem {
	private String name;
	private StringBuilder content;

	public File(String name) {
		this.name = name;
		this.content = new StringBuilder();
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addContent(String content) {
		this.content.append(content);
	}

	public String getContent() {
		return content.toString();
	}

	@Override
	public void ls() {
		System.out.println("File: " + name);
	}

	@Override
	public List<FileSystem> search(String fileName) {
		// Since a file can't contain other files, we just check its name.
		List<FileSystem> result = new ArrayList<>();
		if (name.equals(fileName)) {
			result.add(this);
		}
		return result;
	}
}
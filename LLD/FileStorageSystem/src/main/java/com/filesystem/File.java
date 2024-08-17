package com.filesystem;

import java.util.List;

public record File(String name, long size) implements FileSystem {


	@Override
	public void ls() {
		System.out.println("File: " + name + " (Size: " + size + " bytes)");
	}

	@Override
	public List<File> search(Filter filter) {
		return filter.apply(this) ? List.of(this) : List.of();
	}
}
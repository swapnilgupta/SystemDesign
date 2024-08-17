package com.filesystem;

public class NameFilter implements Filter {
	private final String pattern;

	public NameFilter(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean apply(File file) {
		return file.name().contains(pattern);
	}
}
package com.filesystem;

public class ExtensionFilter implements Filter {
	private final String extension;

	public ExtensionFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean apply(File file) {
		return file.name().endsWith(extension);
	}

}

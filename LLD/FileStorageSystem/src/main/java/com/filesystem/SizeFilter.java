package com.filesystem;

public class SizeFilter implements Filter {
	private final long minSize;
	private final long maxSize;

	public SizeFilter(long minSize, long maxSize) {
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	@Override
	public boolean apply(File file) {
		return file.size() >= minSize && file.size() <= maxSize;
	}
}
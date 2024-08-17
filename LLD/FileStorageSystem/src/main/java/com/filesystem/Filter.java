package com.filesystem;

@FunctionalInterface
public interface Filter {
	boolean apply(File file);
}
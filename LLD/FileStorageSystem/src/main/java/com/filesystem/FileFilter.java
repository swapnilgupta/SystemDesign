package com.filesystem;

@FunctionalInterface
public interface FileFilter {
	boolean apply(FileSystem file);
}
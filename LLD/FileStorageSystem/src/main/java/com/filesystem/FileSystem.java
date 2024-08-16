package com.filesystem;

import java.util.Collection;
import java.util.List;

public interface FileSystem {
	void ls();
	List<FileSystem> search(String fileName);

	String getName();
}

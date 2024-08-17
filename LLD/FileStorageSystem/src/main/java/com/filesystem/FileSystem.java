package com.filesystem;

import java.util.List;

public interface FileSystem {
	void ls();
	List<File> search(Filter filter);
}
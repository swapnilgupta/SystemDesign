package com.filesystem;

public class Main {
	public static void main(String[] args) {
		Directory movies = new Directory("Movies");

		File border = new File("Border");
		FileSystem sholay = new File("Sholay");
		FileSystem bahubali = new File("Bahubali");
		movies.add(border);
		movies.add(sholay);
		movies.add(bahubali);

		Directory comedy = new Directory("Comedy");
		FileSystem heraPheri = new File("Hera Pheri");
		FileSystem golmaal = new File("Golmaal");
		comedy.add(heraPheri);
		comedy.add(golmaal);

		movies.add(comedy);

		movies.ls();

		// Create a new file
		movies.createFile("NewMovie");
		movies.ls();

		// Add content to a file
		border.addContent("This is the content of Border.");

		// Search for a file
		System.out.println("Searching for 'Sholay':");
		for (FileSystem file : movies.search("Sholay")) {
			file.ls();
		}

		// Filter files
		System.out.println("Filtering files with name containing 'a':");
		for (File file : movies.filterFiles(f -> f.getName().contains("a"))) {
			file.ls();
		}
	}
}
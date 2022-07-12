package com.cs.ee.msb.intf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class DependenceCopier {

	private  Set<String> dependencyFiles=new HashSet<String>();
	private  Set<String> dependencyFilesNotAvailable=new HashSet<String>();
	private  String sourcePath="E:\\src\\";
	private  String targetPath="E:\\backUp\\src\\";


	public static void main(String[] args) throws Exception {
		DependenceCopier dc=new DependenceCopier();
		String mainFile="com\\csme\\nedbank\\voucher\\service\\VoucherService.java";
		dc.getDependencyFiles(mainFile);
		dc.displayStats();
		//dc.generateCopyScripts();
		dc.copyFiles();
	}
	public void generateCopyScripts() {
		for (String fileTobeCopy : dependencyFiles) {
			String targetpath=fileTobeCopy.replace(sourcePath,targetPath);
			System.out.println("copy "+fileTobeCopy+"  "+targetpath);
		}
	}
	public void copyFiles() throws IOException {
		for (String fileTobeCopy : dependencyFiles) {
			String targetpath=fileTobeCopy.replace(sourcePath,targetPath);
			FileUtils.copyFile(new File(fileTobeCopy), new File(targetpath));
		}
	}

	public void getDependencyFiles(String fileName) throws FileNotFoundException {
		Scanner sc=null;
		try {
			File f=new File(sourcePath+fileName);
			if(f.exists())
				sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		while (sc.hasNextLine()) {
			String textLine = sc.nextLine();
			if(textLine.startsWith("import")) {
				textLine=textLine.replace(".",File.separator);
				textLine=textLine.replace("import ","").replace(";",".java");
				String sourceFileWithPath = sourcePath+textLine;
				File f=new File(sourceFileWithPath);
				if(f.exists()) {
					if(!dependencyFiles.contains(sourceFileWithPath)) {
						dependencyFiles.add(sourceFileWithPath);
						getDependencyFiles(textLine);
					}
				}else {
					dependencyFilesNotAvailable.add(sourceFileWithPath);
				}
			}
		}
	}
	public void displayStats() {
		System.out.println("Dependency Files .....");
		for (String fileTobeCopy : dependencyFiles) {
			System.out.println(fileTobeCopy);
		}
		System.out.println("Dependency Files which are not availble.....");
		for (String fileTobeCopy : dependencyFilesNotAvailable) {
			System.err.println(fileTobeCopy);
		}
	}
}

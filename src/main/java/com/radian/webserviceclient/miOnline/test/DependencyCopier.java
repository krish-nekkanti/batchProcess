package com.radian.webserviceclient.miOnline.test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class DependencyCopier {

	private  Set<String> dependencyFiles=new HashSet<String>();
	private  Set<String> dependencyFilesNotAvailable=new HashSet<String>();
	private  String sourcePath="C:\\Users\\knekkanti-d\\git\\CUWBilling\\java\\main\\";
	private String source2Path="C:\\Users\\knekkanti-d\\git\\CUWBilling\\foundation\\java\\main\\";
	private  String targetPath="C:\\Users\\knekkanti-d\\Patch\\";


	public static void main(String[] args) throws Exception {
		DependencyCopier dc=new DependencyCopier();
		String mainFile="com\\radian\\cuwbilling\\billing\\cuw\\bs\\ejb\\TimeSheetGenerationBean.java";
		dc.getDependencyFiles(mainFile);
			dc.displayStats();
		//	dc.generateCopyScripts();
		dc.copyFiles();
	}
	public void generateCopyScripts() {
		for (String fileTobeCopy : dependencyFiles) {
			String targetpath=fileTobeCopy.replace(sourcePath,targetPath);
			targetpath=fileTobeCopy.replace(source2Path,targetPath);

			System.out.println("copy "+fileTobeCopy+"  "+targetpath);
		}
	}
	public void copyFiles() throws IOException {
		for (String fileTobeCopy : dependencyFiles) {
			System.out.println("----------------------------------------------");
			System.out.println(fileTobeCopy);

			String targetpath="";
			if(fileTobeCopy.startsWith(sourcePath)) {
				targetpath=fileTobeCopy.replace(sourcePath,targetPath);
			}else {
				targetpath=fileTobeCopy.replace(source2Path,targetPath);
			}
			if(!fileTobeCopy.equalsIgnoreCase(targetpath)) {
				FileUtils.copyFile(new File(fileTobeCopy), new File(targetpath));
				System.out.println(targetpath);
			}else {
				System.err.println("Yes "+targetpath);
			}
		}
	}

	public void getDependencyFiles(String fileName) throws FileNotFoundException {
		System.err.println(fileName);
		if(fileName.indexOf("BaseEnumType")!=-1) {
			System.out.println("here");
		}
		Scanner sc=null;
		try {
			String name = sourcePath+fileName;
			File f=new File(name);
			if(f.exists()) {
				sc = new Scanner(f);
			}else {
				name = source2Path+fileName;
				f=new File(name);
				if(f.exists()) {
					sc = new Scanner(f);
				}else {
					return ;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		while (sc!=null && sc.hasNextLine()) {
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
					sourceFileWithPath = source2Path+textLine;
					System.out.println(sourceFileWithPath);
					f=new File(sourceFileWithPath);
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

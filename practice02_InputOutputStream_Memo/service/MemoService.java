package com.encore.service;
//Memojang기능을 정의한 클래스

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
//import com.encore.vo.Memo;
//import java.util.ArrayList;

public class MemoService {
	
	public MemoService() {}
	
	// String list of all memo file titles
	public String[] viewAll() {
		File dir = new File("src/com/encore/vo/memoDir");
		return dir.list();
	}
	
	// Search file
	public Boolean searchMemo(String fileTitle) {
		File f = new File("src/com/encore/vo/memoDir/"+fileTitle);
		return f.exists();
	}
	
	
	// Create a directory 
	public Boolean createDir() {
		File dir = new File("src/com/encore/vo/memoDir");
		if(!dir.exists()) {
			return dir.mkdir();
		}
		else return true;
	}
	
	// Save memo (String title = file name) *check for redundant name
	public void saveMemo(String fileTitle, String editedDate) {
		createDir();
		//double fileSize=0;
		File f1 = new File("src/com/encore/vo/memoDir/"+fileTitle);
		if(!f1.exists()) {
			try {
				f1.createNewFile();
				//fileSize = f1.length();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}	
		}
	}
	

	// Write memo(String title =file name, continueWrite=이어쓰기 if true)
	public void writeMemo(String fileTitle, Boolean continueWrite) {
		try {
			FileOutputStream fo = new FileOutputStream("src/com/encore/vo/memoDir/"+fileTitle, continueWrite);
			int ch = ' ';
			while ((ch=System.in.read())!='/') {
				fo.write(ch);
			}
			fo.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	} 
	
	// Read memo
	public void readMemo(String fileTitle) {
		//Memo toRead = searchMemo(fileTitle);
		System.out.println("Selected Memo: ");
		//System.out.println(toRead);
		try {
			FileInputStream fi = new FileInputStream("src/com/encore/vo/memoDir/"+fileTitle);
			int ch;
			while((ch=fi.read())!= -1) {
				System.out.print((char)ch);
			}
			fi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// copy memo
	public void copyMemo(String copyFrom, String copyTo) {
		try {
			FileReader fr = new FileReader("src/com/encore/vo/memoDir/"+copyFrom);
			BufferedReader br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter("src/com/encore/vo/memoDir/"+copyTo);
			BufferedWriter bw = new BufferedWriter(fw);
			
			char[] buf = new char[50];
			
			while(br.read(buf)!=-1) {
				bw.write(buf);
			}
			br.close();//closing순서 상관없음.
			bw.close();
			fr.close();
			fw.close();
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	// Delete memo files
	public void deleteMemo(String fileTitle) {
		for (String s:viewAll()) {
			if(s.equals(fileTitle)) {
				File f = new File("src/com/encore/vo/memoDir/"+fileTitle);
				f.delete();
				break;
			}
		}	
	}
	
	// Create a zip file
	public void zipFiles(String[] pathsToZip, String zipFileName) {
		try {
			String zipName = zipFileName.concat(".zip");
			FileOutputStream fo = new FileOutputStream(zipName);
			ZipOutputStream zo = new ZipOutputStream(fo);
			
			for(String path: pathsToZip) {
				zo.putNextEntry(new ZipEntry(new File(path).getName()));
				byte[] bytes = Files.readAllBytes(Paths.get(path));
				zo.write(bytes,0,bytes.length);
				zo.closeEntry();
			}
			zo.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}

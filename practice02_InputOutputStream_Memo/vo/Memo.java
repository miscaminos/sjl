package com.encore.vo;
//Memo file 객체 클래스

public class Memo {
	private String fileName;
	private double fileSize;
	private String editedDate;
	
	public Memo() {}
	
	public Memo(String fileName, double fileSize, String editedDate) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.editedDate = editedDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public String getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(String editedDate) {
		this.editedDate = editedDate;
	}

	@Override
	public String toString() {
		return "Memo [fileName=" + fileName + ", fileSize=" + fileSize + ", editedDate=" + editedDate + "]";
	}
	
	
	
	
}

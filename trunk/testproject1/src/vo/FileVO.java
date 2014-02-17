package vo;

import java.util.Date;

public class FileVO {

	private int fileId;
	private String filePath;
	private Date uploadDate;
	public FileVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileVO(int fileId, String filePath, Date uploadDate) {
		super();
		this.fileId = fileId;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
}

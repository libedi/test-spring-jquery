package vo;

import java.util.Date;

public class BoardVO {

	private int 	id;
	private String 	title;
	private String 	text;
	private String 	writer;
	private Date 	createDate;
	private Date 	modifyDate;
	private int		clickView;
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getClickView() {
		return clickView;
	}
	public void setClickView(int clickView) {
		this.clickView = clickView;
	}
	
	
}

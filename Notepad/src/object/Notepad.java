package object;

import java.util.Date;

public class Notepad {
	private int notepadId;
	private int adminId;
	private String title;
	private String content;
	private Date createdTime;
	private Date updatedTime;

	public Notepad(int notepadId, int adminId, String title, String content, Date createdTime, Date updatedTime) {
		this.notepadId   = notepadId;
		this.adminId     = adminId;
		this.title       = title;
		this.content     = content;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public int getNotepadId() {
		return notepadId;
	}

	public int getAdminId() {
		return adminId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
}

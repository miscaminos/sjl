package com.encore.board;

public class Reply {
	
	private int reply_id;
	private int board_id;
	private String comments;
	
	public Reply() {
		
	}
	
	public Reply(int reply_id, int board_id, String comments) {
		super();
		this.reply_id = reply_id;
		this.board_id = board_id;
		this.comments = comments;
	}

	
	
	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Reply [reply_id=" + reply_id + ", board_id=" + board_id + ", comments=" + comments + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 != null && arg0 instanceof Reply) {
			if(((Reply)arg0).reply_id == reply_id) {
				return true;
			}
		}
		return false;
	}
	
	

}

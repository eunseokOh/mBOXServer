package com.psql.project.vo;


import java.sql.Timestamp;

public class VideoDTO {
	private int VIDEO_SQ_PK;

	private String VIMEO_KEY;
	private Timestamp VIDEO_DT;
	private int STEP;
	private String VIDEO_NM;
	private String VIDEO_DESC;
	private boolean VIDEO_HIDE;
	private int VIDEO_NODE_NO;
	private int PARENT_VIDEO_NODE_NO;
	
	public int getVIDEO_SQ_PK() {
		return VIDEO_SQ_PK;
	}
	public void setVIDEO_SQ_PK(int vIDEO_SQ_PK) {
		VIDEO_SQ_PK = vIDEO_SQ_PK;
	}

	public String getVIMEO_KEY() {
		return VIMEO_KEY;
	}
	public void setVIMEO_KEY(String vIMEO_KEY) {
		VIMEO_KEY = vIMEO_KEY;
	}
	public Timestamp getVIDEO_DT() {
		return VIDEO_DT;
	}
	public void setVIDEO_DT(Timestamp vIDEO_DT) {
		VIDEO_DT = vIDEO_DT;
	}
	public int getSTEP() {
		return STEP;
	}
	public void setSTEP(int sTEP) {
		STEP = sTEP;
	}
	public String getVIDEO_NM() {
		return VIDEO_NM;
	}
	public void setVIDEO_NM(String vIDEO_NM) {
		VIDEO_NM = vIDEO_NM;
	}
	public String getVIDEO_DESC() {
		return VIDEO_DESC;
	}
	public void setVIDEO_DESC(String vIDEO_DESC) {
		VIDEO_DESC = vIDEO_DESC;
	}
	public boolean isVIDEO_HIDE() {
		return VIDEO_HIDE;
	}
	public void setVIDEO_HIDE(boolean vIDEO_HIDE) {
		VIDEO_HIDE = vIDEO_HIDE;
	}
	public int getVIDEO_NODE_NO() {
		return VIDEO_NODE_NO;
	}
	public void setVIDEO_NODE_NO(int vIDEO_NODE_NO) {
		VIDEO_NODE_NO = vIDEO_NODE_NO;
	}
	public int getPARENT_VIDEO_NODE_NO() {
		return PARENT_VIDEO_NODE_NO;
	}
	public void setPARENT_VIDEO_NODE_NO(int pARENT_VIDEO_NODE_NO) {
		PARENT_VIDEO_NODE_NO = pARENT_VIDEO_NODE_NO;
	}
	
	
	
}

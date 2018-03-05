package com.psql.project.vo;

import java.util.List;

public class UploadVideoVO {
	private int CHANNEL_SQ_FK;
	private int STEP;
	private String VIDEO_NM;
	private String VIDEO_DESC;
	private boolean VIDEO_HIDE;
	private String VIMEO_KEY;
	private int videoNode;
	private int parentVideoNode;
	
	List<UploadVideoVO> videoVO;
	public int getCHANNEL_SQ_FK() {
		return CHANNEL_SQ_FK;
	}
	public void setCHANNEL_SQ_FK(int cHANNEL_SQ_FK) {
		CHANNEL_SQ_FK = cHANNEL_SQ_FK;
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
	public String getVIMEO_KEY() {
		return VIMEO_KEY;
	}
	public void setVIMEO_KEY(String vIMEO_KEY) {
		VIMEO_KEY = vIMEO_KEY;
	}
	public int getVideoNode() {
		return videoNode;
	}
	public void setVideoNode(int videoNode) {
		this.videoNode = videoNode;
	}
	public int getParentVideoNode() {
		return parentVideoNode;
	}
	public void setParentVideoNode(int parentVideoNode) {
		this.parentVideoNode = parentVideoNode;
	}
	public List<UploadVideoVO> getVideoVO() {
		return videoVO;
	}
	public void setVideoVO(List<UploadVideoVO> videoVO) {
		this.videoVO = videoVO;
	}
	
	
	
}

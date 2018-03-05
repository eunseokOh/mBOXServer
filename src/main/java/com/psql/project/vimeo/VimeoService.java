package com.psql.project.vimeo;

import com.psql.project.vo.UploadVideoVO;
import com.psql.project.vo.VideoDTO;

public interface VimeoService {
	int insertVideo(UploadVideoVO uploadVideoVO) throws Exception;

}

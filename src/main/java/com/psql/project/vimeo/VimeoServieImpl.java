package com.psql.project.vimeo;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psql.project.vo.UploadVideoVO;
import com.psql.project.vo.VideoDTO;

@Service
@Transactional
public class VimeoServieImpl implements VimeoService {
	
	@Autowired
	VimeoMapper vimeoMapper;

	@Override
	public int insertVideo(UploadVideoVO uploadVideoVO) throws Exception {
	
		return vimeoMapper.insertVideo(uploadVideoVO);
	}




}

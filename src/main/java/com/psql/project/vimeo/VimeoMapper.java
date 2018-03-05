package com.psql.project.vimeo;

import org.apache.ibatis.annotations.Mapper;

import com.psql.project.vo.UploadVideoVO;
import com.psql.project.vo.VideoDTO;

@Mapper
public interface VimeoMapper {

	int insertVideo(UploadVideoVO uploadVideoVO) throws Exception;

}

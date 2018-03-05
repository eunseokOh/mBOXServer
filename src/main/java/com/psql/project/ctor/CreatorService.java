package com.psql.project.ctor;

import java.util.List;

import com.psql.project.vo.VideoDTO;

public interface CreatorService {

	List<VideoDTO> getVideoList(int page) throws Exception;

}

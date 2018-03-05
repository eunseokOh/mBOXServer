package com.psql.project.ctor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.psql.project.vo.VideoDTO;

@Mapper
public interface CreatorMapper {

	List<VideoDTO> getVideoList(int page) throws Exception;

}

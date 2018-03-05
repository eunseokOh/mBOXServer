package com.psql.project.ctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psql.project.vo.VideoDTO;

@Service
public class CreatorServiceImpl implements CreatorService{
	@Autowired
	CreatorMapper cMapper;
	
	@Override
	public List<VideoDTO> getVideoList(int page) throws Exception {
		return cMapper.getVideoList(page);
		
		
	}
	
}

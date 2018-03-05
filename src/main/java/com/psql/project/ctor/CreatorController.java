package com.psql.project.ctor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.psql.project.vo.VideoDTO;

@Controller
@RequestMapping("ctor")
public class CreatorController {

	@Autowired
	CreatorService cService;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showMultiUploadForm() {
		return "ctor/upload";
	}

	@RequestMapping(value = "/video/list/{page}", method = RequestMethod.GET)
	@ResponseBody
	public String videoList(@PathVariable int page) {
		List<VideoDTO> videoDTOlist = null;
		String json = null;
		try {
			videoDTOlist = new ArrayList<VideoDTO>();
			videoDTOlist = cService.getVideoList(page);
			json = new Gson().toJson(videoDTOlist);
			return json;
		} catch (Exception e) {

			e.printStackTrace();
			return e.getMessage();
		}

	}

}

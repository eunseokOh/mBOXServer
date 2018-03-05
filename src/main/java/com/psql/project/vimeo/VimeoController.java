package com.psql.project.vimeo;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.psql.project.vo.UploadVideoVO;
import com.psql.project.vo.VideoDTO;

import net.minidev.json.JSONObject;


@Controller
@RequestMapping("video")
public class VimeoController {
	@Autowired
	VimeoService vService;
	
	private String token = null;
	private final int MAX_THREAD = 50;
	ExecutorService executorServie = null;
	Vimeo vimeo = null;
	private final String DEFAULT_PATH = "c:/upload-dir/";
	
	VideoDTO videoDTO;
	
	@PostConstruct
	public void init() {
		executorServie = Executors.newFixedThreadPool(MAX_THREAD);
		token = "<vimeo token>";
		vimeo = new Vimeo(token);
		videoDTO = new VideoDTO();
	}
	
	
	@RequestMapping(value = "/{videoId}", method = RequestMethod.GET)
	@ResponseBody
	public String video(@PathVariable("videoId") int video_sq_pk){
		JsonParser parser = null;
		JsonElement rootElement = null;
		Gson gson = null;
		JsonObject filesObj = null;
		
		try {
			gson = new GsonBuilder().disableHtmlEscaping().create();
			String jsonData = gson.toJson(vimeo.getVideoInfo("/videos/238721080"));
			parser = new JsonParser();
			rootElement = parser.parse(jsonData);
			rootElement = rootElement.getAsJsonObject().get("json")
					                 .getAsJsonObject().get("nameValuePairs");
			filesObj = rootElement.getAsJsonObject().get("files").getAsJsonObject();
			JsonArray jsonArr = filesObj.getAsJsonObject().get("values").getAsJsonArray();
			
			Map<String, String> video_link = new HashMap<String, String>();
			for(JsonElement jElement : jsonArr){
				JsonObject tmpJsonData = jElement.getAsJsonObject().get("nameValuePairs").getAsJsonObject();
				if(tmpJsonData.get("height") != null){
					video_link.put(tmpJsonData.get("height")+"p",tmpJsonData.get("link_secure").getAsString());
				}
			}
			JSONObject json = new JSONObject();
			json.putAll(video_link);
			return json.toJSONString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public String upload(UploadVideoVO videoVO, @RequestParam("files") MultipartFile[] files, @RequestParam("GROUP_NM") String video_group_nm) throws IOException, JSONException {
		System.out.println(video_group_nm);
		for (short i = 0; i <files.length; i++) {
			try {
				MultipartFile f = files[i];
				InputStream input = f.getInputStream();
				//String fileName = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("\\") + 1);
				String fileExt = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-", "");

				OutputStream out = new FileOutputStream(DEFAULT_PATH + uuid + fileExt, false);

				byte[] buf = new byte[4096];
				int len;
				while ((len = input.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				input.close();
				out.close();
				File tmpF = new File(DEFAULT_PATH + uuid + fileExt);
				Runnable run = new Vimeo(token, tmpF, videoVO.getVideoVO().get(i), vService);
				
				executorServie.submit(run);
				executorServie.shutdown();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return "file upload complete";

	}
}

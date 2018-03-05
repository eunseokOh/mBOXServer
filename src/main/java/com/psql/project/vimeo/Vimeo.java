package com.psql.project.vimeo;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.psql.project.vo.UploadVideoVO;




public class Vimeo implements Runnable{
	private static final String VIMEO_SERVER = "https://api.vimeo.com";
	private String token = null;
	private String tokenType = "bearer";
	private UploadVideoVO uploadVideoVO = null;
	private VimeoService vService = null;
	
	private File file = null;
	
	public Vimeo(String token){
		this.token = token;
	}
	
	public Vimeo(String token, File f, UploadVideoVO uploadVideoVO, VimeoService vService){
		this.token = token;
		this.file = f;
		this.uploadVideoVO = uploadVideoVO;
		this.vService = vService;
	}
	
	public VimeoResponse uploadVideo(File file, String uploadLinkSecure) throws IOException, JSONException {
		return apiRequest(uploadLinkSecure, HttpPut.METHOD_NAME, null, file);
	}
	
	public VimeoResponse getVideoInfo(String endpoint) throws IOException, JSONException {
		return apiRequest(endpoint, HttpGet.METHOD_NAME, null, null);
	}
	

	public VimeoResponse endUploadVideo(String completeUri) throws IOException, JSONException {
		
		
		return apiRequest(completeUri, HttpDelete.METHOD_NAME, null, null);
		
	}
	
	public String addVideo(File f, boolean upgradeTo1080) throws IOException, JSONException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "streaming");
		params.put("redirect_url", "");
		params.put("upgrade_to_1080", upgradeTo1080 ? "true" : "false");
		VimeoResponse response = beginUploadVideo(params);
	

		uploadVideo(f, response.getJson().getString("upload_link_secure"));
		response = endUploadVideo(response.getJson().getString("complete_uri"));
		if (response.getStatusCode() == 201) {
			f.delete();
			return response.getJson().getString("Location");
		}
	
		
		return null;	
	}
	
	public VimeoResponse beginUploadVideo(Map<String, String> params) throws IOException, JSONException {
		System.out.println("begin Upload");
		return apiRequest("/me/videos", HttpPost.METHOD_NAME, params, null);
	}
	
	protected VimeoResponse apiRequest(String endpoint, String methodName, Map<String, String> params, File file) throws IOException, JSONException {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpRequestBase request = null;
		String url = null;
		if (endpoint.startsWith("http")) {
			url = endpoint;
		} else {
			url = new StringBuffer(VIMEO_SERVER).append(endpoint).toString();
		}
		if (methodName.equals(HttpGet.METHOD_NAME)) {
			request = new HttpGet(url);
		} else if (methodName.equals(HttpPost.METHOD_NAME)) {
			request = new HttpPost(url);
		} else if (methodName.equals(HttpPut.METHOD_NAME)) {
			request = new HttpPut(url);
		} else if (methodName.equals(HttpDelete.METHOD_NAME)) {
			request = new HttpDelete(url);
		} else if (methodName.equals(HttpPatch.METHOD_NAME)) {
			request = new HttpPatch(url);
		}
		request.addHeader("Accept", "application/vnd.vimeo.*+json; version=3.2");
		request.addHeader("Authorization", new StringBuffer(tokenType).append(" ").append(token).toString());
		HttpEntity entity = null;
		if (params != null) {
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				postParameters.add(new BasicNameValuePair(key, params.get(key)));
			}
			entity = new UrlEncodedFormEntity(postParameters);
		} else if (file != null) {
			entity = new FileEntity(file, ContentType.MULTIPART_FORM_DATA);
		}
		if (entity != null) {
			if (request instanceof HttpPost) {
				((HttpPost) request).setEntity(entity);
			} else if (request instanceof HttpPatch) {
				((HttpPatch) request).setEntity(entity);
			} else if (request instanceof HttpPut) {
				((HttpPut) request).setEntity(entity);
			}
			
		}
		CloseableHttpResponse response = client.execute(request);
		String responseAsString = null;
		int statusCode = response.getStatusLine().getStatusCode();
		if (methodName.equals(HttpPut.METHOD_NAME) || methodName.equals(HttpDelete.METHOD_NAME)) {
			JSONObject out = new JSONObject();
			for (Header header : response.getAllHeaders()) {
				out.put(header.getName(), header.getValue());
			}
			responseAsString = out.toString();
		} else if (statusCode != 204) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.getEntity().writeTo(out);
			responseAsString = out.toString("UTF-8");
			out.close();
		}
		JSONObject json = null;
		JSONObject headers = null;
		try {
			json = new JSONObject(responseAsString);
			headers = new JSONObject();
			for (Header header : response.getAllHeaders()) {
				headers.put(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			json = new JSONObject();
			headers = new JSONObject();
		}
		VimeoResponse vimeoResponse = new VimeoResponse(json, headers, statusCode);
		response.close();
		client.close();
		return vimeoResponse;
	}

	@Override
	@Transactional
	public void run() {

		try {
			
			uploadVideoVO.setVIMEO_KEY(addVideo(file, false));
			System.out.println("end Upload");
			uploadVideoVO.setCHANNEL_SQ_FK(1);
			
			try {
		
				vService.insertVideo(uploadVideoVO);
				System.out.println("db insert success");
			} catch (Exception e) {
				System.out.println("db insert fail");
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}

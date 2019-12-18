package com.unihannover.gamedev.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihannover.gamedev.models.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.models.Model;

@Service
public class HttpService {

	CloseableHttpClient httpClient;

	@Autowired
	CollectorConfig config;

    public List<User> getUsers() {
		httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		HttpEntity result;
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			HttpGet get = new HttpGet("http://devgame:8080/api/users/all");
			get.setHeader("Accept", "*/*");
			get.setHeader("Content-type", "application/json");
			if (config.getToken() != null) {
				get.setHeader("X-Auth-Token", config.getToken());
			}
			response = httpClient.execute(get);
			String responsestring = EntityUtils.toString(response.getEntity(), "UTF-8");
			// System.out.println("Responsestring: " + responsestring);
			// User[] uArray = objectMapper.readValue(responsestring, new TypeReference<List<User>>(){}););
			List<User> uList = objectMapper.readValue(responsestring, new TypeReference<List<User>>(){}); // Arrays.asList(uArray);
			return uList;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    public HttpEntity sendModel(Model m, String url) {
		List<Model> mList = new ArrayList<>();
		mList.add(m);
		return sendList(mList, url);
	}

	public HttpEntity sendList(List<Model> mList, String url) {
		HttpEntity result = null;
		String json = ListToJSON(mList);
		httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		try {
			// send a JSON data
			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "*/*");
			post.setHeader("Content-type", "application/json");
			if (config.getToken() != null) {
				post.setHeader("X-Auth-Token", config.getToken());
			}
			post.setEntity(new StringEntity(json.toString()));
			System.out.println(json.toString());
			response = httpClient.execute(post);
			result = response.getEntity();
			if (response.getStatusLine().getStatusCode() < 300) {
				return result;
				// updateWithToken(result);
			} else {
				System.out.println("Post failed: " + response.getStatusLine().getStatusCode());
				return null;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpEntity sendSingleModel(Model m, String url) {
		HttpEntity result = null;
		String json = m.toJSON();
		httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		try {
			// send a JSON data
			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "*/*");
			post.setHeader("Content-type", "application/json");
			if (config.getToken() != null) {
				post.setHeader("X-Auth-Token", config.getToken());
			}
			post.setEntity(new StringEntity(json.toString()));
			System.out.println(json.toString());
			response = httpClient.execute(post);
			result = response.getEntity();
			if (response.getStatusLine().getStatusCode() < 300) {
				return result;
				// updateWithToken(result);
			} else {
				System.out.println("Post failed: " + response.getStatusLine().getStatusCode());
				return null;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HttpEntity sendTest(String url) {
		HttpEntity result = null;
		httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		try {
			// send a JSON data
			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "*/*");
			post.setHeader("Content-type", "text/plain");
			StringEntity entity = new StringEntity("TestEntityBody\n");
			post.setEntity(entity);
			System.out.println("Sending post: \n");
			System.out.println(post.getMethod()+"\n");
			response = httpClient.execute(post);
			result = response.getEntity();
			if (response.getStatusLine().getStatusCode() < 300) {
				return result;
				// updateWithToken(result);
			} else {
				System.out.println("Post failed: " + response.getStatusLine().getStatusCode());
				return null;
			}

		} catch (UnsupportedEncodingException e) {
			System.out.println("Post failed: UnsupportedEncodingException");
		} catch (ClientProtocolException e) {
			System.out.println("Post failed: ClientProtocolException");
		} catch (IOException e) {
			System.out.println("Post failed: IOException");
			e.printStackTrace();
		}
		return null;
	}

	public String ListToJSON(List<Model> mList) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		for (Model m : mList) {
			json.append(m.toJSON().toString() + ", ");
		}
		json.deleteCharAt(json.length() - 2);
		json.append("]");
		return json.toString();
	}
}

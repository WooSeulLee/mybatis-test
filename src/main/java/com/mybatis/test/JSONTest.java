package com.mybatis.test;

import java.util.Map;

import com.google.gson.Gson;

public class JSONTest {
	public static void main(String[] args) {
		Gson g = new Gson();
		String str = "{\"name\" : \"이순신\"}";
		System.out.println(str);
		Map<String, String> map = g.fromJson(str, Map.class);
		System.out.println(map.get("name"));
	}
}

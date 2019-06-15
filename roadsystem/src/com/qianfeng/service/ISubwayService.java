package com.qianfeng.service;


import com.qianfeng.vo.SubwayMessages;

public interface ISubwayService {
	public SubwayMessages findAll();
	public SubwayMessages findAllRoadBySta(String ssta,String esta);
}

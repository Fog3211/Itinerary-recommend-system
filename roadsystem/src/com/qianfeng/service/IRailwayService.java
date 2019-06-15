package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Railway;

public interface IRailwayService {

	List<Railway> findAllRailwayBySstaAndEsta(String ssta, String esta);

}

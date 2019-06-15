package com.qianfeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianfeng.entity.Railway;

public interface IRailwayDao {

	Railway findRailwayByTNum(String tnum);

	List<Railway> findAllRailwayBySstaAndEsta(@Param("ssta") String ssta,@Param("esta") String esta);

	List<Railway> findAllRailwayByTNum(@Param("tnum") String transportnum);

	

}

package com.qianfeng.dao;

import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianfeng.entity.Road;

public interface IRoadDao {

  Road findRoadByStas(String stas);

Road findRoadBySsta(String staname);

Road findRoadByEsta(String staname);

Road findRoadByStop(String staname, String staname2);

List<Road> findAllRoadByStas(@Param("staname") String staname,@Param("flag") char c);

List<Road> findAllRoadBySsta(String staname);

List<Road> findAllRoadByEsta(String staname);

Road findRoadByStasAndEstaNum(@Param("stas") String stas,@Param("estanum") String estanum);

Road findRoadBySstaNumAndEstaNum(@Param("sstanum") String sstanum,@Param("estanum") String estanum);

Road findRoadBySstaNumAndStas(@Param("sstanum") String sstanum,@Param("stas") String stas);

double findPriceByTransportNum(String transportnum);

Time findStimeByRdNumAndStaName(@Param("rdnum") String rdnum,@Param("staname") String staname);

Time findEtimeByRdNumAndStaName(@Param("rdnum") String rdnum,@Param("staname") String staname);

int findChangeTimeByStaNameAndSroadNumAndEroadNum(@Param("staname") String staname,@Param("sroadnum") String sroadnum,@Param("eroadnum") String eroadnum);

/*Road findRoadByStasAndStas(@Param("stas2") String stas1,@Param("stas2") String stas2);*/


}

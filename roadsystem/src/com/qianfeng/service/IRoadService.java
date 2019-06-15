package com.qianfeng.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qianfeng.entity.Node;
import com.qianfeng.entity.Road;
import com.qianfeng.entity.RoadMap;
import com.qianfeng.entity.Station;
import com.qianfeng.vo.Approach;
import com.qianfeng.vo.Approachs;
import com.qianfeng.vo.SENode;
import com.qianfeng.vo.SubwayMessages;

public interface IRoadService {
	/*public Approachs findAllRoadBySta(String ssta,String esta,String type);

	public Approachs findAllRoadBySta(String ssta, String esta);

	public Approachs findAllRoadBySta(RoadMap rmap);

	public Approachs findAllRoadBySta(RoadMap rmap, int v);

	public Approachs findAllRoadByRoadMap(RoadMap rmap, int v);*/

	/*public Approachs findAllRoadByRoadMap(RoadMap rmap, Station sv, Station ev);*/

	/*public Approachs findAllRoadByRoadMap(RoadMap rmap, Station sv, Station ev, int n);*/

	/*Approachs findAllRoadByRoadMap(List<Node> rmap, Node sv, Node ev);*/

	/*public Approachs findAllRoadByRoadMap(List<Node> rmap, Station sv, Station ev,int n);*/

	public Approachs findAllRoadByRoadMap(Node sn, Node en, Date cal);

	public String findLocalType(String ssta, String esta);

	public Station findStationByName(String ssta);

	public List<Station> findAllStationByCity(String city);



	public SENode formatNode(List<Station> stas, Station sta, Station eta, String[] tports);

	public Approachs findApproachsByblock(Station sta, Station eta, Date cal);

	public List<Station> findAllStopByCity(String city);

	public List<Station> findAllStopByProvince(String province);

	public List<Station> findAllStop();

/*	public Road findRoadByStas(String ssta);*/

    public List<Road> findAllRoadByStas(String ssta);



}

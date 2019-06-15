package com.qianfeng.vo;

import java.util.List;

public class Route {
 private List<RoadMessage> RoadMessages;
 private int changenum;
 List<Integer> changelast;
 


public List<Integer> getChangelast() {
	return changelast;
}
public void setChangelast(List<Integer> changelast) {
	this.changelast = changelast;
}
public List<RoadMessage> getRoadMessages() {
	return RoadMessages;
}
public void setRoadMessages(List<RoadMessage> roadMessages) {
	RoadMessages = roadMessages;
}
public int getChangenum() {
	return changenum;
}
public void setChangenum(int changenum) {
	this.changenum = changenum;
}
 
}

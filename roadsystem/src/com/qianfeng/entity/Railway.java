package com.qianfeng.entity;

import java.sql.Time;

public class Railway {
 private String tnum;
 private Time stime;
 private Time etime;
 private String last;
 private String ssta;
 private String esta;
 private double fseat;
 private double sseat;
 private double bseat;
public String getTnum() {
	return tnum;
}
public void setTnum(String tnum) {
	this.tnum = tnum;
}
public Time getStime() {
	return stime;
}
public void setStime(Time stime) {
	this.stime = stime;
}
public Time getEtime() {
	return etime;
}
public void setEtime(Time etime) {
	this.etime = etime;
}
public String getLast() {
	return last;
}
public void setLast(String last) {
	this.last = last;
}
public String getSsta() {
	return ssta;
}
public void setSsta(String ssta) {
	this.ssta = ssta;
}
public String getEsta() {
	return esta;
}
public void setEsta(String esta) {
	this.esta = esta;
}
public double getFseat() {
	return fseat;
}
public void setFseat(double fseat) {
	this.fseat = fseat;
}
public double getSseat() {
	return sseat;
}
public void setSseat(double sseat) {
	this.sseat = sseat;
}
public double getBseat() {
	return bseat;
}
public void setBseat(double bseat) {
	this.bseat = bseat;
}
      
}

package com.qianfeng.entity;

import java.sql.Date;
import java.sql.Time;

public class Road {
	
   private String rdnum;
   private String sstanum;
   private String estanum;
   private String stas;
   private String transportnum;
   private Time doftime;
   private Time doltime;
   private int lasttime;
   private int stacount;
   private Time stime;
   private Time etime;
   


/**
 * @param rdnum
 * @param sstanum
 * @param estanum
 * @param stas
 * @param transportnum
 * @param doftime
 * @param doltime
 * @param lasttime
 * @param stacount
 */
   
public Road(Road road) {
	super();
	this.rdnum = road.getRdnum();
	this.sstanum = road.getSstanum();
	this.estanum = road.getEstanum();
	this.stas = road.getStas();
	this.transportnum = road.getTransportnum();
	this.doftime = road.getDoftime();
	this.doltime = road.getDoltime();
	this.lasttime = road.getLasttime();
	this.stacount =road.getStacount();
	this.etime=road.getEtime();
	this.stime=road.getStime();
}
/**
 * 
 */
public Road() {
	super();
	// TODO Auto-generated constructor stub
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
public int getStacount() {
	return stacount;
}
public void setStacount(int stacount) {
	this.stacount = stacount;
}
public Time getDoftime() {
	return doftime;
}
public void setDoftime(Time doftime) {
	this.doftime = doftime;
}
public Time getDoltime() {
	return doltime;
}
public void setDoltime(Time doltime) {
	this.doltime = doltime;
}
public int getLasttime() {
	return lasttime;
}
public void setLasttime(int lasttime) {
	this.lasttime = lasttime;
}
public String getRdnum() {
	return rdnum;
}
public void setRdnum(String rdnum) {
	this.rdnum = rdnum;
}
public String getSstanum() {
	return sstanum;
}
public void setSstanum(String sstanum) {
	this.sstanum = sstanum;
}
public String getEstanum() {
	return estanum;
}
public void setEstanum(String estanum) {
	this.estanum = estanum;
}
public String getStas() {
	return stas;
}
public void setStas(String stas) {
	this.stas = stas;
}
public String getTransportnum() {
	return transportnum;
}
public void setTransportnum(String transportnum) {
	this.transportnum = transportnum;
}
   

   
}

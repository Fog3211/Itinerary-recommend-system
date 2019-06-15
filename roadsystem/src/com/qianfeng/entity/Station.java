package com.qianfeng.entity;

import java.sql.Time;

public class Station {
  private String stanum;
  private String staname;
  private String dir;
  private Time doftime;
  private Time doltime;
  private Time uoftime;
  private Time uoltime;
  private String city;
  private String province;
  private String stop;
  
public String getStop() {
	return stop;
}
public void setStop(String stop) {
	this.stop = stop;
}
public Time getUoftime() {
	return uoftime;
}
public void setUoftime(Time uoftime) {
	this.uoftime = uoftime;
}
public Time getUoltime() {
	return uoltime;
}
public void setUoltime(Time uoltime) {
	this.uoltime = uoltime;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getStanum() {
	return stanum;
}
public void setStanum(String stanum) {
	this.stanum = stanum;
}
public String getStaname() {
	return staname;
}
public void setStaname(String staname) {
	this.staname = staname;
}
public String getDir() {
	return dir;
}
public void setDir(String dir) {
	this.dir = dir;
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

}

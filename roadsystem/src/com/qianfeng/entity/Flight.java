package com.qianfeng.entity;

import java.sql.Time;

public class Flight {
 private String flynum;
 private String flyfirm;
 private String type;
 private Time stime;
 private Time etime;
 private String sadress;
 private String eadress;
 private String rate;
 private double price;
public String getFlynum() {
	return flynum;
}
public void setFlynum(String flynum) {
	this.flynum = flynum;
}
public String getFlyfirm() {
	return flyfirm;
}
public void setFlyfirm(String flyfirm) {
	this.flyfirm = flyfirm;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
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
public String getSadress() {
	return sadress;
}
public void setSadress(String sadress) {
	this.sadress = sadress;
}
public String getEadress() {
	return eadress;
}
public void setEadress(String eadress) {
	this.eadress = eadress;
}
public String getRate() {
	return rate;
}
public void setRate(String rate) {
	this.rate = rate;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
     
}

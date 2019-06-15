package com.qianfeng.vo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import com.qianfeng.entity.Station;

public class RoadMessage {
	 private StationMessage ssta;
	   private StationMessage esta;
	   private String stas;
	   private double price;
	   private int lasttime;
	   private String rdnum;
	   private String transportnum;
	   private Date offdate;
       private Date arrdate;
       
    
	public Date getArrdate() {
		return arrdate;
	}
	public void setArrdate(Date arrdate) {
		this.arrdate = arrdate;
	}
	public Date getOffdate() {
		return offdate;
	}
	public void setOffdate(Date offdate) {
		this.offdate = offdate;
	}
	public StationMessage getSsta() {
		return ssta;
	}
	public void setSsta(StationMessage ssta) {
		this.ssta = ssta;
	}
	public StationMessage getEsta() {
		return esta;
	}
	public void setEsta(StationMessage esta) {
		this.esta = esta;
	}
	public String getStas() {
		return stas;
	}
	public void setStas(String stas) {
		this.stas = stas;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getTransportnum() {
		return transportnum;
	}
	public void setTransportnum(String transportnum) {
		this.transportnum = transportnum;
	}
       
}

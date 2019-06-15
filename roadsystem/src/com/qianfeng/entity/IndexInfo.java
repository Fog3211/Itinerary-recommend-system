package com.qianfeng.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class IndexInfo {
     String sta;
     String eta;
     String[] transports;
     String sorttype;
     @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
      Date cal;
     
	public Date getCal() {
		return cal;
	}
	public void setCal(Date cal) {
		this.cal = cal;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String[] getTransports() {
		return transports;
	}
	public void setTransports(String[] transports) {
		this.transports = transports;
	}
	public String getSorttype() {
		return sorttype;
	}
	public void setSorttype(String sorttype) {
		this.sorttype = sorttype;
	}
     
}

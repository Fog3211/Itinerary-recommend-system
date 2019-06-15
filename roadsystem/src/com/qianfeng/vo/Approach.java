package com.qianfeng.vo;

import java.sql.Timestamp;
import java.util.List;

public class Approach {
  Route route;
  int totallasttime;
  double totalprice;
public Route getRoute() {
	return route;
}
public void setRoute(Route route) {
	this.route = route;
}
public int getTotallasttime() {
	return totallasttime;
}
public void setTotallasttime(int totallasttime) {
	this.totallasttime = totallasttime;
}
public double getTotalprice() {
	return totalprice;
}
public void setTotalprice(double totalprice) {
	this.totalprice = totalprice;
}
  
@Override
public String toString() {
	String ss="";
	
	for(int i=0;i<route.getRoadMessages().size();i++) {
		
		if(i==route.getRoadMessages().size()-1) {
			ss+=route.getRoadMessages().get(i).getOffdate().toString()+route.getRoadMessages().get(i).getArrdate()+"|"
		+route.getRoadMessages().get(i).getStas()+"|"+route.getRoadMessages().get(i).getLasttime();
		}else {
			ss+=route.getRoadMessages().get(i).getOffdate().toString()+"|"+route.getRoadMessages().get(i).getArrdate()+"|"
		+route.getRoadMessages().get(i).getStas()+"|"+route.getRoadMessages().get(i).getLasttime()+"*";
			
		}
		
	}
	
    return "approach{" +
            "route=" + ss  +
            ", totallasttime=" + totallasttime +
             ", totalprice=" + totalprice +
            "}";
}

}

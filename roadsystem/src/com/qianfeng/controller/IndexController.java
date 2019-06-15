package com.qianfeng.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.IndexInfo;
import com.qianfeng.entity.Node;
import com.qianfeng.entity.Road;
import com.qianfeng.entity.RoadMap;
import com.qianfeng.entity.Station;
import com.qianfeng.service.IRailwayService;
import com.qianfeng.service.IRoadService;
import com.qianfeng.service.ISortService;
import com.qianfeng.service.IFlightService;
import com.qianfeng.service.impl.RailwayService;
import com.qianfeng.vo.Approach;
import com.qianfeng.vo.Approachs;
import com.qianfeng.vo.JsonBean;
import com.qianfeng.vo.SENode;

@Controller
public class IndexController {
     @Autowired
     private IRoadService roadService;
     @Autowired
     private IRailwayService railwayService;
     @Autowired
     private IFlightService flightService;
     /*@Autowired
     private ISortService sortService;*/
     
     @RequestMapping(value="roadindex", method=RequestMethod.POST)
	public @ResponseBody JsonBean RoadIndex(IndexInfo indexinfo) {
		System.out.println("RoadIndexIndex<<<<<<<<<<<<<");
		
		JsonBean  bean = new JsonBean();
		try{
			String ssta=indexinfo.getSta();
	    	 String esta=indexinfo.getEta();
	    	 String[] tports=indexinfo.getTransports();
	    	 String stype=indexinfo.getSorttype();
	    	 Date cal=indexinfo.getCal();
	    	 System.out.println(ssta+" "+esta+""+tports[0]+" "+cal);
	    	 System.out.println("here**************formate");
	    	 
	  		Station sta=roadService.findStationByName(ssta);
	 		Station eta=roadService.findStationByName(esta);
	 	   
	 		System.out.println(sta.getCity());
	 		System.out.println(eta.getCity());
	 		List<Station> stas =new ArrayList<Station>();
	 	         if(sta.getCity().equals(eta.getCity())) {
	 	        	 stas= roadService.findAllStopByCity(sta.getCity());
	 	         }else if(sta.getProvince().equals(eta.getProvince())) {
	 	        	 stas= roadService.findAllStopByProvince(sta.getProvince());
	 	         }else {
	 	        	 stas= roadService.findAllStop();
	 	         }
	 	         
	 			Road froad=roadService.findAllRoadByStas(ssta).get(0);
	 			Road eroad=roadService.findAllRoadByStas(esta).get(0);
	 			
	 			System.out.println(froad.getRdnum()+eroad.getRdnum());
	 			if(froad.getRdnum().equals(eroad.getRdnum())) {
	 				System.out.println("同一地铁区间");
	 				Approachs aps=roadService.findApproachsByblock(sta,eta,cal);
	 			    bean.setCode(1);
	 				bean.setMsg(aps);
	 				System.out.println(aps.getRoutemsgs().get(0).getTotalprice());
	 				
	 			}
	 			else {
	 				System.out.println("非同一地铁区间");
	 				
	 				SENode se= roadService.formatNode(stas,sta,eta,tports);
	 			  
	 				/*System.out.println(se.getSnode().getRelationNodes().get(1).getRelationNodes().get(1).getSta().getStaname());
	 				
	 				System.out.println(se.getSnode().getRelationNodes().get(1).getRelationNodes().get(2).getRelationNodes().get(1).getSta().getStaname());
	*/ 				Approachs aps=new Approachs();
	                
	                aps=roadService.findAllRoadByRoadMap(se.getSnode(),se.getEnode(),cal);
	                
	                for(Approach ap : aps.getRoutemsgs()) {
	                	 
	                	System.out.println(ap.toString());
	                }
	                System.out.println("nosort<<<<<<<<<<<<");
	               if(stype.equals("time")) {
	            	   Collections.sort(aps.getRoutemsgs(), new Comparator<Approach>() {
	                       public int compare( Approach o1,  Approach o2) {
	                       	
	                              if(o2.getTotallasttime()<o1.getTotallasttime()){
	                           	   return 1;
	                              }else {
	                           	   return -1;
	                              }
	                             
	                      }
	                   });
	               }
	               else if(stype.equals("price")) {
	            	   Collections.sort(aps.getRoutemsgs(), new Comparator<Approach>() {
	                       public int compare( Approach o1,  Approach o2) {
	                       	
	                              if(o2.getTotalprice()<o1.getTotalprice()){
	                           	   return 1;
	                              }else {
	                           	   return -1;
	                              }
	                             
	                      }
	                   });
	               }
	               else if(stype.equals("changnum")){
	            	   Collections.sort(aps.getRoutemsgs(), new Comparator<Approach>() {
	                       public int compare( Approach o1,  Approach o2) {
	                       	
	                              if(o2.getRoute().getChangenum()<o1.getRoute().getChangenum()){
	                           	   return 1;
	                              }else {
	                           	   return -1;
	                              }
	                             
	                      }
	                   });
	               }
	               System.out.println("rout<<<<<<"+aps.getRoutemsgs().size());
	                aps.setRoutemsgs(aps.getRoutemsgs());
	                for(Approach ap : aps.getRoutemsgs()) {
	                	 
	                	System.out.println(ap.toString());
	                }
	                bean.setCode(1);
	                bean.setMsg(aps);
	                aps=null;
	               // System.out.println(aps.getRoutemsgs().get(1).getTotallasttime()+"******aps*****"+aps.getRoutemsgs().get(0).getTotalprice());
	 				
	 			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			bean.setCode(0);
			bean.setMsg(e.getMessage());
			e.printStackTrace();
			
		}
    	 	
		return bean;
    	 
     }
     @RequestMapping(value="railway", method=RequestMethod.POST)
	 public @ResponseBody JsonBean RailwayIndex(String ssta,String esta) {
	    	System.out.println("RailwayIndex<<<<<<<<<<<<<");
	 		JsonBean  bean = new JsonBean();
	 		try {
	 			bean.setMsg(railwayService.findAllRailwayBySstaAndEsta(ssta, esta));
	 			bean.setCode(1);
	 			
	 		}catch (Exception e) {
				// TODO: handle exception
	 			bean.setCode(0);
	 			bean.setMsg(e.getMessage());
	 			e.printStackTrace();
			}
	 		return bean;
	     }
     
     @RequestMapping(value="flightindex", method=RequestMethod.POST)
	 public @ResponseBody JsonBean flightIndex(String sadress,String eadress) {
		    	System.out.println("flightIndex<<<<<<<<<<<<<");
		 		JsonBean  bean = new JsonBean();
		 		try {
		 			bean.setMsg(flightService.findAllFlightBySadressAndEadress(sadress, eadress));
		 			bean.setCode(1);
		 			
		 		}catch (Exception e) {
					// TODO: handle exception
		 			bean.setCode(0);
		 			bean.setMsg(e.getMessage());
		 			e.printStackTrace();
				}
		 		return bean;
		     }
}

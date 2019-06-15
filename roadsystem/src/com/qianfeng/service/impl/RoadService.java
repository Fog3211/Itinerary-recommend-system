package com.qianfeng.service.impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.accessibility.AccessibleRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EditorAwareTag;

import com.qianfeng.dao.IFlightDao;
import com.qianfeng.dao.IRailwayDao;
import com.qianfeng.dao.IRoadDao;
import com.qianfeng.dao.IStationDao;
import com.qianfeng.dao.ISubwayDao;
import com.qianfeng.entity.Flight;
import com.qianfeng.entity.Node;
import com.qianfeng.entity.Railway;
import com.qianfeng.entity.Road;
import com.qianfeng.entity.RoadMap;
import com.qianfeng.entity.Station;
import com.qianfeng.entity.Subway;

import com.qianfeng.service.IRoadService;
import com.qianfeng.service.ISubwayService;
import com.qianfeng.vo.Approach;
import com.qianfeng.vo.Approachs;
import com.qianfeng.vo.NodeMessage;
import com.qianfeng.vo.RoadMessage;
import com.qianfeng.vo.Route;
import com.qianfeng.vo.SENode;
import com.qianfeng.vo.StationMessage;
import com.qianfeng.vo.SubwayMessages;


@Service
public class RoadService implements IRoadService{

	@Autowired
	private IRoadDao roadDao;
	
	@Autowired
	private ISubwayDao subwayDao;

	@Autowired
	private IStationDao staDao;
	
	@Autowired
	private IRailwayDao railwayDao;
	
	@Autowired
	private IFlightDao flightDao;
	
	Approachs aprchs=new Approachs();//存储返回的方案集
	Route route=new Route();
	
	
	
	 //临时保存路径节点的栈 
	 public static Stack<Node> stack = new Stack<Node>();
	  //存储路径的集合 
	 public static ArrayList<Object[]> sers = new ArrayList<Object[]>();
	 
	  //判断节点是否在栈中 
	 public static boolean isNodeInStack(Node node)
	 {
	 Iterator<Node> it = stack.iterator();
	 while (it.hasNext()) {
	 Node node1 = (Node) it.next();
	 if (node == node1)
	 return true;
	 }
	 return false;
	 }
	 
	  //此时栈中的节点组成一条所求路径，转储并打印输出 
	 public static void showAndSavePath()
	 {
	 Object[] o = stack.toArray();
	 for (int i = 0; i < o.length; i++) {
	 Node nNode = (Node) o[i];
	 
	 if(i < (o.length - 1))
	 System.out.print(nNode.getSta().getStaname() + "->");
	 else
	 System.out.print(nNode.getSta().getStaname());
	 }
	 
	 sers.add(o);  //转储 
	 System.out.println("\n");
	 }
	 
	/* 
	 * 寻找路径的方法 
	 * cNode: 当前的起始节点currentNode
	 * pNode: 当前起始节点的上一节点previousNode
	 * sNode: 最初的起始节点startNode
	 * eNode: 终点endNode*/
	 
	 public static boolean getPaths(Node cNode, Node pNode, Node sNode, Node eNode) {
	 Node nNode = null;
	 //boolean path=false;
	  //如果符合条件判断说明出现环路，不能再顺着该路径继续寻路，返回false 
	 if (cNode != null && pNode != null && cNode == pNode)
	 return false;
	 
	 if (cNode != null) {
		 
	 int i = 0;
	 // 起始节点入栈 
	 stack.push(cNode);
	 // 如果该起始节点就是终点，说明找到一条路径 
	 if (cNode == eNode)
	 {
	 // 转储并打印输出该路径，返回true 
		 //path=true;
	 showAndSavePath();
	 return true;
	 }
	 // 如果不是,继续寻路 
	 else
	 {
	  
	  /** 从与当前起始节点cNode有连接关系的节点集中按顺序遍历得到一个节点
	  * 作为下一次递归寻路时的起始节点 */
	  
	 nNode = cNode.getRelationNodes().get(i);
	 while (nNode != null) {
	 /* 
	  * 如果nNode是最初的起始节点或者nNode就是cNode的上一节点或者nNode已经在栈中 ， 
	  * 说明产生环路 ，应重新在与当前起始节点有连接关系的节点集中寻找nNode*/
	  
	  if (pNode != null
	  && (nNode == sNode || nNode == pNode || isNodeInStack(nNode))) {
	  i++;
	  if (i >= cNode.getRelationNodes().size())
	  nNode = null;
	  else
	  nNode = cNode.getRelationNodes().get(i);
	  continue;
	  }
	   //以nNode为新的起始节点，当前起始节点cNode为上一节点，递归调用寻路方法 
	  if (getPaths(nNode, cNode, sNode, eNode)) //递归调用 
	  {
	  // 如果找到一条路径，则弹出栈顶节点 
	  stack.pop();
	  //path=true;
	  }
	   //继续在与cNode有连接关系的节点集中测试nNode 
	  i++;
	  if (i >= cNode.getRelationNodes().size())
	  nNode = null;
	  else
	  nNode = cNode.getRelationNodes().get(i);
	 }
	  
	  /** 当遍历完所有与cNode有连接关系的节点后，
	  * 说明在以cNode为起始节点到终点的路径已经全部找到 */
	  
	 stack.pop();

		 return false;
	 }
	 }
	 else {
		
			 return false;
		 
	 }
	 
	 }
	
	@Override
	public Approachs findAllRoadByRoadMap(Node sn,Node en,Date cal) {
		// TODO Auto-generated method stub
		System.out.println("here**************");
		Approachs aps=new Approachs();
		try{
			System.out.println("findAllRoadByRoadMap**************");
			stack.clear();
			sers.clear();
			getPaths(sn, null, sn, en);	
		   
			
		   if(sers.size()==0) {
			   throw new RuntimeException("未查到路线信息，亲");
		   }
		   else {//根据线路注入方案
			   List<Approach> aplist=new ArrayList<Approach>();
			   for(Approach ap : aplist) {
				   System.out.println(ap.toString());
			   }
			   
			  
			  
			   for(int i=0;i<sers.size();i++) {
			    	System.out.println(sers.size());
			    	Approach ap=new Approach();
			        Route  route=new Route();
			    	Object[] o=sers.get(i);
			    	List<Road> roads=new ArrayList<Road>();
			    	List<RoadMessage> roadmessages=new ArrayList<RoadMessage>();
			    	Road lastr=new Road();
			    	Boolean noroad=false;//判断根据出发时间是否有路
			    	
			    	for(int j=0;j<o.length-1;j++) {
			    		
			    		Node sNode=(Node)o[j];
			    		Node eNode=(Node)o[j+1];
			    		System.out.println(sNode.getSta().getStaname());
			    		System.out.println(eNode.getSta().getStaname());
			    		
			    		if(j==0) {
			    		    
			    			 Date tmpcal=new Date();
			    			 tmpcal.setTime(cal.getTime());
			    			System.out.println("findAllRoadByRoadMap**************j0");
			    			//System.out.println(sNode.getSta().getStop());
				    			if(sNode.getSta().getStop().equals("0")) {
				    				
				    				System.out.println("findAllRoadByRoadMap**************snode是站点");
				    				Road sr=roadDao.findRoadByStasAndEstaNum(sNode.getSta().getStaname(),eNode.getSta().getStaname());
				    				System.out.println(sr.getStas()+"PPPPPPPPPPPP&&&&&&");
				    				
				    				RoadMessage rmg = new RoadMessage();
				    				
				    				
				    				if(sr.getTransportnum().subSequence(0, 2).equals("dt")) {
				    					    Road tmpr=new Road(sr);
				    					    Date sdate=new Date();
						                    Date edate=new Date();
						                    Date arrdate =new Date();
						                    Time stime=roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
						                    Time etime=roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
						                    sdate.setHours(stime.getHours());
						                    sdate.setMinutes(stime.getMinutes());
						                    sdate.setSeconds(stime.getSeconds());
						                    
						                    edate.setHours(etime.getHours());
						                    edate.setMinutes(etime.getMinutes());
						                    edate.setSeconds(etime.getSeconds());
						               if(tmpcal.getTime()<edate.getTime()&&tmpcal.getTime()>sdate.getTime()) {
						                       System.out.println("j0***出发时间在发车区间");
					    					
						               }else {//出发时间不在地铁乘车区间内
						            	   noroad =true;	                    	
	                                               break;			 
						               }
						               System.out.println("dt**********j=0");
				    					Subway subway=subwayDao.findSubwayBySubNum(sr.getTransportnum());
				    					
				    					String[] stass=sr.getStas().split(" ");
				    					int index=-1;
				    					String stasss="";
				    					
				    					for(int p=0;p<stass.length;p++) {
				    						
				    						 if(stass[p].equals(sNode.getSta().getStaname())) {
				    							
				    							 index=p;
				    							 break;
				    						 }
				    					
				    					}
				    				   for(int p=index;p<stass.length;p++) {
				    					   
				    					   if(p==stass.length-1) {
				    						   stasss+=stass[p];
				    					   }else {
				    						   stasss+=stass[p]+" ";
				    					   }
				    				   }
				    				
				    		            
				    					tmpr.setStas(stasss);
				    					tmpr.setSstanum(sNode.getSta().getStaname());
				    					tmpr.setEstanum(eNode.getSta().getStaname());
				    					tmpr.setTransportnum(sr.getTransportnum());
				    					System.out.println(sr.getStacount()+"*"+index+"*******i=0&sta=stop&dt");
				    					tmpr.setStacount(sr.getStacount()-index);
				    					
				    					
				    					StationMessage sstamsg=new StationMessage();
					    				sstamsg.setStaname(tmpr.getSstanum());
					    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				
					    				StationMessage estamsg=new StationMessage();
					    				estamsg.setStaname(tmpr.getEstanum());
					    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
					    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
					    				
					    				rmg.setSsta(sstamsg);
					    				rmg.setEsta(estamsg);
					    				
					                    rmg.setLasttime((tmpr.getStacount()-1)*sr.getLasttime());
					                    
					                    rmg.setOffdate(tmpcal);
					                    arrdate.setTime(tmpcal.getTime()+rmg.getLasttime()*60*1000);
					                    rmg.setArrdate(arrdate);
					                    
					    				rmg.setPrice(subway.getPrice());
					    				rmg.setStas(tmpr.getStas());
					    				rmg.setTransportnum(tmpr.getTransportnum());
					    				System.out.println(rmg.getLasttime()+"*"+rmg.getTransportnum()+"*******i=0&sta=stop&dt");
					    				
					    				roadmessages.add(rmg);
					    			    System.out.println(tmpr.getStas());
					       
					    			    lastr=tmpr;
						            
					    				
				    				}
				    				/*else if(sr.getTransportnum().subSequence(0, 1).equals("G")) {
				    					
				    					Railway railway=railwayDao.findRailwayByTNum(sr.getTransportnum());
					                    
				    					
					    				rmg.setPrice(railway.getSseat());
					    				
					    				roadmessages.add(rmg);
				    				}
				    				else {
				    					Flight flight = flightDao.findFlightByFlyNum(sr.getTransportnum());
				    					  
					    				rmg.setPrice(flight.getPrice());
					    				roadmessages.add(rmg);
				    				}*/
				    				
				    		        System.out.println(lastr.getStas()+"*"+lastr.getSstanum()+"*"+lastr.getEstanum()+"**********j=0");
				    			}
				    			else {//第一个站点不是stop
				    				System.out.println("j0*******第一个站点不是stop");
				    				Road r=roadDao.findRoadBySstaNumAndEstaNum(sNode.getSta().getStaname(),eNode.getSta().getStaname());
				    				
				    				RoadMessage rmg=new RoadMessage();
				    				
				    				
				    				if(r.getTransportnum().subSequence(0, 2).equals("dt")) {
				    					
				    					
				    					    Road tmpr=new Road(r);
				    					    Date sdate=new Date();
						                    Date edate=new Date();
						                    Date arrdate =new Date();
						                    Time stime=roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
						                    Time etime=roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
						                    System.out.println(stime+"|"+etime+"*********");
						                  /*  sdate.set*/
						           
						                    sdate.setHours(stime.getHours());
						                    sdate.setMinutes(stime.getMinutes());
						                    sdate.setSeconds(stime.getSeconds());
						                    
						                    edate.setHours(etime.getHours());
						                    edate.setMinutes(etime.getMinutes());
						                    edate.setSeconds(etime.getSeconds());
						        
						                    System.out.println(tmpcal+"|"+sdate+"|"+edate+"*********"+(tmpcal.getTime()>stime.getTime())+" "+tmpcal.getTime()+"|"+sdate.getTime()+tmpcal.before(edate));
						                if(tmpcal.getTime()<edate.getTime()&&tmpcal.getTime()>sdate.getTime()) {//出发时间在地铁发车区间内
						                	 Subway subway=subwayDao.findSubwayBySubNum(r.getTransportnum());
						    					rmg.setPrice(subway.getPrice());
						    					
						    					StationMessage sstamsg=new StationMessage();
							    				sstamsg.setStaname(tmpr.getSstanum());
							    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
							    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
							    				
							    				StationMessage estamsg=new StationMessage();
							    				estamsg.setStaname(tmpr.getEstanum());
							    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
							    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
						    					
							    				
							    				
							    				rmg.setSsta(sstamsg);
							    				rmg.setEsta(estamsg);
						    					rmg.setLasttime((tmpr.getStacount()-1)*tmpr.getLasttime());
						    					rmg.setOffdate(tmpcal);
							                  
							                    arrdate.setTime(tmpcal.getTime()+rmg.getLasttime()*60*1000);
							                    rmg.setArrdate(arrdate);
						    					rmg.setStas(tmpr.getStas());
						    					rmg.setTransportnum(tmpr.getTransportnum());;
							    				
							    				roadmessages.add(rmg);
							    				lastr=tmpr;
						                }else {
						                	noroad =true;
						                	break;
						                }
						                    
						               
				    				}
				    				else if(r.getTransportnum().subSequence(0, 1).equals("G")) {
				    					
					  
				    					Road tmpr=new Road(r);
				    					
				    					Railway rrailway=railwayDao.findRailwayByTNum(tmpr.getTransportnum());
						    			List<Railway> railways=railwayDao.findAllRailwayBySstaAndEsta(rrailway.getSsta(), rrailway.getEsta());
						    			Boolean rflag=false;//判断是否有合适高铁（根据出发时间）
						    			Collections.sort(railways, new Comparator<Railway >() {
						                       public int compare(Railway o1,  Railway  o2) {
						                       	
						                              if(o2.getStime().getTime()<o1.getStime().getTime()){
						                           	   return 1;
						                              }else {
						                           	   return -1;
						                              }
						                             
						                      }
						                   });
						    			for(Railway railway : railways) {
						    				
						    				Date sdate=new Date();
						    				sdate.setHours(railway.getStime().getHours());
						    			    sdate.setMinutes(railway.getStime().getMinutes());
						    			    sdate.setSeconds(railway.getStime().getSeconds());
						    			    Date edate=new Date();
						    			    edate.setHours(railway.getEtime().getHours());
						    			    edate.setMinutes(railway.getEtime().getMinutes());
						    			    edate.setSeconds(railway.getEtime().getSeconds());
						    			    if(!(sdate.getTime()<edate.getTime())) {//到达时间越过当日时间如： 23：30 ——次日00：30
						    			    	edate.setDate(edate.getDate()+1);
						    				}
						    			    System.out.println("j=0***!stop******G"+tmpcal+"|"+sdate+"|"+edate+"*********");
							    			if(tmpcal.getTime()<sdate.getTime()) {//判断出发时间是否在发车时间之前
							    				rmg.setPrice(railway.getSseat());
							    				rmg.setLasttime((int)(edate.getTime()-sdate.getTime())/(60*1000));
							    				rmg.setTransportnum(railway.getTnum());
							    				rmg.setOffdate(sdate);
							    				rmg.setArrdate(edate);
							    				rflag=true;
							    				break;
							    			}
						    			}
						    			if(rflag==false) {
						    				noroad =true;
						    				break;
						    			}
						    					
						    		    
					    				StationMessage sstamsg=new StationMessage();
					    				sstamsg.setStaname(tmpr.getSstanum());
					    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				
					    				StationMessage estamsg=new StationMessage();
					    				estamsg.setStaname(tmpr.getEstanum());
					    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
					    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
				    					
					    				
					    				
					    				rmg.setSsta(sstamsg);
					    				rmg.setEsta(estamsg);
				    					rmg.setStas(tmpr.getStas());
					    				roadmessages.add(rmg);
					    				lastr=tmpr;
					    				
					    				
				    				}
				    				else {
				    					
	                                    Road tmpr=new Road(r);
				    					
	                                   
						    			List<Flight> flights=flightDao.findAllFlightBySadressAndEadress(tmpr.getSstanum(), tmpr.getEstanum());
						    			Boolean rflag=false;//判断是否有合适高铁从而辨别路线合不合适（根据出发时间）
						    			Collections.sort(flights, new Comparator<Flight >() {
						                       public int compare(Flight o1,  Flight  o2) {
						                       	
						                              if(o2.getStime().getTime()<o1.getStime().getTime()){
						                           	   return 1;
						                              }else {
						                           	   return -1;
						                              }
						                             
						                      }
						                   });
						    			for(Flight flight : flights) {
						    				
						    				Date sdate=new Date();
						    				sdate.setHours(flight.getStime().getHours());
						    			    sdate.setMinutes(flight.getStime().getMinutes());
						    			    sdate.setSeconds(flight.getStime().getSeconds());
						    			    Date edate=new Date();
						    			    edate.setHours(flight.getEtime().getHours());
						    			    edate.setMinutes(flight.getEtime().getMinutes());
						    			    edate.setSeconds(flight.getEtime().getSeconds());
						    			    if(!(sdate.getTime()<edate.getTime())) {//到达时间越过当日时间如： 23：30 ——次日00：30
						    			    	edate.setDate(edate.getDate()+1);
						    				}
						    			    System.out.println("j=0***!stop******flight"+tmpcal+"|"+sdate+"|"+edate+"*********");
							    			if(tmpcal.getTime()<sdate.getTime()) {//判断出发时间是否在发车时间之前
							    				rmg.setPrice(flight.getPrice());
							    				rmg.setLasttime((int)(edate.getTime()-sdate.getTime())/(60*1000));
							    				rmg.setTransportnum(flight.getFlynum());
							    				rmg.setOffdate(sdate);
							    				rmg.setArrdate(edate);
							    				rflag=true;
							    				break;
							    			}
						    			}
						    			if(rflag==false) {
						    				noroad =true;
						    				break;
						    			}
	                                    
					    				StationMessage sstamsg=new StationMessage();
					    				sstamsg.setStaname(tmpr.getSstanum());
					    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				
					    				StationMessage estamsg=new StationMessage();
					    				estamsg.setStaname(tmpr.getEstanum());
					    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
					    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
				    					
					    				
					    				
					    				rmg.setSsta(sstamsg);
					    				rmg.setEsta(estamsg);
				    					
				    					rmg.setStas(tmpr.getStas());
				    					
					    				
					    				roadmessages.add(rmg);
					    				lastr=tmpr;
				    				}
				    				
				    				
				    			}
				    			
				    			System.out.println("findAllRoadByRoadMap**************j0"+" "+roadmessages.get(0).getPrice());
				    			
			    		}else {
			    			//j!=0时**********************
			    			System.out.println("findAllRoadByRoadMap**************j!=0");
			    			Date tmpcal=new Date();
		    				tmpcal.setTime(roadmessages.get(roadmessages.size()-1).getArrdate().getTime());
			    		    
			    			if(eNode.getSta().getStop().equals("0")){
			    				System.out.println("findAllRoadByRoadMap**************enode是站点");
			    				Road er=roadDao.findRoadBySstaNumAndStas(sNode.getSta().getStaname(),eNode.getSta().getStaname());
			    				
			    				
			    				RoadMessage rmg=new RoadMessage();
			    				
			    				if(er.getTransportnum().subSequence(0, 2).equals("dt")){
			    					
			    					Road tmpr=new Road(er);
			    					
			    					 Subway subway=subwayDao.findSubwayBySubNum(er.getTransportnum());
				    					if(er.getTransportnum().equals(lastr.getTransportnum())) {
				    						
					    					String[] stass=er.getStas().split(" ");
					    					int index=-1;
					    					String stasss="";
					    					int p=0;
					    					for(String sta : stass) {
					    						
					    						 if(sta.equals(eNode.getSta().getStaname())) {
					    							 stasss+=sta;
					    							 index=p;
					    							 break;
					    						 }
					    						 if(p!=0) {
					    							 stasss+=sta+" ";
					    						 }
					    						 p++;
					    					}
					    					String stas=lastr.getStas()+" "+stasss;
					    					
					    				tmpr.setRdnum(lastr.getRdnum());	
					    				tmpr.setStas(stas);
					    				tmpr.setSstanum(lastr.getSstanum());
					    				tmpr.setEstanum(stass[index]);
					    				tmpr.setStacount(lastr.getStacount()+index);
					    				
					    				
					    				Date offdate=new Date();
					    				offdate.setTime(roadmessages.get(roadmessages.size()-1).getOffdate().getTime());
					    				rmg.setOffdate(offdate);
					    		        Date arrdate =new Date();
					    		        arrdate.setTime(offdate.getTime()+tmpr.getStacount()*tmpr.getLasttime()*60*1000);
					                    rmg.setArrdate(arrdate);
					    				
					    				roadmessages.remove(roadmessages.size()-1);
					    				
					    				}else {
					    					String[] stass=er.getStas().split(" ");
					    					int index=0;
					    					String stasss="";
					    					int p=0;
					    					for(String sta : stass) {
					    						
					    						 if(sta.equals(eNode.getSta().getStaname())) {
					    							 stasss+=sta;
					    							 index=p;
					    							 break;
					    						 }else{
					    							 stasss+=sta+" ";
					    						 }
					    						 p++;
					    					}
					    					
					    						
						    				tmpr.setStas(stasss);
						    				
						    				tmpr.setEstanum(stass[index]);
						    				tmpr.setStacount(index+1);
						    				
						    				
						    				
						    				
						    				Date sdate=new Date();
						                    Date edate=new Date();
						                   
						                    Time stime=roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
						                    Time etime=roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
						                    sdate.setHours(stime.getHours());
						                    sdate.setMinutes(stime.getMinutes());
						                    sdate.setSeconds(stime.getSeconds());
						                    
						                    edate.setHours(etime.getHours());
						                    edate.setMinutes(etime.getMinutes());
						                    edate.setSeconds(etime.getSeconds());
						                    System.out.println(tmpcal+"|"+sdate+"|"+sdate+"*********");
						              if(tmpcal.getTime()<edate.getTime()&&tmpcal.getTime()>sdate.getTime()) {
						            	    System.out.println("j!=0***stop***dt出发时间在乘车区间");
						            	    String staname = tmpr.getSstanum();
						    				String sroadnum = lastr.getTransportnum();
						    				String eroadnum = tmpr.getTransportnum();
						            	    Date offdate=new Date();
						    				offdate.setTime(tmpcal.getTime()+roadDao.findChangeTimeByStaNameAndSroadNumAndEroadNum(staname, sroadnum, eroadnum)*60*1000);
						    				rmg.setOffdate(offdate);
						    		        Date arrdate =new Date();
						                    arrdate.setTime(offdate.getTime()+rmg.getLasttime()*60*1000);
						                    rmg.setArrdate(arrdate);
						              }else {
						            	  noroad =true;
						                	break;
						              }
					    		}
		                                
				    					

				    					StationMessage sstamsg=new StationMessage();
					    				sstamsg.setStaname(tmpr.getSstanum());
					    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				
					    				StationMessage estamsg=new StationMessage();
					    				estamsg.setStaname(tmpr.getEstanum());
					    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    					
				    					rmg.setSsta(sstamsg);
					    				rmg.setEsta(estamsg);
					    				rmg.setPrice(subway.getPrice());
					    				rmg.setStas(tmpr.getStas());
					    				rmg.setTransportnum(tmpr.getTransportnum());
					    				rmg.setLasttime((tmpr.getStacount()-1)*tmpr.getLasttime());
					    				
					    				
					    				
					    				roadmessages.add(rmg);
					    				lastr=tmpr;
			    					
			    					    
			    					
			    				}//高铁
			    				/*else if(er.getTransportnum().subSequence(0, 1).equals("G")) {
			    					
			    					Railway railway=railwayDao.findRailwayByTNum(er.getTransportnum());
			    					
			    					Road tmpr=new Road(er);
			    					StationMessage sstamsg=new StationMessage();
				    				sstamsg.setStaname(tmpr.getSstanum());
				    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				
				    				StationMessage estamsg=new StationMessage();
				    				estamsg.setStaname(tmpr.getEstanum());
				    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				                 
				    				rmg.setSsta(sstamsg);
				    				rmg.setEsta(estamsg);
				    				rmg.setStas(tmpr.getStas());
				    				rmg.setTransportnum(tmpr.getTransportnum());
				    				rmg.setPrice(railway.getSseat());
				    				rmg.setLasttime((er.getStacount()-1)*er.getLasttime());
				    				roadmessages.add(rmg);
				    				
				    				lastr=tmpr;
				    				
			    				}//飞机
			    				else {
			    					
			    					Flight flight = flightDao.findFlightByFlyNum(er.getTransportnum());
			    					  
			    					Road tmpr=new Road(er);
			    					StationMessage sstamsg=new StationMessage();
				    				sstamsg.setStaname(tmpr.getSstanum());
				    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				
				    				StationMessage estamsg=new StationMessage();
				    				estamsg.setStaname(tmpr.getEstanum());
				    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				                 
				    				rmg.setSsta(sstamsg);
				    				rmg.setEsta(estamsg);
				    				rmg.setStas(tmpr.getStas());
				    				rmg.setTransportnum(tmpr.getTransportnum());
				    				rmg.setPrice(flight.getPrice());
				    				rmg.setLasttime((er.getStacount()-1)*er.getLasttime());
				    				roadmessages.add(rmg);
				    				
				    				lastr=tmpr;
			    				}*/
			    				 
			    			}
			    			else {
			    				//System.out.println("<<<<<<<<<<<<<kkkk");
			    				System.out.println(sNode.getSta().getStaname()+"<<<<<<<<<<<<<"+eNode.getSta().getStaname());
			    				Road r=roadDao.findRoadBySstaNumAndEstaNum(sNode.getSta().getStaname(),eNode.getSta().getStaname());
			    				
			    				//Road rs=roadDao.findRoadBySstaNumAndEstaNum(sNode.getSta().getStaname(),eNode.getSta().getStaname());
			    				System.out.println(r.getStas());
			    				RoadMessage rmg=new RoadMessage();
			    				
			    				if(r.getTransportnum().subSequence(0, 2).equals("dt")) {
			    					
			    					
			    					Road tmpr=new Road(r);
			    					 
					            	  Subway subway=subwayDao.findSubwayBySubNum(r.getTransportnum());
				    					System.out.println(r.getStas()+"<<<<<<<<<<<<<");
				                       if(r.getTransportnum().equals(lastr.getTransportnum())) {
				                    	   System.out.println(lastr.getSstanum()+"*"+lastr.getEstanum()+"<<<<<<<<<<<<<lastr");
				                    	   System.out.println(r.getSstanum()+"*"+r.getEstanum()+"<<<<<<<<<<<<<r");
					    					String[] stass=r.getStas().split(" ");
					    					int index=0;
					    					String stasss="";
					    					int p=0;
					    					for(String sta : stass) {
					    					
					    						 if(p!=0) {
					    							 if(p==stass.length-1) {
					    								 stasss+=sta;
					    							 }else {
					    								 stasss+=sta+" ";
					    							 }
					    							
					    						 }
					    						 p++;
					    					}
					    				
					    					System.out.println(stasss+"<<<<<<<<<<<<<");
					    					
					    					String stas=lastr.getStas()+" "+stasss;
					    					 System.out.println(lastr.getStas()+"*"+stas);
					    					 
					    					tmpr.setRdnum(lastr.getRdnum()); 
					    					tmpr.setStas(stas);
					    					tmpr.setSstanum(lastr.getSstanum());
					    					tmpr.setEstanum(r.getEstanum());
					    					tmpr.setStacount(r.getStacount()+lastr.getStacount()-1);
					                        
					    					Date offdate=new Date();
						    				offdate.setTime(roadmessages.get(roadmessages.size()-1).getOffdate().getTime());
						    				rmg.setOffdate(offdate);
						    		        Date arrdate =new Date();
						                    arrdate.setTime(offdate.getTime()+tmpr.getStacount()*tmpr.getLasttime()*60*1000);
						                    
						                    rmg.setArrdate(arrdate);
					    					roadmessages.remove(roadmessages.size()-1);
					    				}else {
					    					  Date sdate=new Date();
							                    Date edate=new Date();
							                   
							                    Time stime=roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
							                    Time etime=roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
							                    sdate.setHours(stime.getHours());
							                    sdate.setMinutes(stime.getMinutes());
							                    sdate.setSeconds(stime.getSeconds());
							                    
							                    edate.setHours(etime.getHours());
							                    edate.setMinutes(etime.getMinutes());
							                    edate.setSeconds(etime.getSeconds());
							                    
							                  System.out.println("j!=0**!stop***dt**"+tmpcal+"|"+sdate+"|"+edate+"*********");
							              if(tmpcal.getTime()<edate.getTime()&&tmpcal.getTime()>sdate.getTime()) {//出发时间在地铁乘车区间
							            	  System.out.println("j!=0**!stop**出发时间在地铁乘车区间");
							            	  
							            		String staname = tmpr.getSstanum();
							    				String sroadnum = lastr.getTransportnum();
							    				String eroadnum = tmpr.getTransportnum();
							            	    Date offdate=new Date();
							    				offdate.setTime(tmpcal.getTime()+roadDao.findChangeTimeByStaNameAndSroadNumAndEroadNum(staname, sroadnum, eroadnum)*60*1000);
							    				rmg.setOffdate(offdate);
							    		        Date arrdate =new Date();
							                    arrdate.setTime(offdate.getTime()+rmg.getLasttime()*60*1000);
							                    rmg.setArrdate(arrdate);
							            	
							              }else {//出发时间不在地铁乘车区间
							            	  System.out.println("出发时间不在地铁乘车区间");
							            	  noroad=true;
							            	  break;
							              }
					    				}
				                       System.out.println(tmpr.getStas()+"*"+tmpr.getSstanum()+"*"+tmpr.getEstanum()+"<<<<<<<<<<<<<");
				    					StationMessage sstamsg=new StationMessage();
					    				sstamsg.setStaname(tmpr.getSstanum());
					    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				
					    				StationMessage estamsg=new StationMessage();
					    				estamsg.setStaname(tmpr.getEstanum());
					    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
					    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getEstanum()));
					                 
					    				System.out.println(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum())+"****j!=0&stop&dt&stime");
					    				System.out.println(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
					    				
					    				
					    				rmg.setSsta(sstamsg);
					    				rmg.setEsta(estamsg);
					    				rmg.setPrice(subway.getPrice());
					    				
					    				rmg.setStas(tmpr.getStas());
					    				rmg.setLasttime((tmpr.getStacount()-1)*r.getLasttime());
					    				
					    				rmg.setTransportnum(subway.getSubnum());
					    				
					    				roadmessages.add(rmg);
					    				
					    				lastr=tmpr;
				                       
				                       
				                     
				                       
			    					
			    					 
			    					
			                       
			    				}
			    				else if(r.getTransportnum().subSequence(0, 1).equals("G")) {
			    					
				                 
			    					Road tmpr=new Road(r);
			    					Railway rrailway=railwayDao.findRailwayByTNum(tmpr.getTransportnum());
					    			List<Railway> railways=railwayDao.findAllRailwayBySstaAndEsta(rrailway.getSsta(), rrailway.getEsta());
					    			Boolean rflag=false;//判断是否有合适高铁（根据出发时间）
					    			Collections.sort(railways, new Comparator<Railway >() {
					                       public int compare(Railway o1,  Railway  o2) {
					                       	
					                              if(o2.getStime().getTime()<o1.getStime().getTime()){
					                           	   return 1;
					                              }else {
					                           	   return -1;
					                              }
					                             
					                      }
					                   });
					    			for(Railway railway : railways) {
					    				
					    				Date sdate=new Date();
					    				sdate.setHours(railway.getStime().getHours());
					    			    sdate.setMinutes(railway.getStime().getMinutes());
					    			    sdate.setSeconds(railway.getStime().getSeconds());
					    			    Date edate=new Date();
					    			    edate.setHours(railway.getEtime().getHours());
					    			    edate.setMinutes(railway.getEtime().getMinutes());
					    			    edate.setSeconds(railway.getEtime().getSeconds());
					    			    if(!(sdate.getTime()<edate.getTime())) {//到达时间越过当日时间如： 23：30 ——次日00：30
					    			    	edate.setDate(edate.getDate()+1);
					    				}
					    			    System.out.println("j!=0***!stop******G"+tmpcal+"|"+sdate+"|"+edate+"*********");
					    			    
						    			if(tmpcal.getTime()<sdate.getTime()) {//判断出发时间是否在发车时间之前
						    				rmg.setPrice(railway.getSseat());
						    				rmg.setLasttime((int)(edate.getTime()-sdate.getTime())/(60*1000));
						    				rmg.setTransportnum(railway.getTnum());
						    				rmg.setOffdate(sdate);
						    				rmg.setArrdate(edate);
						    				rflag=true;
						    				break;
						    			}
					    			}
					    			if(rflag==false) {
					    				noroad =true;
					    				break;
					    			}
			    					
			    					StationMessage sstamsg=new StationMessage();
				    				sstamsg.setStaname(tmpr.getSstanum());
				    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				
				    				StationMessage estamsg=new StationMessage();
				    				estamsg.setStaname(tmpr.getEstanum());
				    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				                 
				    				rmg.setSsta(sstamsg);
				    				rmg.setEsta(estamsg);
				    				rmg.setStas(tmpr.getStas());
				    			    
				    				roadmessages.add(rmg);
				    				
				    				lastr=tmpr;
			    				}
			    				else {
			    					
			    					  
			    					Road tmpr=new Road(r);
			    					
			    					List<Flight> flights=flightDao.findAllFlightBySadressAndEadress(tmpr.getSstanum(), tmpr.getEstanum());
					    			Boolean rflag=false;//判断是否有合适高铁从而辨别路线合不合适（根据出发时间）
					    			Collections.sort(flights, new Comparator<Flight >() {
					                       public int compare(Flight o1,  Flight  o2) {
					                       	
					                              if(o2.getStime().getTime()<o1.getStime().getTime()){
					                           	   return 1;
					                              }else {
					                           	   return -1;
					                              }
					                             
					                      }
					                   });
					    			for(Flight flight : flights) {
					    				
					    				Date sdate=new Date();
					    				sdate.setHours(flight.getStime().getHours());
					    			    sdate.setMinutes(flight.getStime().getMinutes());
					    			    sdate.setSeconds(flight.getStime().getSeconds());
					    			    Date edate=new Date();
					    			    edate.setHours(flight.getEtime().getHours());
					    			    edate.setMinutes(flight.getEtime().getMinutes());
					    			    edate.setSeconds(flight.getEtime().getSeconds());
					    			    if(!(sdate.getTime()<edate.getTime())) {//到达时间越过当日时间如： 23：30 ——次日00：30
					    			    	edate.setDate(edate.getDate()+1);
					    				}
					    			    System.out.println("j!=0***!stop******flight"+tmpcal+"|"+sdate+"|"+edate+"*********");
					    			   
						    			if(tmpcal.getTime()<sdate.getTime()) {//判断出发时间是否在发车时间之前
						    				rmg.setPrice(flight.getPrice());
						    				rmg.setLasttime((int)(edate.getTime()-sdate.getTime())/(60*1000));
						    				rmg.setTransportnum(flight.getFlynum());
						    				rmg.setOffdate(sdate);
						    				rmg.setArrdate(edate);
						    				rflag=true;
						    				break;
						    			}
					    			}
					    			if(rflag==false) {
					    				noroad =true;
					    				break;
					    			}
			    					StationMessage sstamsg=new StationMessage();
				    				sstamsg.setStaname(tmpr.getSstanum());
				    				sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				
				    				StationMessage estamsg=new StationMessage();
				    				estamsg.setStaname(tmpr.getEstanum());
				    				estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				    				estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum()));
				                 
				    				rmg.setSsta(sstamsg);
				    				rmg.setEsta(estamsg);
				    				rmg.setStas(tmpr.getStas());
				    				
				    				
				    				
				    				roadmessages.add(rmg);
				    				
				    				lastr=tmpr;
			    				}
			    				
			   
			    				
			    			}
			    			
			    		}
			    		
			    			
			    		
			    		//if(findRoad(sNode.getSta(),eNode.getSta());
			    	}
			    	System.out.println("i**************"+i);
			    	
			    	if(!noroad) {
			    		route.setRoadMessages(roadmessages);
				    	route.setChangenum(roadmessages.size()-1);
				    	ap.setRoute(route);
				    	
				    	int totallasttime=(int) (route.getRoadMessages().get(route.getRoadMessages().size()-1).getArrdate().getTime()-route.getRoadMessages().get(0).getOffdate().getTime())/(60*1000);
				    	double totalprice=0;
				    	for(int p=0;p<ap.getRoute().getRoadMessages().size();p++) {
				    		System.out.print(ap.getRoute().getRoadMessages().get(p).getStas()+"*");
				    	}
				    	System.out.print("******ap\n");
				    	
				    	
				    	for(int l=0;l<route.getChangenum()+1;l++) {
				    		
				    		
				    		totalprice+=route.getRoadMessages().get(l).getPrice();
				    		System.out.print(route.getRoadMessages().get(l).getLasttime()+"*"+
				    		route.getRoadMessages().get(l).getPrice()+"*"+
				    		route.getRoadMessages().get(l).getTransportnum()+"*"+
				    		route.getRoadMessages().get(l).getSsta().getStime()+
				    		route.getRoadMessages().get(l).getSsta().getEtime()+"******ap\n");
				    	}
				    	
				    	System.out.print(totallasttime+"*"+totalprice+"<<<<<<ap\n");
				    	ap.setTotallasttime(totallasttime);
				    	ap.setTotalprice(totalprice);
				    	aplist.add(ap);
			    	}
			    	
			    	
			    }//sers.sixe!=0
			   for(Approach ap : aplist) {
				   System.out.println(ap.toString());
			   }
			   
			   aps.setRoutemsgs(aplist);
			   
		   }
		    
		   
			
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return aps;
	}
	
     

	@Override
	public  String findLocalType(String ssta, String esta) {
		// TODO Auto-generated method stub
		Station sta=staDao.findStationByName(ssta);
		Station eta=staDao.findStationByName(esta);
		System.out.println(sta.getProvince());
		System.out.println(eta.getProvince());
	
		if(sta.getCity()== eta.getCity()) {
			return sta.getCity()+" "+"city";
		}
		else {
			if(sta.getProvince()== eta.getProvince()) {
				return sta.getProvince();
			}
			else {
				return "中国";
			}
		}
		
	}

	@Override
	public List<Road> findAllRoadByStas(String ssta) {
		// TODO Auto-generated method stub
		
		List<Road>   bs = roadDao.findAllRoadByStas(ssta,'m');
        if(bs.isEmpty()) {
        	bs= roadDao.findAllRoadByStas(ssta,'s');
        	if(bs.isEmpty()) {
        		bs= roadDao.findAllRoadByStas(ssta,'e');
        	}
        }	   
		return bs;
	}

	@Override
	public Station findStationByName(String ssta) {
		// TODO Auto-generated method stub
		return staDao.findStationByName(ssta);
	}

	@Override
	public List<Station> findAllStationByCity(String city) {
		// TODO Auto-generated method stub
		return staDao.findAllStationByCity(city);
	}

	@Override
	public SENode formatNode(List<Station> stas, Station ssta, Station esta,String[] tports){
		// TODO Auto-generated method stub
		  System.out.println("formatNode*********");
		  SENode se=new SENode();
		  
		/* Road sroad=roadDao.findRoadByStas(ssta.getStaname());
		 Road eroad=roadDao.findRoadByStas(ssta.getStaname());*/
		 
		/*int[][] nodeRalation= new int[][];*/
		 ArrayList<ArrayList<Integer>> nodeRalation=new ArrayList<ArrayList<Integer>>();
		/*=
			 {
				 {1},  //0
				 {0,5,2,3},//1
				 {1,4,3}, //2
				 {1,4,2}, //3
				 {2,3,5}, //4
				 {1,4,6,7},  //5
				 {5,7},
				 {6,5,8},
				 {7},
				 {10},
				 {9,11,12},
				 {10,12},
				 {10,11}
			 };*/
		int flag=0,eflag=0,fsi=-1,fei=-1,esi=-1,eei=-1,fi=-1,ei=-1;
		
		
		Road froad=findAllRoadByStas(ssta.getStaname()).get(0);
		Road efroad=findAllRoadByStas(esta.getStaname()).get(0);
		
		for(int i=0;i<stas.size();i++) {
			//记录初始，终点站点坐标
			if(ssta.getStaname().equals(stas.get(i).getStaname())&&flag==0) {
				flag=1;
				fi=i;
			}
			if(esta.getStaname().equals(stas.get(i).getStaname())&&eflag==0) {
				eflag=1;
				ei=i;
			}
			List<Road> iroad=roadDao.findAllRoadBySsta(stas.get(i).getStaname());
			if(froad.getSstanum().equals(stas.get(i).getStaname())&&fsi==-1) {
				fsi=i;
			}
			if(froad.getEstanum().equals(stas.get(i).getStaname())&&fei==-1) {
				fei=i;
			}
			if(efroad.getSstanum().equals(stas.get(i).getStaname())&&esi==-1) {
				esi=i;
			}
			if(efroad.getEstanum().equals(stas.get(i).getStaname())&&eei==-1) {
				eei=i;
			}
			
			ArrayList<Integer> noder=new ArrayList<Integer>();
			for(int j=0;j<iroad.size();j++) {
		             int index=-1;
				    for(int o=0;o<stas.size();o++) {
				    	if(stas.get(o).getStaname().equals(iroad.get(j).getEstanum())) {//判断站点与路径是否匹配
				    		int tportflag=0;
				    		for(int p=0;p<tports.length;p++) {
				    			if(iroad.get(j).getTransportnum().subSequence(0, 2).equals("dt")&&tports[p].equals("subway")) {
				    				tportflag=1;
				    				break;
				    			}else if(iroad.get(j).getTransportnum().subSequence(0, 1).equals("G")&&tports[p].equals("railway")) {
				    				tportflag=1;
				    				break;
				    			}else if(!(iroad.get(j).getTransportnum().subSequence(0, 1).equals("G"))&&
				    					!(iroad.get(j).getTransportnum().subSequence(0, 2).equals("dt"))&&tports[p].equals("flight")){
				    				tportflag=1;
				    				break;
				    			}
						    		
				    		}
				    		if(tportflag==1) {//判读站点的路径是否在交通搜索域中
				    			index=o;//记录站点坐标
				    		}
				    		break;
				    	}
				    		
				    }
				    if(index!=-1)
					noder.add(index);
		
			}
			nodeRalation.add(noder);
		}
      if(flag!=1) {
    	
    		ArrayList<Integer> noder=new ArrayList<Integer>();
    		noder.add(fsi);
    		noder.add(fei);
    		nodeRalation.add(noder);
    		
    		noder.remove(noder);
    		System.out.println(noder.get(0)+"************");
    		for(int i=0;i<nodeRalation.get(fsi).size();i++) {
    			if(nodeRalation.get(fsi).get(i).equals(fei))
    			nodeRalation.get(fsi).remove(i);
    		}
    		for(int i=0;i<nodeRalation.get(fei).size();i++) {
    			if(nodeRalation.get(fei).get(i).equals(fsi))
    			nodeRalation.get(fei).remove(i);
    		}
    		
    		nodeRalation.get(fsi).add(nodeRalation.size()-1);
    		nodeRalation.get(fei).add(nodeRalation.size()-1);
      }
      
      if(eflag!=1) {
    		System.out.println("eflag************");
    	  ArrayList<Integer> noder=new ArrayList<Integer>();
  		noder.add(esi);
  		noder.add(eei);
  		nodeRalation.add(noder);
  		
  		noder.remove(noder);
  		System.out.println(noder.get(0)+"************");
  		
  		for(int i=0;i<nodeRalation.get(esi).size();i++) {
			if(nodeRalation.get(esi).get(i).equals(eei))
			nodeRalation.get(esi).remove(i);
		}
		for(int i=0;i<nodeRalation.get(eei).size();i++) {
			if(nodeRalation.get(eei).get(i).equals(esi))
			nodeRalation.get(eei).remove(i);
		}
  		nodeRalation.get(esi).add(nodeRalation.size()-1);
  		nodeRalation.get(eei).add(nodeRalation.size()-1);  	
    	  
      }
      for(int i=0;i<nodeRalation.size();i++) {
    	  for(int j=0;j<nodeRalation.get(i).size();j++) {
    		  if(j!=nodeRalation.get(i).size()-1)
    		  System.out.print(nodeRalation.get(i).get(j)+" ");
    		  else
    			  System.out.println(nodeRalation.get(i).get(j));
    	  }
      }
		 //定义节点数组
         int length=nodeRalation.size();
		 Node[] node = new Node[length];
		 
		 for(int i=0;i<length-2;i++)
		 {
			 node[i]=new Node();
		   node[i].setSta(stas.get(i));
		 }
		 
		 if(flag!=1) {
			 if(eflag!=1) {
				 node[length-2]=new Node();
			   node[length-2].setSta(ssta);
			   node[length-1]=new Node();
			   node[length-1].setSta(esta);
			 }
			 else {
				 node[length-2]=new Node();
				   node[length-2].setSta(stas.get(length-2));
				   node[length-1]=new Node();
				   node[length-1].setSta(ssta);
			 }
			
		 }
		 else {
			 if(eflag!=1) {
				 node[length-2]=new Node();
				 node[length-2].setSta(stas.get(length-2));
				 node[length-1]=new Node();
				   node[length-1].setSta(esta);
			 }
			 else {
				 node[length-2]=new Node();
				 node[length-2].setSta(stas.get(length-2));
				 node[length-1]=new Node();
				   node[length-1].setSta(stas.get(length-1));
			 }
		 }
		 // 定义与节点相关联的节点集合 
		 for(int i=0;i<nodeRalation.size();i++)
		 {
		List<Node> List = new ArrayList<Node>();
		 
		 for(int j=0;j<nodeRalation.get(i).size();j++)
		 {
			 
		 List.add(node[nodeRalation.get(i).get(j)]);
		
		 }
		 node[i].setRelationNodes(List);
		 for(int j=0;j<nodeRalation.get(i).size();j++)
		 System.out.print(List.get(j).getSta().getStaname()+"*");
		 System.out.print("\n");
		 List = null; //释放内存
		 }
		 /* Node[] node = new Node[stas.size()+2]; 
		  for(int i=0;i<stas.size()+2;i++)
			 {
			  if(i<stas.size()) {
				  Station sta=new Station();
					 sta.setStaname(stas.get(i).getStaname());
					
				   node[i] = new Node();
				   node[i].setSta(sta);
			  }
			  else {
				  if(i==stas.size()) {
					  Station sta=new Station();
					  sta.setStaname(ssta.getStaname());
					  node[i] = new Node();
					  node[i].setSta(sta);
				  }
				  else {
					  Station sta=new Station();
					  sta.setStaname(esta.getStaname());
					  node[i] = new Node();
					  node[i].setSta(sta);
				  }
			  }
				  
			 }
		  ArrayList<ArrayList<Road>> ijroad=new  ArrayList<ArrayList<Road>>();
		 for(int i=0;i<stas.size();i++) {
			 List<Road> iroad=roadDao.findAllRoadBySsta(stas.get(i).getStaname());
			 
			 List<Node> List = new ArrayList<Node>();
			 for(int j=0;j<iroad.size();j++) {
				 
				List.add(node)
			 }
			 node[i].setRelationNodes(List);
			 for(int j=0;j<nodeRalation.get(i).size();j++)
			 System.out.print(List.get(j).getSta().getStaname()+"*");
			 System.out.print("\n");
			 List = null; //释放内存
		 }*/
		 if(flag!=1) {
			 if(eflag!=1) {
				 se.setSnode(node[node.length-2]);
				 se.setEnode(node[node.length-1]);
			 }
			 else {
				 se.setSnode(node[node.length-1]);
				 se.setEnode(node[ei]);
			 }
		 }
		 else {
			 if(eflag!=1) {
				 se.setSnode(node[fi]);
				 se.setEnode(node[node.length-1]);
			 }
			 else {
				 se.setSnode(node[fi]);
				 se.setEnode(node[ei]);
			 }
		 }
		
		 /*System.out.println(se.getSnode().getRelationNodes().get(0).getSta().getStaname()+"*");
		 System.out.println(se.getEnode().getRelationNodes().get(0).getRelationNodes().get(0).getSta().getStaname()+"*");*/
		return se;
	}

	

	@Override
	public Approachs findApproachsByblock(Station sta, Station eta,Date cal) {
		// TODO Auto-generated method stub
		System.out.println("findApproachsByblock**************");
	    Approachs apss=new Approachs();
	    List<Approach> routeMessages=new ArrayList<Approach>();
	    Approach aps=new Approach();
	    Route route=new Route();
	    RoadMessage roadmsg=new RoadMessage();
	    List<RoadMessage> roadmsgs=new ArrayList<RoadMessage>();
	   /* List<Road> roads=roadDao.findAllRoadByStas(sta.getStaname(),"m");
	    if(roads.isEmpty()) {
	    	roads=roadDao.findAllRoadByStas(sta.getStaname(),"s");
	    	if(roads.isEmpty()) {
	    		roads=roadDao.findAllRoadByStas(sta.getStaname(),"e");
	    	}
	    }
	    Road tmpr=new Road();
	   
	    String[] stas= {};
	    String stass="";
	    int sindex=0;
	    int eindex=0;
	   for(int j=0;j<2;j++) {
		   Road road=roads.get(j);
		   System.out.println( road.getStas());
		   stas= road.getStas().split(" ");
		   for(String s:stas) {
			   System.out.println(s);
		   }
 	    for(int i=0; i<stas.length;i++) {
 	    	if(stas[i].equals(sta.getStaname()))
 	    		
 	    		sindex=i;
 	    	if(stas[i].equals(eta.getStaname()))
 	    		eindex=i;
 	    }
 	   System.out.println(sindex+" "+eindex);
 	  if(sindex<eindex) {
 		  tmpr=road;
 		   for(int i=sindex; i<eindex+1;i++) {
 	 		   if(i!=eindex)
 		    	stass+=stas[i]+" ";
 	 		   else
 	 			stass+=stas[i];
 		   
 		    }
		   break;
	   }
	   }
	  
	   System.out.println(stass+"**************");
*/	   
	  
	    Road tmpr=new Road();
	   
	    String[] stas= {};
	    String stass="";
	    int sindex=0;
	    int eindex=0;
	   List<Road> roads=findAllRoadByStas(sta.getStaname());
	    for(Road road:roads) {
	    	System.out.println(road.getStas());
			   stas= road.getStas().split(" ");
			   for(String s:stas) {
				   System.out.println(s);
			   }
	 	    for(int i=0; i<stas.length;i++) {
	 	    	if(stas[i].equals(sta.getStaname()))
	 	    		
	 	    		sindex=i;
	 	    	if(stas[i].equals(eta.getStaname()))
	 	    		eindex=i;
	 	    }
	 	   System.out.println(sindex+" "+eindex);
	 	  if(sindex<eindex) {
	 		  tmpr=road;
	 		   for(int i=sindex; i<eindex+1;i++) {
	 	 		   if(i!=eindex)
	 		    	stass+=stas[i]+" ";
	 	 		   else
	 	 			stass+=stas[i];
	 		   
	 		    }
			  break;
		   }
	    }
		  
		   
	   
	  
	   System.out.println(stass+"**************");
	   Date sdate=new Date();
       Date edate=new Date();
      
       Time stime=roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
       Time etime=roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),tmpr.getSstanum());
       sdate.setHours(stime.getHours());
       sdate.setMinutes(stime.getMinutes());
       sdate.setSeconds(stime.getSeconds());
       
       edate.setHours(etime.getHours());
       edate.setMinutes(etime.getMinutes());
       edate.setSeconds(etime.getSeconds());
       System.out.println(cal+"|"+sdate+"|"+sdate+"*********");
       if(!(cal.getTime()<edate.getTime()&&cal.getTime()>sdate.getTime())) {
    	   return apss;
       }
      
	  double price=subwayDao.findSubwayBySubNum(tmpr.getTransportnum()).getPrice();
	  String transportnum=tmpr.getTransportnum();
	  String rdnum=tmpr.getRdnum();
	   StationMessage sstamsg=new StationMessage();
		sstamsg.setStaname(sta.getStaname());
		sstamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),stas[sindex]));
		sstamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),stas[sindex]));
		
		StationMessage estamsg=new StationMessage();
		estamsg.setStaname(eta.getStaname());
		estamsg.setStime(roadDao.findStimeByRdNumAndStaName(tmpr.getRdnum(),stas[eindex]));
		estamsg.setEtime(roadDao.findEtimeByRdNumAndStaName(tmpr.getRdnum(),stas[eindex]));
    
 	 roadmsg.setSsta(sstamsg); 
 	 roadmsg.setEsta(estamsg);
 	 roadmsg.setStas(stass);
 	 roadmsg.setOffdate(cal);
 	 roadmsg.setLasttime((eindex-sindex)*tmpr.getLasttime());
 	 Date arrdate=new Date();
 	 arrdate.setTime(roadmsg.getOffdate().getTime()+roadmsg.getLasttime()*60*1000);
 	 roadmsg.setArrdate(arrdate);
 	 roadmsg.setPrice(price);
 	 roadmsg.setTransportnum(transportnum);
 	roadmsg.setRdnum(rdnum);
 	 roadmsgs.add(roadmsg);
 	 
 	 route.setRoadMessages(roadmsgs);
 	 route.setChangenum(0);
 	 aps.setRoute(route);
 	 aps.setTotallasttime((eindex-sindex)*tmpr.getLasttime());
 	 aps.setTotalprice(price);
 	 routeMessages.add(aps);
 	 apss.setRoutemsgs(routeMessages);
 	  
 	 System.out.println(aps.getRoute().getRoadMessages().get(0).getOffdate()+"**************");
 	    return apss;
	}

	@Override
	public List<Station> findAllStopByCity(String city) {
		// TODO Auto-generated method stub
		
		return staDao.findAllStopByCity(city);
	}

	@Override
	public List<Station> findAllStopByProvince(String province) {
		// TODO Auto-generated method stub
		return staDao.findAllStopByProvince(province);
	}

	@Override
	public List<Station> findAllStop() {
		// TODO Auto-generated method stub
		return staDao.findAllStop();
	}


	
     
}


package com.qianfeng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Station;
import com.qianfeng.service.ISubwayService;
import com.qianfeng.vo.Approach;
import com.qianfeng.vo.JsonBean;
import com.qianfeng.vo.UserMessages;

@Controller
public class SubwayController {
	
	@Autowired
	private ISubwayService subwayService;
	
	public @ResponseBody JsonBean SubwayRoad(HttpSession session){
		System.out.println("here1<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	
		JsonBean bean = new JsonBean();
		try {
			     
			
			/*List<Approach> tapchs= new ArrayList<Approach>();//临时方案集
			String city=staDao.getCityName(ssta);//获得站点城市名
			//System.out.println(city);i
			List<Station> stas=staDao.findAllStationByCity(city);
			List<Object> ttransports= new ArrayList<Object>();
			
			Subway sub=new Subway();
			sub.setStas("jjjjjjj");
			transports.add(sub);
			
			Approach rotmsg=new Approach();
			rotmsg.setTotalprice(100);
			rotmsg.setTransports(transports);
			trotmsgs.add(rotmsg);
				if(stas==null) {
				throw new RuntimeException("未查询到相关信息");
			}
				System.out.println(stas.get(0).getStaname());
				rotmsgs.setRoutemsgs(trotmsgs);
			//System.out.println(stas.get(0).getStaname());
			System.out.println(rotmsgs.getRoutemsgs().get(0).getTotalprice());
			System.out.println(rotmsgs.getRoutemsgs().get(0).getTransports().get(0).getClass());*/
			
		
	/*	List<Subway> ssubs=subwayDao.findAllSubwayBySta(ssta);
		List<Subway> esubs=subwayDao.findAllSubwayBySta(esta);
		List<Station> estas=staDao.findAllStationByName(esta);
		List<Station> sstas=staDao.findAllStationByName(ssta);*/
		
		
		//System.out.println("here1**************");
			
			/*bean.setMsg(bookService.findAll());
			session.setAttribute("bmsg",bookService.findAll());
			bean.setCode(1);
			System.out.println(bookService.findAll());*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
		
	}
}

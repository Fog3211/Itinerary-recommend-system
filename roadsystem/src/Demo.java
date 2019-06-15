


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qianfeng.controller.IndexController;
import com.qianfeng.entity.IndexInfo;
import com.qianfeng.entity.Node;
import com.qianfeng.entity.RoadMap;
import com.qianfeng.entity.Station;
import com.qianfeng.service.IRoadService;
import com.qianfeng.service.ISubwayService;
import com.qianfeng.vo.Approachs;
import com.qianfeng.vo.JsonBean;


public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		IndexInfo indexinfo=new IndexInfo();
		indexinfo.setSta("万寿路");
		indexinfo.setEta("长水国际机场");
		indexinfo.setSorttype("price");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoadIndexController ctrl= ac.getBean(RoadIndexController.class);
		//IRoadService con =  ac.getBean(IRoadService.class);
		System.out.println(ctrl.RoadIndex(indexinfo));
		JsonBean c=ctrl.RoadIndex(indexinfo);
		Approachs aps=(Approachs)c.getMsg();*/
		
		
		
		//System.out.println(aps.getRoutemsgs().get(0).getRoute().getRoadMessages().get(0).getStas());
		/*System.out.println(c.getMsg());*/
	
		
		/*List<Station> rs=new ArrayList<Station>();
		RoadMap rmap=new RoadMap();
		List<Node> rnmap=new ArrayList<Node>();
		int nodeRalation[][] =
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
			 };
		 //定义节点数组 
		 Node[] node = new Node[nodeRalation.length];
		
		 for(int i=0;i<nodeRalation.length;i++)
		 {
			 Station sta=new Station();
			 sta.setStaname("sta"+i);
		   node[i] = new Node();
		   node[i].setSta(sta);
		 }
		 
		 // 定义与节点相关联的节点集合 
		 for(int i=0;i<nodeRalation.length;i++)
		 {
		List<Node> List = new ArrayList<Node>();
		 
		 for(int j=0;j<nodeRalation[i].length;j++)
		 {
			 
		 List.add(node[nodeRalation[i][j]]);
		
		 }
		 node[i].setRelationNodes(List);
		 for(int j=0;j<nodeRalation[i].length;j++)
		 System.out.print(List.get(j).getSta().getStaname()+"*");
		 System.out.print("\n");
		 List = null; //释放内存
		 }
		
		//con.findAll();
		con.findAllRoadByRoadMap(node[0],node[8]);*/
		
	}
}

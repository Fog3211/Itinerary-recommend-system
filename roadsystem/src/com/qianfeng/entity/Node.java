package com.qianfeng.entity;

import java.util.List;

public class Node {
  private Station sta ;
  private List<Node> relationNodes;
public Station getSta() {
	return sta;
}
public void setSta(Station sta) {
	this.sta = sta;
}
public List<Node> getRelationNodes() {
	return relationNodes;
}
public void setRelationNodes(List<Node> relationNodes) {
	this.relationNodes = relationNodes;
}

  
  
}

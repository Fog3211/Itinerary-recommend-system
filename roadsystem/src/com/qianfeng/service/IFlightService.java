package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Flight;

public interface IFlightService {

	List<Flight> findAllFlightBySadressAndEadress(String sadress, String eadress);
    
}

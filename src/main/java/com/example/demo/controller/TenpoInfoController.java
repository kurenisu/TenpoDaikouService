package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.TenpoInfoService;

/**
 * 店舗情報 Controller
 */
@Controller
public class TenpoInfoController {
	
	/**
	 * 店舗情報 Service
	**/
	@Autowired
	private TenpoInfoService tenpoInfoService;
	
	

}

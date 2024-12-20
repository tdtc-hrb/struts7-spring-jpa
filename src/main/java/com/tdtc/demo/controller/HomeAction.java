package com.tdtc.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tdtc.demo.model.TrainOrder;
import com.tdtc.demo.model.UserDetails;
import com.tdtc.demo.service.TrainOrderService;
import com.tdtc.demo.service.UserDetailsService;
import org.apache.struts2.ActionContext;
import org.apache.struts2.ActionSupport;

import org.apache.struts2.interceptor.parameter.StrutsParameter;

public class HomeAction extends ActionSupport {
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private TrainOrderService trainOrderService;
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	private String userId;
	private String userName;
	private String trainNum = "58";
	private String month = "1";
	
	public String getMonth() {
		return month;
	}

	@StrutsParameter
	public void setMonth(String month) {
		this.month = month;
	}

	public String getTrainNum() {
		return trainNum;
	}

	@StrutsParameter
	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String index() {
		return SUCCESS;
	}
	
	public String userList() {
		List<UserDetails> users = userService.getUsers();
		System.out.println("struts-tags: userList.jsp");
		for(UserDetails user : users) {
			logger.info(user.getEmail() + " " + user.getName());
		}
		
		ActionContext ctx = ActionContext.getContext();
        ctx.put("users", users);
		
		return SUCCESS;
	}
	
	
	public String trainList() {
		int iTrainNum = Integer.valueOf(trainNum);
		List<TrainOrder> trains = trainOrderService.getCarList("2007", month, iTrainNum);
		
		System.out.println("JSTL: trainOrder.jsp");
		for(TrainOrder train : trains) {
			logger.info(train.getCarNumber());
		}
		
		ActionContext ctx = ActionContext.getContext();
        ctx.put("trainList", trains);
        ctx.put("trainNumber", iTrainNum);
		
		return SUCCESS;
	}
	
	public String userSingle() {
		logger.info(userId);
		
		UserDetails user = userService.findUserById(this.userId);
		ActionContext ctx = ActionContext.getContext();
        ctx.put("user", user);
		return SUCCESS;
	}
	
	public String delUserById() {
		userService.delUserById(Integer.valueOf(userId));
		
		userList();
		return SUCCESS;
	}
	
	public String insertUser() {
		UserDetails user = new UserDetails();
		user.setName("test");
		user.setAddress("wuhan-hanyang");
		user.setEmail("test4@163.com");
		user.setUserName("test4");
		// Bcrypt-4(length)
		user.setPasswrod("$2a$04$HcffZJMrgN0.lmEFpC4KTeeQhitLYzrH0uTCpGRJRBw4o03ms.qWa");
		userService.insertUser(user);
		
		userList();
		return SUCCESS;
	}
	
	public String updateUser() {
		logger.info(userName + "-" + userId);
		userService.updateUser(userName, Integer.valueOf(userId));
		
		userList();
		return SUCCESS;
	}

}

package com.js.Dao;

import java.util.List;

import com.js.model.Notification;



public interface NotificationDao {
	void addNotification(com.js.model.Notification notification);
	List<Notification>  getNotificationNotViewed(String email);
	Notification getNotificaiton(int id);
	void updateNotification(int id);
}



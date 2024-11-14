package com.jacaranda.eventia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.eventia.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}

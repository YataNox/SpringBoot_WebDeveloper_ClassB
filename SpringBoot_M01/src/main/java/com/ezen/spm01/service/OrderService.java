package com.ezen.spm01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IOrderDao;

@Service
public class OrderService {
	@Autowired
	IOrderDao odao;
}

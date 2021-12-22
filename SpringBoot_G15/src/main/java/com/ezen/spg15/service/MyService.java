package com.ezen.spg15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg15.dao.ITransactionDao1;
import com.ezen.spg15.dao.ITransactionDao2;
import com.ezen.spg15.dao.ITransactionDao3;

@Service
public class MyService {
	@Autowired
	ITransactionDao1 td1;
	
	@Autowired
	ITransactionDao2 td2;
	
	@Autowired
	ITransactionDao3 td3;
}

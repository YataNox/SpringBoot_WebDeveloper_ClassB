package com.ezen.spg13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg13.dao.ITransactionDao1;
import com.ezen.spg13.dao.ITransactionDao2;

@Service
public class MyService {
	@Autowired
	ITransactionDao1 td1;
	
	@Autowired
	ITransactionDao2 td2;

	public int buy(String id, int amount, String error) {
		int n;
		try {
			td1.buy(id, amount);
			if(error.equals("1"))
				n = 10 / 0; // error 값이 1이면 강제 에러 발생
			td2.buy(id, amount);
			System.out.println("에러 없이 둘 다 실행되었습니다.");
			return 1;
		}catch(Exception e) {
			System.out.println("중간에 에러나서 하나만 실행되었습니다.");
			// System.out.println("중간에 에러나서 둘 다 실행이 안되었습니다.");
			return 0;
		}
	}
}

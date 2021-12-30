package com.ezen.spm01.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IAdminDao;
import com.ezen.spm01.dto.AdminVO;
import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;

	public int workerCheck(String workId, String workPwd) {
		int result = 0;
		AdminVO avo = null;
		avo = adao.workerCheck(workId, workPwd);
		
		if(avo == null) {
			result = -1;
		}else if(!workPwd.equals(avo.getPwd())) {
			result = 0;
		}else if(workPwd.equals(avo.getPwd())) {
			result = 1;
		}
		return result;
	}

	public ArrayList<ProductVO> listProduct(Paging paging, String key) {
		return adao.listProduct(paging, key);
	}

	public int getAllCount(String tablename, String fieldname, String key) {
		int result = adao.getAllCount(tablename, fieldname, key);
		return result;
	}
}

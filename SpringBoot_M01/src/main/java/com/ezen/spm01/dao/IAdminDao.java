package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.AdminVO;
import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;

@Mapper
public interface IAdminDao {
	public AdminVO workerCheck(String workId, String workPwd);

	public ArrayList<ProductVO> listProduct(Paging paging, String key);

	public int getAllCount(String tablename, String fieldname, String key);
}

package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.ProductVO;

@Mapper
public interface IProductDao {
	public ArrayList<ProductVO> getNewList();
	public ArrayList<ProductVO> getBestList();
	public ArrayList<ProductVO> getKindList(String kind);
	public ProductVO getProduct(int pseq);
}

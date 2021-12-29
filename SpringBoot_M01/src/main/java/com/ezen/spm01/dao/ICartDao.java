package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.CartVO;

@Mapper
public interface ICartDao {
	public void insertCart(CartVO cvo);

	public ArrayList<CartVO> listCart(String id);
}

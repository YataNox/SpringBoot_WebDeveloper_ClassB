package com.ezen.spm01.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.CartVO;

@Mapper
public interface IOrderDao {
	public void insertOrders(String id);
	public int LookUpMaxOseq();
	public void insertOrderDetail(CartVO cvo, int oseq);
	public void deleteCart(Integer cseq);
}

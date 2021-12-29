package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.CartVO;
import com.ezen.spm01.dto.OrderVO;

@Mapper
public interface IOrderDao {
	public void insertOrders(String id);
	public int LookUpMaxOseq();
	public void insertOrderDetail(CartVO cvo, int oseq);
	public void deleteCart(Integer cseq);
	public ArrayList<OrderVO> listOrderByOseq(int oseq);
}

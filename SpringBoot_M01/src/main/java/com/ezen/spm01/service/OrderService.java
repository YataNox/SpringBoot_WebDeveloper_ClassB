package com.ezen.spm01.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IOrderDao;
import com.ezen.spm01.dto.CartVO;
import com.ezen.spm01.dto.OrderVO;

@Service
public class OrderService {
	@Autowired
	IOrderDao odao;

	public int insertOrder(ArrayList<CartVO> cartList, String id) {
		int oseq = 0;
		// Orders 테이블에 현재 아이디로 레코드를 추가
		odao.insertOrders(id);
		// 방금 추가된 Orders 테이블의 레코드의 주문번호(oseq)를 조회합니다.(가장 큰 주문번호)
		oseq = odao.LookUpMaxOseq();
		// 그 주문번호와 함께 카트리스트에 있는 상품들을 한하나 order_detail테이블에 추가합니다.
		for(CartVO cvo : cartList) {
			odao.insertOrderDetail(cvo, oseq);
			odao.deleteCart(cvo.getCseq());
		}
		// 주문번호를 리턴합니다.
		return oseq;
	}

	public ArrayList<OrderVO> listOrderByOseq(int oseq) {
		return odao.listOrderByOseq(oseq);
	}

	public ArrayList<Integer> selectOseqOrderIng(String id) {
		return odao.selectOseqOrderIng(id);
	}
}

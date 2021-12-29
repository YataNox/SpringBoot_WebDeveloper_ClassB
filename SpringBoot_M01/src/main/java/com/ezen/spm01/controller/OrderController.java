package com.ezen.spm01.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spm01.dto.CartVO;
import com.ezen.spm01.dto.MemberVO;
import com.ezen.spm01.service.CartService;
import com.ezen.spm01.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService os;
	
	@Autowired
	CartService cs;
	
	@RequestMapping(value="/orderInsert")
	public String orderInsert(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		int oseq = 0;
		if(mvo == null){
			return "member/login";
		}else {
			ArrayList<CartVO> cartList = cs.listCart(mvo.getId());
			oseq = os.insertOrder(cartList, mvo.getId());
		}
		return "redirect:/orderList?oseq=" + oseq;
	}
}

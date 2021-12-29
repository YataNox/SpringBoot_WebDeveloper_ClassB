package com.ezen.spm01.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.dto.CartVO;
import com.ezen.spm01.dto.MemberVO;
import com.ezen.spm01.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService cs;
	
	@RequestMapping(value="cartInsert")
	public String cartInsert(Model model, @RequestParam("pseq") int pseq,
			@RequestParam("quantity") int quantity, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "member/login";
		}else {
			CartVO cvo = new CartVO();
			cvo.setId(mvo.getId());
			cvo.setPseq(pseq);
			cvo.setQuantity(quantity);
			cs.insertCart(cvo);
		}
		return "redirect:/cartList";
	} // cartInsert End
	
	@RequestMapping(value="cartList")
	public ModelAndView cartList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			ArrayList<CartVO> list = cs.listCart(mvo.getId());
			int totalPrice = 0;
			for(CartVO cvo : list)
				totalPrice += cvo.getPrice2() * cvo.getQuantity();
			mav.addObject("cartList", list);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/cartList");
		}
		return mav;
	} // cartList End
	
	@RequestMapping(value="/cartDelete")
	public String cartDelete(@RequestParam("cseq") int[] cseqArr) {
		for(int cseq : cseqArr)
			cs.deleteCart(cseq);
		return "redirect:/cartList";
	} // cartDelete End
}

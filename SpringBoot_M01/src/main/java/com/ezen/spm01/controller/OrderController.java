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
import com.ezen.spm01.dto.OrderVO;
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
	} // orderInsert End
	
	@RequestMapping(value="/orderList")
	public ModelAndView orderList(HttpServletRequest request,
			@RequestParam("oseq") int oseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session =request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			ArrayList<OrderVO> list = os.listOrderByOseq(oseq);
			int totalPrice = 0;
			for(OrderVO ovo : list)
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			mav.addObject("orderList", list);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/orderList");
		}
		
		return mav;
	} // orderList End
	
	@RequestMapping(value="/myPage")
	public ModelAndView mypage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null)
			mav.setViewName("member/login");
		else {
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			ArrayList<Integer> oseqList = os.selectOseqOrderIng(mvo.getId());
			for(int oseq : oseqList) {
				ArrayList<OrderVO> orderListIng = os.listOrderByOseq(oseq);
				OrderVO ovo = orderListIng.get(0);
				ovo.setPname(ovo.getPname() + " 포함 " + orderListIng.size() + " 건");
				int totalPrice = 0;
				for(OrderVO ovo1 : orderListIng)
					totalPrice += ovo1.getPrice2() * ovo.getQuantity();
				ovo.setPrice2(totalPrice);
				orderList.add(ovo);
			}
			mav.addObject("titlt", "진행중인 주문 내역");
			mav.addObject("orderList", orderList);
			mav.setViewName("mypage/mypage");
		}
		return mav;
	} // myPage End
	
	@RequestMapping(value="orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request, @RequestParam("oseq") int oseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			ArrayList<OrderVO> orderList = os.listOrderByOseq(oseq); // 주문번호로 주문 상품들의 리스트 리턴
			int totalPrice = 0;
			for( OrderVO ovo : orderList)
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			mav.addObject("orderList", orderList);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/orderDetail");
			mav.addObject("orderDetail", orderList.get(0));
		}
		
		return mav;
	}
}

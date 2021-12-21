package com.ezen.spg06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VailController {
	@RequestMapping(value="/")
	public String main() {
		return "startPage";
	}
	
	@RequestMapping(value="/create")
	public String main(@ModelAttribute("dto") ContentDto contentdto, Model model, BindingResult result) {
		// 매개변수에 Dto 객체를 변수로 넣으면 전달되어 지는 파라미터들이 객체내의 동일한 이름의
		// 멤버변수에 자동 대입됩니다.
		// writer -> contentdto.writer content -> contentdto.content
		// 추가로 contentdto라는 이름으로 객체를 model 객체에 추가됩니다.
		
		// 매개변수 객체(매개변수) 앞에 ModelAttribute(전달이름)을 붙이면,
		// return 되는 페이지에 해당 객체가 .model.addAttribute로 넣은 것처럼
		// 같이 전달됩니다. model.addAttribute("dto", contentdto);와 동일한 동작
		
		// 전달된 파라미터가 비어있거나 널이라면, 그 값을 갖고 원래의 페이지로 돌아가고,
		// 유효한 데이터라면 DonePage.jsp로 전송됩니다.
		/* if(true) {
			model.addAttribute("message", "Write를 입력하세요.");
			return "startPage";
		} */
		
		// ContentValidator : 오류 검출기능을 갖춘 사용자 지정 클래스
		ContentValidator validator = new ContentValidator();
		validator.validate(contentdto, result);
		// BindingResult : 키 값(제목)과 밸류 값(내용)으로 구성된 오류 내용 저장 클래스
		// validator 메소드는 contentdto와 BindingResult 형태의 레퍼런스 변수를 매개변수로 넣고 시작.
		// validator의 멤버 메소드인 validate가 contentdto 내용을 검사한 후 result에 오류 내용을 담아주고,
		// 리턴되지 않아오 call by reference 이기때문에 오류 내용을 현재 위치에서도 result 라는 이름으로
		// 사용이 가능합니다.
		
		if(result.hasErrors()) {
			model.addAttribute("message", "writer 또는 content를 입력하세요.");
			return "startPage";
		}
		
		return "DonePage";
	}
}

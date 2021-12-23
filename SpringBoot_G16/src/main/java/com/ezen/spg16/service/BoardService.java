package com.ezen.spg16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.ezen.spg16.dao.IBoardDao;
import com.ezen.spg16.dto.BoardVO;
import com.ezen.spg16.dto.Paging;

@Service
public class BoardService {
	@Autowired
	IBoardDao bdao;
	
	@Autowired
	TransactionTemplate tt;

	public List<BoardVO> selectBoardAll(Paging paging) {
		List<BoardVO> list = null;
		try {
			// 10개의 게시물 리스트가 리턴되어 list에 저장됩니다.
			list = bdao.selectBoardAll(paging);
			
			for(BoardVO bvo : list) {
				// 각 게시물 번호를 이용하여 댓글 갯수를 조회하는 메소드를 호출하여 갯수를 얻습니다.
				int count = bdao.getCount(bvo.getNum());
				// 조회된 댓글 갯수를 dto에 업데이트
				bvo.setReplycnt(count);
			}
		}catch(Exception e) {
			System.out.println("DB 오류로 인해 조회되지 않았습니다.");
		}
		return list;
	}

	public int getAllCount() {
		return bdao.getAllCount();
	}
}

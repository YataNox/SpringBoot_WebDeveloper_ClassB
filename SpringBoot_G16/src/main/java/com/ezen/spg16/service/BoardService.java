package com.ezen.spg16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg16.dao.IBoardDao;
import com.ezen.spg16.dto.BoardVO;

@Service
public class BoardService {
	@Autowired
	IBoardDao bdao;

	public List<BoardVO> selectBoardAll() {
		return bdao.selectBoardAll();
	}
}
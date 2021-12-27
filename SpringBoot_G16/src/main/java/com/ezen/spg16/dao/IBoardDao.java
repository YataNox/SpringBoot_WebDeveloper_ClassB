package com.ezen.spg16.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg16.dto.BoardVO;
import com.ezen.spg16.dto.Paging;

@Mapper
public interface IBoardDao {
	public List<BoardVO> selectBoardAll(Paging paging);

	public int getAllCount();

	public int getCount(int num);

	public void insertBoard(BoardVO bdto);
}

package com.ezen.spg16.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg16.dto.BoardVO;

@Mapper
public interface IBoardDao {
	public List<BoardVO> selectBoardAll();
}

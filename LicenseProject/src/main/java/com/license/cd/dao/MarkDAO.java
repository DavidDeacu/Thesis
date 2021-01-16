package com.license.cd.dao;

import java.util.List;

import com.license.cd.entity.Mark;

public interface MarkDAO {

	public List<Mark> getAllMarks(int id);
	
	public void saveMark(Mark mark);
	
	public Mark getMark(int theId);
	
	public void deleteMark(int theId);
}

package com.license.cd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.license.cd.dao.MarkDAO;
import com.license.cd.entity.Mark;

@Service
public class MarkServiceImpl implements MarkService {
	
	//need to inject the student dao
	@Autowired
	private MarkDAO markDAO;

	@Override
	@Transactional
	public List<Mark> getAllMarks(int id) {
		return markDAO.getAllMarks(id);
	}

	@Override
	@Transactional
	public void saveMark(Mark mark) {
		markDAO.saveMark(mark);
	}

	@Override
	@Transactional
	public Mark getMark(int theId) {
		return markDAO.getMark(theId);
	}

	@Override
	@Transactional
	public void deleteMark(int theId) {
		markDAO.deleteMark(theId);
	}

}

package com.testweb.main.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.bbs.model.BbsDAO;
import com.testweb.bbs.model.BbsVO;

public class MainListServiceImpl implements MainService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BbsDAO dao = BbsDAO.getInstance();
		ArrayList<BbsVO> list =  dao.mainList();
		
		if(list != null) {
			request.setAttribute("mainList", list);
		} else {
			return;
		}
		

	}

}

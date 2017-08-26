package cn.ssmc.terms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssmc.dao.TermsDao;
import cn.ssmc.entity.Terms;
import cn.ssmc.terms.service.TermService;
@Service
public class TermsServiceImpl implements TermService{

	@Autowired
	private TermsDao termsDao;
	
	@Override
	public Terms selectNow() {
		// TODO Auto-generated method stub
		return termsDao.selectNow();
	}

	@Override
	public Terms selectBefore1() {
		// TODO Auto-generated method stub
		return termsDao.selectBefore1();
	}

}

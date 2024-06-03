package com.funnygorilla.tinybankaccount.repository;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funnygorilla.tinybankaccount.repository.dataaccess.AccountDAO;
import com.funnygorilla.tinybankaccount.repository.dataaccess.AccountDB;
import com.funnygorilla.tinybankaccount.services.domain.entity.AccountBO;
 

@Repository
public class AccountRepository  
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountDAO accountDao;

	/**
	 * 
	 * @return
	 */
	public List<AccountBO> getAllAccounts(){
		List<AccountDB> dbs = this.accountDao.findAll();
		
		if (dbs == null) return null;
		
		List <AccountBO> bos = new ArrayList<AccountBO> ();
		
		AccountBO bo = null;
		
		Iterator<AccountDB> r = dbs.iterator();
		while (r.hasNext()) {
			bo = new AccountBO();
			// simple data model transformation
			BeanUtils.copyProperties(r.next(), bo);
			bos.add(bo);
		}
		
		return bos;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AccountBO getAccountByNumber(Long id) {
		AccountDB db = this.getAccount(id);
		
		// simple data model transformation from table entity to business object
		AccountBO bo = new AccountBO();
		BeanUtils.copyProperties(db, bo);
		
		return bo;
	}
	/**
	 * 
	 * @param student
	 * @return
	 */
	public Long createAccount(AccountBO student) {
		AccountDB db = new AccountDB();
		BeanUtils.copyProperties(student, db);
		db = this.accountDao.saveAndFlush(db);
		logger.debug("--> Account " + db.toString() +  " created.");
		return db.getAccountNo();
	}
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public AccountBO updateAccount (AccountBO sbo ) {
		Long targetID = sbo.getAccountNo();
		AccountDB sdb = new AccountDB();
		
		// retrieve student from repository
		sdb = this.getAccount(targetID);
		
		// if it does not exist return null;
		if (sdb == null) {
			logger.warn("--> Account " + targetID +  " does not exist!");
			return null;
		}
		
		// if it exist, update it and return updated record.
		BeanUtils.copyProperties(sbo, sdb);
        sdb = this.accountDao.saveAndFlush(sdb);
        
        AccountBO updatedSt = new AccountBO();
        BeanUtils.copyProperties(sdb, updatedSt);
	        
        logger.debug("--> Account " + updatedSt.toString() +  " updated.");
		return updatedSt;
	}
	/**
	 * 
	 * @param studentId
	 */
	public boolean deleteAccount (Long studentId) {
		AccountDB sdb = this.getAccount(studentId);
		
		if (null == sdb) {
			logger.debug("--> Account " + studentId +  " does not exit and nothing has been deleted.");
			return false;
		}
		
		this.accountDao.deleteById(studentId);
		logger.debug("--> Account " + studentId +  " deleted.");
		return true;
	}
	/**
	 * retrieve student record by id.
	 * @param id
	 * @return
	 */
	private AccountDB getAccount (Long id) {
		
		logger.debug("--> Account " + id + " before accountDao call.");
		AccountDB sdb = this.accountDao.findById(id).orElse(null);
		if (null == sdb) {
			logger.warn("--> Account " + id +  " does not exist!");
		}
		
		return sdb;
	}

}
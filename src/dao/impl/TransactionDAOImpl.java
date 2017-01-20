package dao.impl;

import dao.api.TransactionDAO;
import dto.TransactionDTO;
import entity.Transaction;
import entity.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import DataSource.InterfaceInMemory;
import DataSource.MySQL;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class TransactionDAOImpl implements TransactionDAO {
	static InterfaceInMemory inmem = new MySQL();
	@Override
	public boolean buyFromBacket(Transaction transaction) {
		// TODO Auto-generated method stub
		return inmem.addTransaction(transaction);
	}

	@Override
	public List<Transaction> getTransactionbyUser(User user) {
		// TODO Auto-generated method stub
		return inmem.getTransactionByUser(user.getId());
	}

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return inmem.getAllTransaction();
	}

	@Override
	public boolean addTrsansaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return inmem.addTransaction(transaction);
	}
	

		
	
}

package service.impl;

import java.util.LinkedList;
import java.util.List;

import dao.impl.ProductDAOImpl;
import dao.impl.TransactionDAOImpl;
import dto.ProductDTO;
import dto.TransactionDTO;
import dto.UserDTO;
import entity.Product;
import entity.Transaction;
import helper.Transformer;
import service.api.TransactionService;


public class TransactonImpl implements TransactionService {
	public TransactionDAOImpl trDAO = new TransactionDAOImpl();
	public ProductDAOImpl productDAO = new ProductDAOImpl();
	@Override
	public boolean add(TransactionDTO transactionDTO, ProductDTO productDTO) {
		boolean result = true;
		Transaction transaction = helper.Transformer.transformTRDTOtoTransaction(transactionDTO);
		Product product = helper.Transformer.transformProductDTOToProduct(productDTO);
		int oldCount = productDAO.findById(product.getId()).getCount();
		try{
			int count =productDAO.findById(product.getId()).getCount()- product.getCount();
			
			System.out.println("was=" + productDAO.findById(product.getId()).getCount());
			System.out.println("bought= " + product.getCount());
			if(count>0){
			product.setCount(count);
			productDAO.update(product);
			
			} else result=false;
			
		if (result){
			result = trDAO.addTrsansaction(transaction);
		}	else{
			product.setCount(oldCount);
			productDAO.update(product);
			
		}
	     
	     
		
		}catch (Exception e){
			result = false;
			e.printStackTrace();
		}
		 
		return result;
	}

	@Override
	public List<TransactionDTO> getAllTransactions() {
		List<TransactionDTO> newList = new LinkedList<>();
		List<Transaction> trList = new LinkedList<>();
		trList = trDAO.getAllTransactions();
		int count = trList.size();
		
		for(int i=0; i<count;i++ ){
			TransactionDTO trDTO = Transformer.transformTRtoTransactionDTO(trList.get(i));
			newList.add(trDTO);
		}
		
		
		
		// TODO Auto-generated method stub
		return newList;
	}

	@Override
	public List<TransactionDTO> getUserTransactions(UserDTO user) {
		List<TransactionDTO> newList = new LinkedList<>();
		List<Transaction> trList = new LinkedList<>();
		trList = trDAO.getTransactionbyUser(Transformer.transformUserDTOToUser(user));
		int count = trList.size();
		
		for(int i=0; i<count;i++ ){
			TransactionDTO trDTO = Transformer.transformTRtoTransactionDTO(trList.get(i));
			newList.add(trDTO);
		}
		
		
		
		// TODO Auto-generated method stub
		return newList;
	}

}

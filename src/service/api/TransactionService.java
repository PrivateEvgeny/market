package service.api;
import java.util.List;

import dto.ProductDTO;
import dto.TransactionDTO;
import dto.UserDTO;
public interface TransactionService {

	public  boolean add(TransactionDTO transaction, ProductDTO productDTO);
	public List<TransactionDTO> getAllTransactions();
	public List<TransactionDTO> getUserTransactions(UserDTO user);
	
}

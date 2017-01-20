package dao.api;

import entity.Transaction;
import entity.User;

import java.util.List;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public interface TransactionDAO {

    //boolean putToBacket(Transaction transaction);
    
    boolean buyFromBacket(Transaction transaction);
    
    List<Transaction> getTransactionbyUser(User user);
    List<Transaction> getAllTransactions();
    
    boolean addTrsansaction(Transaction transaction);
    
}

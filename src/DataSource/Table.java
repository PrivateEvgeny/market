package DataSource;

import java.util.Set;
import java.util.TreeSet;

public class Table {
	int productId;
	int productGroupId;
  private static Set<String> tableString = new TreeSet<>();
  private static Set<Table> stroka = new TreeSet<>();
  Table(){}
  Table(int productIdNew, int productGroupIdNew){
	  productId =productIdNew;
	  productGroupId = productGroupIdNew;
  }
  
   public boolean addElement(int productId, int productGroupId){
	   boolean result = true;
	   String element = productGroupId +"-"+  productId;
	   if (tableString.contains(tableString)){
		   result = false;
	   } else{
		   newTable(productId, productGroupId);
	   }
	   
	   
	   return result;
   }
private void newTable(int productId2, int productGroupId2) {
	stroka.add(new Table(productId2,productGroupId2));
	
}
}



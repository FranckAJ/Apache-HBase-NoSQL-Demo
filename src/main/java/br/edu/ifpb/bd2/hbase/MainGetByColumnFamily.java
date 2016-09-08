package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.enumerations.Familys;

public class MainGetByColumnFamily {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<String> result1 = dao.findByColumnFamily(Familys.BOOK.toString(), "name", "isbn", "numberOfPage");
		
		System.out.println("## famlília de coluna BOOK");
		for (String string : result1) {
			System.out.println(string);
		}
		
		List<String> result = dao.findByColumnFamily(Familys.SESSION.toString(), "name", "localization");
		
		System.out.println("## famlília de coluna SESSION");
		for (String string : result) {
			System.out.println(string);
		}
	}
}

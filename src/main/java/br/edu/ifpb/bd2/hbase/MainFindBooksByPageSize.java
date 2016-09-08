package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;

public class MainFindBooksByPageSize {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<String> result1 = dao.findBookByPageSize(100);
		
		for (String string : result1) {
			System.out.println(string);
		}
	}
}

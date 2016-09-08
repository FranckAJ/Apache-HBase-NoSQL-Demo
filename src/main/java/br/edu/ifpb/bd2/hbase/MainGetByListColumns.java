package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.enumerations.Familys;

public class MainGetByListColumns {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<String> result1 = dao.findByMultipleColumns("name", Familys.BOOK,  Familys.SESSION, Familys.EDITION);
		
		for (String string : result1) {
			System.out.println(string);
		}
	}
}

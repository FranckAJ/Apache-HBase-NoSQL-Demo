package br.edu.ifpb.bd2.hbase;

import java.io.IOException;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.entities.ComicBook;

public class MainGetByRow {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		ComicBook hq = dao.findByRow("12345");
		
		System.out.println("## " + hq);
	}

}

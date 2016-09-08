package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.entities.ComicBook;

public class MainGetAll {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<ComicBook> hqs = dao.findAll();
		
		for (ComicBook comicBook : hqs) {
			System.out.println("## "+comicBook);
		}
	}
}

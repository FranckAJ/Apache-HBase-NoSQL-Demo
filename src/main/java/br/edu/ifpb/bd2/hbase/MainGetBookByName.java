package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.entities.ComicBook;

public class MainGetBookByName {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<ComicBook> hqs = dao.findBookByName("The Flash 34");
		
		if(hqs != null){
			for (ComicBook comicBook : hqs) {
				System.out.println("##"+ comicBook);
			}
		}else{
			System.out.println("## Nehum dado econtrado!");
		}
		
		dao.getTable().close();
	}
}

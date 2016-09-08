package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.entities.ComicBook;

public class MainDeleteAll {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<ComicBook> hqs = dao.findAll();
		
		if(!hqs.isEmpty()){
			System.out.println("## "+hqs.size()+ " Registros encontrados antes de remover todos!");
		}
		
		dao.removeAll();
		
		hqs = dao.findAll();
		if(hqs.isEmpty())
			System.out.println("## "+hqs.size()+" Registros encontrados após remover todos!");
			
		dao.getTable().close();
	}
}

package br.edu.ifpb.bd2.hbase;

import java.io.IOException;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.entities.ComicBook;

public class MainUpdate {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		ComicBook hq = dao.findByRow("12345");
		System.out.println("## ANTES DE ATUALIZAR: "+ hq);
		
		hq.setName("The Flash 1979");
		hq.getEdition().setName("Originais anos 70/80");
		hq.getEdition().setYear(1979);
		
		System.out.println("## DEPOIS DE ATUALIZAR: "+ dao.update(hq));
	}
}

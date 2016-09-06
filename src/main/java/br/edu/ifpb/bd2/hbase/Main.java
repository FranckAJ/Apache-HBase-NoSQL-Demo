package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.Date;

import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.dao.ComicBook;
import br.edu.ifpb.bd2.hbase.dao.Edition;
import br.edu.ifpb.bd2.hbase.dao.Session;



public class Main {

	public static void main(String[] args) throws IOException, BD2Exception {

		ComicBook bo = new ComicBook();
		bo.setIsbn("12345");
		bo.setName("Demolidor 34");
		bo.setNumberOfPages(220);
		bo.setSession(new Session("HQS", "2º Andar 1ª Fila"));
		bo.setEdition(new Edition("Novos 52", 2012, new Date()));
		
		BookDAO b = new BookDAO();
		String[] s = {"BOOK", "SESSION", "EDITION"};
		
		//b.createTable("tb_book", s);
		//b.deleteTable("tb_book");
		
	//b.add(bo);
		
	System.out.println("ANTES DE REMOVER: "+ b.findByRow("12345"));
	//	b.remove("12345");
	//	System.out.println("DEPPOIS DE REMOVER: " +b.findByRow("12345"));
		
	//	bo.setName("The Hobbit - A batalha dos cinco exercitos");
		
		//System.out.println(b.update(bo));
		
		//System.out.println("DEPPOIS DE ATUALIZAR: " +b.findByRow("12345"));
		
	}

}

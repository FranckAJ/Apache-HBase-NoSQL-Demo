package br.edu.ifpb.bd2.hbase;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;

public class MainDelete {
	
	public static void main(String[] args) throws BD2Exception {
		
		BookDAO dao = new BookDAO();
		
		System.out.println("## ANTES DE REMOVER: "+ dao.findByRow("12345"));
		dao.deleteTable("tb_book");
		System.out.println("## DEPOIS DE REMOVER: " + dao.findByRow("12345"));
	}
}

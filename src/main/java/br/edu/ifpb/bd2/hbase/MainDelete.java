package br.edu.ifpb.bd2.hbase;

import java.io.IOException;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;

public class MainDelete {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		System.out.println("## ANTES DE REMOVER: "+ dao.findByRow("123456"));
		dao.remove("123456");
		
		/*fecho as conexões com tabela*/
		dao.getTable().close();

		System.out.println("## DEPOIS DE REMOVER: " + dao.findByRow("123456"));
		
	}
}

package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.List;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.enumerations.Familys;

public class MainGetByQualifier {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		BookDAO dao = new BookDAO();
		
		List<String> hqs = dao.findByQualifier(Familys.BOOK, "name");
		
		if(hqs != null){
			for (String str : hqs) {
				System.out.println("##"+ str);
			}
		}else{
			System.out.println("## Nehum dado econtrado!");
		}
		
		dao.getTable().close();
	}
}

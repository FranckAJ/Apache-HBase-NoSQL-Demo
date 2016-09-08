package br.edu.ifpb.bd2.hbase;

import java.io.IOException;
import java.util.Date;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;
import br.edu.ifpb.bd2.hbase.entities.ComicBook;
import br.edu.ifpb.bd2.hbase.entities.Edition;
import br.edu.ifpb.bd2.hbase.entities.Session;

public class MainSave {
	
	public static void main(String[] args) throws BD2Exception, IOException {
		
		ComicBook hq = new ComicBook();
		hq.setIsbn("12345");
		hq.setName("The Flash 34");
		hq.setNumberOfPages(220);
		hq.setSession(new Session("HQS", "2º Andar 1ª Fila"));
		hq.setEdition(new Edition("Novos 52", 2012, new Date()));
		
		ComicBook hqThanos = new ComicBook();
		hqThanos.setIsbn("123456");
		hqThanos.setName("Thannos v3 vs Galactus");
		hqThanos.setNumberOfPages(80);
		hqThanos.setSession(new Session("HQS", "2º Andar 1ª Fila"));
		hqThanos.setEdition(new Edition("thanos", 1887, new Date()));
		
		BookDAO dao = new BookDAO();
		
		dao.add(hq);
		dao.add(hqThanos);
		
		System.out.println("## "+ dao.findByRow("12345"));
		System.out.println("## "+ dao.findByRow("123456"));
		
		dao.getTable().close(); 
		
	}
}

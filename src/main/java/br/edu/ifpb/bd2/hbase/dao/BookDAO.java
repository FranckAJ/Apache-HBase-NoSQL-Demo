package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import br.edu.ifpb.bd2.hbase.entities.ComicBook;
import br.edu.ifpb.bd2.hbase.entities.Edition;
import br.edu.ifpb.bd2.hbase.entities.Session;
import br.edu.ifpb.bd2.hbase.enumerations.Familys;

public class BookDAO extends AbstractDAO<ComicBook, String>{
	
	 private final TableName TABLE_NAME = TableName.valueOf("tb_book");
	 private Table table;
	 
	 public BookDAO() {
		 try {
			table = getConnection().getTable(TABLE_NAME);
		} catch (IOException | BD2Exception e) {
			e.printStackTrace();
		}
	 }
	
	@Override
	public void add(ComicBook entity) throws BD2Exception, IOException {
		Put p = new Put(Bytes.toBytes(entity.getIsbn()));
		
	    p.addColumn(Bytes.toBytes(Familys.BOOK.toString()), Bytes.toBytes("name"), Bytes.toBytes(entity.getName()));
	    p.addColumn(Bytes.toBytes(Familys.BOOK.toString()), Bytes.toBytes("isbn"), Bytes.toBytes(entity.getIsbn()));
	    p.addColumn(Bytes.toBytes(Familys.BOOK.toString()), Bytes.toBytes("numberOfPage"), Bytes.toBytes(entity.getNumberOfPages()));
	    
	    p.addColumn(Bytes.toBytes(Familys.SESSION.toString()), Bytes.toBytes("name"), Bytes.toBytes(entity.getSession().getName()));
	    p.addColumn(Bytes.toBytes(Familys.SESSION.toString()), Bytes.toBytes("localization"), Bytes.toBytes(entity.getSession().getLocalization()));
	    
	    p.addColumn(Bytes.toBytes(Familys.EDITION.toString()), Bytes.toBytes("name"), Bytes.toBytes(entity.getEdition().getName()));
	    p.addColumn(Bytes.toBytes(Familys.EDITION.toString()), Bytes.toBytes("year"), Bytes.toBytes(entity.getEdition().getYear()));
	    p.addColumn(Bytes.toBytes(Familys.EDITION.toString()), Bytes.toBytes("release"), Bytes.toBytes(entity.getEdition().getRelease().toString()));
	   
	    table.put(p);
	}

	@Override
	public ComicBook update(ComicBook entity) throws BD2Exception, IOException {
		this.add(entity);
		return this.findByRow(entity.getIsbn());
	}

	@Override
	public void remove(String row) throws BD2Exception, IOException {
		Delete delete = new Delete(Bytes.toBytes(row));
		try {
			table.delete(delete);
		} catch (IOException e) {
			throw new BD2Exception("Ocorreu um erro ao tentar remover o registro "+ e.getMessage());
		}
	}

	@Override
	public ComicBook findByRow(String row) throws BD2Exception, IOException {
		Get theGet = new Get(Bytes.toBytes(row));
		Result result = null;
		ComicBook comicBook = null;
	    try {
	    	result = table.get(theGet);
	    	
	        byte [] value = result.getValue(Bytes.toBytes(Familys.BOOK.toString()),Bytes.toBytes("name"));
	        byte [] isbn = result.getValue(Bytes.toBytes(Familys.BOOK.toString()),Bytes.toBytes("isbn"));
	        byte [] numberOfPages = result.getValue(Bytes.toBytes(Familys.BOOK.toString()),Bytes.toBytes("numberOfPage"));
	       
	        byte [] nameSession = result.getValue(Bytes.toBytes(Familys.SESSION.toString()),Bytes.toBytes("name"));
	        byte [] localization = result.getValue(Bytes.toBytes(Familys.SESSION.toString()),Bytes.toBytes("localization"));
	        
	        byte [] nameEdition = result.getValue(Bytes.toBytes(Familys.EDITION.toString()),Bytes.toBytes("name"));
	        byte [] yearEdition = result.getValue(Bytes.toBytes(Familys.EDITION.toString()),Bytes.toBytes("year"));
	        byte [] release = result.getValue(Bytes.toBytes(Familys.EDITION.toString()),Bytes.toBytes("release"));

	    	comicBook = new ComicBook();
	    	comicBook.setName(Bytes.toString(value));
	    	comicBook.setIsbn(Bytes.toString(isbn));
	    	comicBook.setNumberOfPages(Bytes.toInt(numberOfPages));
	    	Session session = new Session(Bytes.toString(nameSession), Bytes.toString(localization));
	    	Edition edition = new Edition(Bytes.toString(nameEdition),Bytes.toInt(yearEdition),formatDate(release));
	    	comicBook.setSession(session);
	    	comicBook.setEdition(edition);
	    	
		} catch (IOException e) {
			throw new BD2Exception(e.getMessage());
		}
		return comicBook;
	}
	
	private Date formatDate(byte[] date){
		String s = new String(date);
		SimpleDateFormat sdt = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		Date dateFormated = null;
		
		try {
			dateFormated = sdt.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormated;
	}
}

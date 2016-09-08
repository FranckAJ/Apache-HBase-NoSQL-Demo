package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
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
		try {
			Delete delete = new Delete(Bytes.toBytes(row));
			table.delete(delete);
		} catch (IOException e) {
			throw new BD2Exception("Ocorreu um erro ao tentar remover o registro "+ e.getMessage());
		}
	}
	
	public void removeAll() throws BD2Exception, IOException{
		List<ComicBook> hqs = findAll();
		if(hqs != null){
			for (ComicBook comicBook : hqs) {
				remove(comicBook.getIsbn());
			}
		}
	}
	
	public void findByColumn(String row, String column) throws IOException{
		Filter filter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(Bytes.toBytes(column)));
	    Scan scan = new Scan();
	    scan.setFilter(filter);
	    ResultScanner scanner = table.getScanner(scan);
	    for (Result result : scanner) {
			System.out.println(result);
		}
	}
	
	public String findByQualifier(Familys family, String qualifier) throws IOException{
		Filter filter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(Bytes.toBytes(qualifier)));
		
	    Scan scan = new Scan();
	    scan.setFilter(filter);
	    ResultScanner scanner = table.getScanner(scan);
	    byte[] value = null;
	    for (Result result : scanner) {
	    	value = result.getValue(Bytes.toBytes(family.toString()), Bytes.toBytes(qualifier));
	    }
		return Bytes.toString(value);
	}

	@Override
	public ComicBook findByRow(String row) throws BD2Exception, IOException {
		Get theGet = new Get(Bytes.toBytes(row));
		Result result = null;
		ComicBook comicBook = null;
	    try {
	    	result = table.get(theGet);
	    	if(!result.isEmpty()){
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
	    	}
	    	
		} catch (IOException e) {
			throw new BD2Exception(e.getMessage());
		}
		return comicBook;
	}
	
	public List<ComicBook> findAll(){
		List<ComicBook> list = new ArrayList<ComicBook>();
		Scan scan = new Scan();
		try {
		scan.setFilter(new FirstKeyOnlyFilter());
		ResultScanner scanner = table.getScanner(scan);
		for (Result result : scanner) {
			byte[] key = result.getRow();
			list.add(findByRow(Bytes.toString(key)));
		}	
		} catch (IOException | BD2Exception e) {
			e.printStackTrace();
		}
		return list;
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

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
}

package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import br.edu.ifpb.bd2.hbase.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.enumerations.Familys;

public class BookDAO extends AbstractDAO<ComicBook, String>{
	
	 private final TableName TABLE_NAME = TableName.valueOf("tb_book");
	 private Table table;
	 
	 {
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
	    table.put(p);
	}

	@Override
	public ComicBook update(ComicBook entity) {
		return null;
	}

	@Override
	public void remove(ComicBook entity) {
		
	}

	@Override
	public ComicBook findByRow(String row) throws BD2Exception {
		Get theGet = new Get(Bytes.toBytes(row));
		 Result result = null;
		 ComicBook comicBook = null;
	    try {
	    	result = table.get(theGet);
	    	
	        byte [] value = result.getValue(Bytes.toBytes(Familys.BOOK.toString()),Bytes.toBytes("name"));
	        byte [] isbn = result.getValue(Bytes.toBytes(Familys.BOOK.toString()),Bytes.toBytes("isbn"));

	    	comicBook = new ComicBook();
	    	comicBook.setName(Bytes.toString(value));
	    	comicBook.setIsbn(Bytes.toString(isbn));
	    	
		} catch (IOException e) {
			e.printStackTrace();
			throw new BD2Exception(e.getMessage());
		}
		return comicBook;
	}
	
	

}

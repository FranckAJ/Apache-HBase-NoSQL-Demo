package br.edu.ifpb.bd2.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import br.edu.ifpb.bd2.hbase.dao.ComicBook;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;



public class Main {

	public static void main(String[] args) throws IOException, BD2Exception {
//		 TableName tableName = TableName.valueOf("stock-prices");
//		 
//		    Configuration conf = HBaseConfiguration.create();
//		    Connection conn = ConnectionFactory.createConnection(conf);
//		   
//		    
//		    Admin admin = conn.getAdmin();
//		    if (!admin.tableExists(tableName)) {
//		        admin.createTable(new HTableDescriptor(tableName).addFamily(new HColumnDescriptor("cf")));
//		    }
//		 
//		    Table table = conn.getTable(tableName);
//		    Put p = new Put(Bytes.toBytes("AAPL10232015")); //  cria linha
//		    
//		    p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("close"), Bytes.toBytes(119));
//		    table.put(p);
//		 
//		    Result r = table.get(new Get(Bytes.toBytes("AAPL10232015")));
//		    System.out.println(r);
		
		ComicBook bo = new ComicBook();
		bo.setIsbn("12345");
		bo.setName("Demolidor 34");
		BookDAO b = new BookDAO();
		String[] s = {"BOOK"};
		//b.createTable("tb_book", s);
		//b.add(bo);
		//b.deleteTable("tb_book");
		
		System.out.println("ANTES DE REMOVER: "+ b.findByRow("12345"));
		b.remove("12345");
		System.out.println("DEPPOIS DE REMOVER: " +b.findByRow("12345"));
	}

}

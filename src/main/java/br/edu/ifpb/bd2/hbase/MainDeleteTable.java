package br.edu.ifpb.bd2.hbase;

import java.io.IOException;

import br.edu.ifpb.bd2.hbase.dao.BD2Exception;
import br.edu.ifpb.bd2.hbase.dao.BookDAO;


public class MainDeleteTable {

	public static void main(String[] args) throws IOException, BD2Exception {

		BookDAO dao = new BookDAO();
		dao.deleteTable("tb_book");;
	}
}

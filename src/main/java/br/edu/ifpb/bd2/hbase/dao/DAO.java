package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;

import br.edu.ifpb.bd2.hbase.BD2Exception;

public interface DAO<T, K> {
	
	/**
	 * 
	 * @param entity
	 * @throws IOException 
	 */
	public void add(T entity) throws BD2Exception, IOException;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity) throws BD2Exception;
	
	/**
	 * 
	 * @param entity
	 */
	public void remove(T entity) throws BD2Exception;
	
	public T findByRow(K row) throws BD2Exception;
	

}

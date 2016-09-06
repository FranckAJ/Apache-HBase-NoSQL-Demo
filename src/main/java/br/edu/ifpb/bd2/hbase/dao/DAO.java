package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T>
 * @param <K>
 */
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
	 * @throws IOException 
	 */
	public T update(T entity) throws BD2Exception, IOException;
	
	/**
	 * 
	 * @param entity
	 */
	public void remove(String row) throws BD2Exception;
	
	/**
	 * 
	 * @param row
	 * @return
	 * @throws BD2Exception
	 */
	public T findByRow(K row) throws BD2Exception;
	

}

package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;

/**
 * Interface comum para todas os DAO's, possui assinatura dos métodos básicos de
 * um CRUD.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T>
 * @param <K>
 */
public interface DAO<T, K> {

	/**
	 * Interface padrão utilizada para CRUD padrão nos D
	 * 
	 * @param entity
	 * @throws IOException
	 */
	void add(T entity) throws BD2Exception, IOException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	T update(T entity) throws BD2Exception, IOException;

	/**
	 * 
	 * @param entity
	 * @throws IOException 
	 */
	void remove(String row) throws BD2Exception, IOException;

	/**
	 * 
	 * @param row
	 * @return
	 * @throws BD2Exception
	 * @throws IOException 
	 */
	T findByRow(K row) throws BD2Exception, IOException;

}

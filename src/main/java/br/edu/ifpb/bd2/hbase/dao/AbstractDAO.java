package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import br.edu.ifpb.bd2.hbase.BD2Exception;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T>
 */
public abstract class AbstractDAO<T, K> implements DAO<T, K> {

	protected Configuration conf;
	private Connection conn;
	private Admin admin;
	
	{
		conf = HBaseConfiguration.create();
		}

	/**
	 * 
	 * @return
	 * @throws BD2Exception
	 */
	public Connection getConnection() throws BD2Exception {
		try {
			conn = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			throw new BD2Exception("Ocorreu um erro ao tentar conectar ao Banco de dados " + e);
		}
		return conn;
	}

	/**
	 * 
	 * @return
	 * @throws BD2Exception
	 */
	public Admin getAdmin() throws BD2Exception {
		this.getConnection();
		try {
			admin = conn.getAdmin();
		} catch (IOException e) {
			throw new BD2Exception("Ocorreu um erro ao tentar recuperar o admin" + e);
		}
		return admin;
	}

	/**
	 * 
	 * @param name
	 * @throws BD2Exception
	 * @throws IOException
	 */
	public void createTable(String name, String[] familys) throws BD2Exception,IOException {
		try {
			this.getAdmin();
			TableName tableName = TableName.valueOf(name);
			if (!admin.tableExists(tableName)) {
				HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
				for (int i = 0; i < familys.length; i++) {
					hTableDescriptor.addFamily(new HColumnDescriptor(familys[i]));
				}
				admin.createTable(hTableDescriptor);
			}
		} finally {
			conn.close();
		}
	}
	
    /**
     * Delete a table
     */
    public void deleteTable(String name) throws BD2Exception {
        try {
        	this.getAdmin();
			TableName tableName = TableName.valueOf(name);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
            throw new BD2Exception(e.getMessage());
		}
    }
}

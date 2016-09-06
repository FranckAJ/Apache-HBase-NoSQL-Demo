package br.edu.ifpb.bd2.hbase.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * DAO abstrato, responsável por encapsular algumas configurações recorrentes em
 * operações com HBase.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T, K>
 */
public abstract class AbstractDAO<T, K> implements DAO<T, K> {

	protected Configuration conf;
	private Connection conn;
	private Admin admin;

	/**
	 * cria instância de configurações do HBase.
	 */
	{
		conf = HBaseConfiguration.create();
	}

	/**
	 * Obtém conexão do HBase
	 * 
	 * @return conexão com HBase
	 * @throws BD2Exception
	 */
	public Connection getConnection() throws BD2Exception {
		try {
			if (conn == null)
				conn = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			throw new BD2Exception("Ocorreu um erro ao tentar conectar ao Banco de dados " + e);
		}
		return conn;
	}

	/**
	 * Obtém acesso de admin do HBase.
	 * 
	 * @return Objeto Admin
	 * @throws BD2Exception
	 * @throws IOException
	 */
	public Admin getAdmin() throws BD2Exception, IOException {
		this.getConnection();
		try {
			if (admin == null)
				admin = conn.getAdmin();
		} catch (IOException e) {
			throw new BD2Exception("Ocorreu um erro ao tentar recuperar o admin" + e);
		}
		return admin;
	}

	/**
	 * Método responsável por criar uma tabela no banco de dados.
	 * 
	 * Passando por parametro o nome da tabela e suas colunas.
	 * 
	 * @throws BD2Exception
	 * @throws IOException
	 */
	public void createTable(String name, String[] familys) throws BD2Exception,
			IOException {
		this.getAdmin();
		TableName tableName = TableName.valueOf(name);
		if (!admin.tableExists(tableName)) {
			HTableDescriptor hTableDescriptor = new HTableDescriptor(
					tableName);
			for (int i = 0; i < familys.length; i++) {
				hTableDescriptor.addFamily(new HColumnDescriptor(familys[i]));
			}
			admin.createTable(hTableDescriptor);
		}
	}

	/**
	 * Remover uma tabela existente no banco de dados.
	 * 
	 * @param nome
	 *            da tabela a ser removida.
	 */
	public void deleteTable(String name) throws BD2Exception {
		try {

			this.getAdmin();
			TableName tableName = TableName.valueOf(name);
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		} catch (IOException e) {
			throw new BD2Exception(
					"Acorreu algum problema ao tentar remover tabela "
							+ e.getMessage());
		}
	}
}

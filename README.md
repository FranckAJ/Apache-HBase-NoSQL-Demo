#Data Base NoSql HBase - Demo
### Demostração de uso do banco de dados NoSql família de colunas.

### Info
HBase (https://hbase.apache.org/) é um banco de dados NoSQL, famílias de colunas que roda em cima do Hadoop (http://hadoop.apache.org/) como armazenamento de dados grande distribuído e escalável. Isto significa que HBase pode alavancar o paradigma de processamento distribuído do Hadoop Distributed File System (HDFS) e beneficiar de modelo de programação MapReduce do Hadoop. Ele se destina a acolher grandes tabelas com bilhões de linhas com potencialmente milhões de colunas e executar em um cluster de hardware commodity. Mas além de suas raízes Hadoop, HBase é um poderoso banco de dados em seu próprio direito, que combina recursos de consulta em tempo real com a velocidade de um armazenamento de chave / valor e processamento off-line ou em lote através de MapReduce. Em suma, HBase permite-lhe consultar registros individuais, bem como relatórios analíticos agregados obtêm em uma enorme quantidade de dados.


### Pré-requisitos
  * Sistema Operacional: Linux/Windows (USE LINUX É FODA!)
  * HBASE: http://mirror.nbtelecom.com.br/apache/hbase/stable/
  * JDK (Java Development kit), versão utilizada 1.8.
  * Variável de ambiante do java configurada JAVA_HOME.
  * Variável de ambiente do HBase configurada HBASE_HOME.
  

### Execução
* Iniciar HBase (Linha de comando): start-hbase.sh (Em qualquer diretório se o HBASE_HOME estiver configurado)
* Parar Hbase (Linha de comando): stop-hbase.sh (Em qualquer diretório se o HBASE_HOME estiver configurado)
* local Shell (Linha de comando): hbase shell em (instalacao-do-hbase/bin) 
* Master host (interface): http://localhost:16010/

### Aplicação Demo (Exemplo CRUD Livro)
 * Criar tabela.
 * Remover tabela.
 * Inserir registro em familias de colunas (demo: Coluna BOOK, SESSION, EDITION).
 * Atualizar registros.
 * Remover registros.
 * Remover todos os registros de uma vez.
 * Consulta todos os registros de uma vez (Demo: consulta todos os livros).
 * Consulta por linha da tabela (Demo: consulta ISBN (key da tabela)).
 * Consulta por campo (Demo: consulta livros por nome, uso DO EQUAL).
 * Consulta por campo (Demo: consulta livros por quantidade de paginas, uso do GREATER_OR_EQUAL).
 * Consulta por qualificadores da tabela.
 * Consulta por Família de colunas.
 * Consulta por multiplas colunas de uma vez.


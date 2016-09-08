#Data Base NoSql HBase - Demo
### Demostração de uso do banco de dados NoSql família de colunas.

### Info


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
 * Inserir registro em colunas (demo: Coluna BOOK, SESSION, EDITION).
 * Atualizar registros.
 * Remover registros.
 * Remover todos os registros de uma vez.
 * Consulta todos os registros de uma vez (Demo: consulta todos os livros).
 * Consulta por linha da tabela (Demo: consulta ISBN (key da tabela)).
 * Consulta por campo (Demo: consulta livros por nome, uso DO EQUAL).
 * Consulta por campo (Demo: consulta livros por quantidade de paginas, uso do GREATER_OR_EQUAL).
 * Consulta por qualificadores da tabela.
 * Consulta por Família de colunas.
 * Consulta por multiplicas colunas de uma vez.


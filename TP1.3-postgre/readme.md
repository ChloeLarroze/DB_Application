## How to use
After setting up a PostgreSQL Server and a new DB, enter the following cmd: 

```bash 
asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=5432:password=password:user=etuemse:serverName=localhost:databaseName=biblio biblioPool #create conection

asadmin create-jdbc-resource --connectionpoolid biblioPool jdbc/biblio #create ress.

asadmin ping-connection-pool PostgreSQLPool #ping
```
package eggboss.clipper.jdbc;

import java.sql.Connection;

public interface JDBCConnector {
	public Connection getConnection();
}

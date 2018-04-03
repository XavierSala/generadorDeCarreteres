package net.xaviersala.generacarreteres.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NomsDBPostgreSQL extends NomsDBDatabase {
	
	private static final String dropTaulaCiutats = "DROP TABLE IF EXISTS CIUTATS";
	private static final String dropTaulaCarreteres = "DROP TABLE IF EXISTS CARRETERES";
	private static final String crearTaulaCiutats = "CREATE TABLE CIUTATS ("
			                                            + "id VARCHAR(10) PRIMARY KEY, "
			                                            + "nom VARCHAR(50))";
	private static final String crearTaulaCarreteres = "CREATE TABLE CARRETERES("
			                                            + "codi SERIAL PRIMARY KEY,"
			                                            + "id1 VARCHAR(10) REFERENCES CIUTATS, "
			                                            + "id2 VARCHAR(10) REFERENCES CIUTATS, "
			                                            + "KM integer)";
	private static final String insertCiutat = "INSERT INTO CIUTATS (id, nom) VALUES (?, ?)";
	private static final String insertCarretera = "INSERT INTO CARRETERES (id1, id2, KM) VALUES (?, ?, ?)";

	public NomsDBPostgreSQL() throws SQLException {		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/ciutats", "postgres", "ies2010");
		init();		
	}
	
	@Override
	public String insertCiutat() {
		return insertCiutat;
	}

	@Override
	public String insertCarretera() {
		return insertCarretera;
	}

	@Override
	public void init() throws SQLException {
		Statement st = conn.createStatement();
		st.execute(dropTaulaCarreteres);
		st.execute(dropTaulaCiutats);		
		st.execute(crearTaulaCiutats);
		st.execute(crearTaulaCarreteres);		
		st.close();		
	}

}

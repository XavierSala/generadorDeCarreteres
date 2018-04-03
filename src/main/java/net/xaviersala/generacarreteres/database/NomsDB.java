package net.xaviersala.generacarreteres.database;

import java.sql.SQLException;

import org.graphstream.graph.Graph;

public interface NomsDB {
	void desar(Graph graph) throws SQLException;
}

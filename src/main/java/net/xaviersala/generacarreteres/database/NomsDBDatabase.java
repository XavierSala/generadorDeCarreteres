package net.xaviersala.generacarreteres.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.graphstream.graph.*;

public abstract class NomsDBDatabase implements NomsDB {

	protected Connection conn;

	public abstract String insertCiutat();
	public abstract String insertCarretera();

	public abstract void init() throws SQLException;

	public void desar(Graph graph) throws SQLException {

		conn.setAutoCommit(false);

		desaPobles(graph);
		desaCarreteres(graph);

		conn.commit();

	}

	private void desaPobles(Graph graph) throws SQLException {
		PreparedStatement pes = conn.prepareStatement(insertCiutat());

		for (Node node : graph) {
			String nom = node.getAttribute("label");
			pes.setString(1, node.getId());
			pes.setString(2, nom);
			int quants = pes.executeUpdate();
			if (quants == 0) {
				System.out.println("Ha fallat " + node.getId());
			}
		}
		pes.close();

	}

	private void desaCarreteres(Graph graph) throws SQLException {
		for (Node node : graph) {
			desaCarretera(node, graph);
		}
	}

	private void desaCarretera(Node node, Graph graph) throws SQLException {
		PreparedStatement pes = conn.prepareStatement(insertCarretera());

		for (Edge carretera : node.getEdgeSet()) {
			Node candidat = carretera.getOpposite(node);
			int km = carretera.getAttribute("km");
			pes.setString(1, node.getId());
			pes.setString(2, candidat.getId());
			pes.setInt(3, km);
			int quants = pes.executeUpdate();
			if (quants == 0) {
				System.out.println("Ha fallat " + node.getId() + " " + candidat.getId());
			}
		}
		pes.close();

	}

}

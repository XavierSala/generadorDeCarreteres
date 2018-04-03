package net.xaviersala.generacarreteres;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import net.xaviersala.GeneradorDePobles.GeneradorDePobles;
import net.xaviersala.generacarreteres.database.NomsDB;
import net.xaviersala.generacarreteres.database.NomsDBPostgreSQL;
import net.xaviersala.generacarreteres.model.Poble;

/**
 * Programa que crea una llista de pobles i les carreteres que 
 * els uneixen.
 *  - Representa gràficament els pobles i les carreteres (amb el seu pès)
 *  - Emmagatzema les dades en una base de dades (Per ara Postgres)
 *  
 * @author xavier
 *
 */
public class App {

	// Número de pobles que s'han de generar
	private static final int NUMERODEPOBLES = 12;
	
	// Número mínim de veins
	private static final int MINVEINS = 2;
	// Número màxim de veins
	private static final int MAXVEINS = 4;

	// Per quan sembla que no té més possibles opcions de veins
	private static final int MAXINTENTS = 10;

	// Número màxim de Km entre ciutats
	private static final int MAXKM = 6;
	
	private static final boolean SAVEDATABASE = false;
	private static final String DATABASE_JDBC = "jdbc:postgresql://localhost/ciutats";
	private static final String DATABASE_USER = "postgres";
	private static final String DATABASE_PASSWORD = "ies2010";
			
	

	private static Random aleatori = new Random();
	private static Graph graph;
	
	public static void main(String args[]) {
		
		System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");		
		graph = new SingleGraph("Pobles");
		graph.addAttribute("ui.antialias"); 
		graph.setAttribute("ui.stylesheet", "url(mapa.css);");
		
		// graph.addAttribute("ui.stylesheet", "edge {text-alignment: along; text-background-color:white; }");

		GeneradorDePobles generador = new GeneradorDePobles();
		
		// Només per evitar repeticions de pobles
		Set<Poble> candidats = new HashSet<Poble>();

		// Crear l'estructura dels pobles 
		for (int i = 0; i < NUMERODEPOBLES; i++) {
			Poble poble = new Poble(generador.generaNomDePoble());
			if (candidats.add(poble)) {
				graph.addNode(poble.getId());
				Node vila = graph.getNode(poble.getId());
				vila.addAttribute("label", poble.getNom());	
				vila.setAttribute("max", MINVEINS + aleatori.nextInt(MAXVEINS));
			}
		}
		candidats.clear();

		// Generar els enllaços entre pobles
		generaCarreteres();

		// Pintar els nodes 
		graph.display();
		
		
		// Desar en la base de dades 
		if (SAVEDATABASE) {
			try {
				NomsDB database = new NomsDBPostgreSQL(DATABASE_JDBC, DATABASE_USER, DATABASE_PASSWORD);
				database.desar(graph);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
				
	}

	/**
	 * Genera les carreteres (edges) de cada una de les ciutats (node)
	 */
	private static void generaCarreteres() {

		for (Node node: graph) {
			int max = node.getAttribute("max");
			if (node.getDegree() < max ) {
				int quantsVeins = max - node.getDegree();
				for(int i=0; i < quantsVeins; i++) {
					generaCarretera(node);
				}
			}
			
		}
	}
	
	/**
	 * Genera les carreteres que han de sortir d'un determinat node
	 * @param node node del que es generaran
	 */
	private static void generaCarretera(Node node) {
		
		boolean afegit = false;
		
		// Per evitar que no acabi mai
		int provats = 0;
		
		do {			
			provats++; 
			// Si s'han fet molts intents i no n'hem trobat cap
			if (provats == MAXINTENTS) { 
				afegit = true;
				break;
			}
			
			int numCandidat = aleatori.nextInt(graph.getNodeCount());
			Node candidat = graph.getNode(numCandidat);
			int maxCandidat = candidat.getAttribute("max");
			if (candidat.getDegree() < maxCandidat && !candidat.equals(node)) {				
				if (!node.hasEdgeToward(candidat)) {
					afegit = true;
					String identificador = node.getId()+"-"+candidat.getId();
					graph.addEdge(identificador, node, candidat);
					int km = 1 + aleatori.nextInt(MAXKM);
					Edge edge = graph.getEdge(identificador);
					edge.setAttribute("km", km);
					edge.addAttribute("ui.label", String.valueOf(km));
					// edge.addAttribute("ui.style", "fill-color: blue;size: 2px; text-size: 15; text-color: blue; text-background-mode: plain;");
					afegit = true;
				}				
			}
			
		} while(afegit == false);
				
	}




}

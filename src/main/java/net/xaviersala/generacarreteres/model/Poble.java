package net.xaviersala.generacarreteres.model;

public class Poble {
	
	private static int nextId = 0;
	private int id;
	private String idString;
	private String nom;
	
	public Poble(String nom) {
		id = ++nextId;
		idString = String.valueOf(id);
		this.nom = nom;
	}

	public String getId() {
		return idString;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Poble [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append("]");
		return builder.toString();
	}
	
	

}

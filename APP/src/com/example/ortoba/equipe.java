package com.example.ortoba;

public class equipe {
	int id;
    String nom;
    String ville;
    int score;
 
    equipe(int Id, String Nom, String Ville, int Score){
    	this.id = Id;

    	this.nom = Nom;

    	this.ville = Ville;

    	this.score = Score;
      }
    
    public int getId()
    {
    	return id;
    }
    
    public void setId(int Id)
    {
    	id = Id;
    }
    
    public String getNom()
    {
    	return nom;
    }
    
    public void setNom( String Nom )
    {
    	nom = Nom;
    }
    
    public String getVille()
    {
    	return ville;
    }
    
    public void setVille( String Ville)
    {
    	ville = Ville;
    }
    
    public int getScore()
    {
    	return score;
    }
    
    public void setScore( int Score )
    {
    	score = Score;
    }
    
}

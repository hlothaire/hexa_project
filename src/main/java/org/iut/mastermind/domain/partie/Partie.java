package org.iut.mastermind.domain.partie;

import org.iut.mastermind.domain.proposition.MotSecret;
import org.iut.mastermind.domain.proposition.Reponse;

import java.util.Optional;

public class Partie {
    private static final int NB_ESSAIS_MAX = 5;
    private final Joueur joueur;
    private final String motADeviner;
    private int nbEssais;
    private boolean partieTerminee;

    public Partie(Joueur joueur, String motADeviner, int nbEssais, boolean partieTerminee) {
        this.joueur = joueur;
        this.motADeviner = motADeviner;
        this.nbEssais = nbEssais;
        this.partieTerminee = partieTerminee;
    }

    public static Partie create(Joueur joueur, String motADeviner) {
        return new Partie(joueur, motADeviner, 0, false);
    }

    public static Partie create(Joueur joueur, String motADeviner, int nbEssais) {
        return new Partie(joueur, motADeviner, nbEssais, false);
    }

    // getter joueur
    public Joueur getJoueur() {
        return joueur;
    }

    // getter nombre d'essais
    public int getNbEssais() {
        return nbEssais;
    }

    // getter mot à deviner
    public String getMot() {
        return motADeviner;
    }

    // si le nombre max d'essais n'est pas atteint
    // on compare la proposition au mot secret
    // et on renvoie la réponse
    // si toutes les lettres sont correctement placées,
    // on a terminé la partie
    public Reponse tourDeJeu(String motPropose) {
        Reponse reponse=new Reponse(motADeviner);
        if(verifieNbEssais()){
            reponse.compare(motPropose);
            nbEssais++;
            if (reponse.lettresToutesPlacees() || !verifieNbEssais()){
                done();
            }
        } else {
            done();
        }
        return reponse;
    }

    // vérifie que le nombre d'essais max n'est pas atteint
    private boolean verifieNbEssais()  {
        return this.nbEssais < NB_ESSAIS_MAX;
    }

    // la partie est-elle terminée
    public boolean isTerminee() {
        return partieTerminee;
    }

    // la partie est terminée
    void done() {
        partieTerminee = true;
    }
}

package iia.games.algo;

import iia.games.base.IGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class AlphaBeta implements IAlgo {

    /**
     * La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 4;

    // -------------------------------------------
    // Attributs
    // -------------------------------------------

    /**
     * La profondeur de recherche utilisée pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

    /**
     * Le joueur Min
     * (l'adversaire)
     */
    private String joueurMin;

    /**
     * Le joueur Max
     * (celui dont l'algorithme de recherche adopte le point de vue)
     */
    private String joueurMax;

    /**
     * Le nombre de noeuds développé par l'algorithme
     * (intéressant pour se faire une idée du nombre de noeuds développés)
     */
    private int nbnoeuds;

    /**
     * Le nombre de feuilles évaluées par l'algorithme
     */
    private int nbfeuilles;

    // un RNG pour selectionner autre chose que le premier coup avec le plus haut score
    private Random rand = new Random();
    private Date startTime;

    public AlphaBeta(String maxRole, String mminRole) {
        joueurMax = maxRole;
        joueurMin = mminRole;
    }

    public AlphaBeta(String maxRole, String minRole, int maxDepth) {
        joueurMax = maxRole;
        joueurMin = minRole;
        profMax = maxDepth;
    }

    @Override
    public String bestMove(IGame game, String role) {
        // on enregistre le moment de depart de l'algo pour ne pas depasser les 10 secondes réglementaires
        startTime = new Date();
        long elapsedTime = 0;
        // on informe quel algo on utilise
        System.out.println("[AlphaBeta]");
        // initialisation du nombre de noeuds/feuilles parcourus
        nbfeuilles = 0;
        nbnoeuds = 0;
        // On recupere la liste de coups pour le joueur ami.
        ArrayList<String> coupsPossibles = game.possibleMoves(joueurMax);
        // on initialise la liste des meilleurs coups
        ArrayList<String> listeMeilleursCoups = new ArrayList<String>();

        // Ca va etre la valeur heuristique correspondante au meilleur coup.
        // C'est aussi ce qui va nous permettre de comparer pour effectivement savoir si un coup est mieux qu'un autre
        double max = IGame.MIN_VALUE;
        // on initialise la profondeur
        int profActu = 1;
        // On va enregistrer l'evaluation heuristique de chaque coup dans se dictionnaire
        HashMap<String, Double> valeursCoups = new HashMap<>();
        // on remplit le dictionnaire avec les coups possibles pour le joueur ami
        for (String coup : coupsPossibles) {
            valeursCoups.put(coup, max);
        }
        // on explore en augmentant la profondeur a chaque fois jusqu'a la profondeur maximale ou jusqu'a ce qu'il n'y ait plus de temps
        while (profActu <= profMax) {
            // pour chaque coup possible du joueur ami
            for (String move : coupsPossibles) {
                // on verifie le temps qui nous reste
                Date timeActu = new Date();
                elapsedTime = timeActu.getTime() - startTime.getTime();
                if (elapsedTime < 9400) {
                    // on evalue le coup en explorant l'arbre avec l'algorithme
                    IGame new_b = game.play(move, joueurMax);
                    Double newVal = this.minMax(new_b, profActu, IGame.MIN_VALUE, IGame.MAX_VALUE);
                    // Si l'algorithme se rend compte qu'il n'a plus de temps, il abandonne l'exploration et renvoie null
                    // on ne prend en compte que les coups totalement explorés
                    if (newVal != null) {
                        // si le coup a été exploré totalement a la nouvelle profondeur on replace son evaluation avec la nouvelle
                        valeursCoups.replace(move, newVal);
                    }
                }
            }
            if (elapsedTime > 9400) {
                break;
            }
            profActu++;
        }

        // on met a jour la liste des meilleurs coups avec les données de l'exploration
        for (String coup : coupsPossibles) {
            double v = valeursCoups.get(coup);
            System.out.println("Le coup " + coup + " a pour valeur minimax " + v);
            if (v > max) {
                listeMeilleursCoups.clear();
                listeMeilleursCoups.add(coup);
                max = v;
            } else if (v == max) {
                listeMeilleursCoups.add(coup);
            }
        }
        // on selectionne aléatoirement un coup parmi les meilleurs, ce qui fonctionne mieux que de prendre le premier
        String meilleurCoup = listeMeilleursCoups.get(rand.nextInt(listeMeilleursCoups.size()));

        // Affichage des informations importantes
        System.out.println("Le meilleur coup est : " + meilleurCoup);
        System.out.println("Nombre de noeuds parcourus : " + this.nbnoeuds);
        System.out.println("Nombre de noeuds decouvertes : " + this.nbfeuilles);
        System.out.println("Profondeur Maximale atteinte : " + profActu);

        // on retourne le coup a jouer
        return meilleurCoup;
    }

    /**
     * Evaluation de la valeur AlphaBeta du noeud AMI
     *
     * @param game le plateau correspondant au noeud
     * @return Un entier, la valeur max trouvee.
     **/
    public Double maxMin(IGame game, int profondeur, double alpha, double beta) {
        Date actuTime = new Date();
        // si il n'y a plus le temps on arrete l'exploration et on renvoie null
        if (actuTime.getTime() - startTime.getTime() > 9700) {
            return null;
        } else if (profondeur == 0 || game.isGameOver()) {
            // Astuce pour compter le nombre de feuilles
            this.nbfeuilles++;
            return game.getValue(this.joueurMax);
        } else {
            // Astuce pour compter le nombre de noeuds
            this.nbnoeuds++;
            for (IGame succ : game.successors(joueurMax)) {
                Double newAlpha = minMax(succ, profondeur - 1, alpha, beta);
                if (newAlpha == null) return null;
                alpha = Math.max(alpha, newAlpha);
                if (alpha >= beta) return beta;
            }
        }
        return alpha;
    }

    /**
     * Evaluation de la valeur AlphaBeta du noeud ennemi
     *
     * @param game le plateau correspondant au noeud
     * @return Un entier, la valeur max trouvee.
     **/
    public Double minMax(IGame game, int profondeur, double alpha, double beta) {
        Date actuTime = new Date();
        // L'ennemi est en fin de partie (plateau = feuille; joueur = ennemi)
        if (actuTime.getTime() - startTime.getTime() > 9700) {
            return null;
        } else if (profondeur == 0 || game.isGameOver()) {
            // Astuce pour compter le nombre de feuilles
            this.nbfeuilles++;
            return game.getValue(this.joueurMin);
        }
        // On simule le coup de ennemi, il doit faire son meilleur coup
        else {
            // Astuce pour compter le nombre de noeuds parcourus
            this.nbnoeuds++;
            for (IGame succ : game.successors(joueurMin)) {
                Double newBeta = maxMin(succ, profondeur - 1, alpha, beta);
                if (newBeta == null) return null;
                beta = Math.min(beta, newBeta);
                if (alpha >= beta) return alpha;
            }
            return beta;
        }
    }
}

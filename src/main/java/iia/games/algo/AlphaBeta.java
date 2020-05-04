package iia.games.algo;

import iia.games.base.IGame;

import java.util.ArrayList;
import java.util.Date;
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

    Random rand = new Random();
    Date startTime;

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
        startTime = new Date();
        long elapsedTime = 0;


        System.out.println("[AlphaBeta]");

        nbfeuilles = 0;
        nbnoeuds = 0;
        // On initialise par le premier coup par defaut.
        ArrayList<String> coupsPossibles = game.possibleMoves(joueurMax);
        ArrayList<String> listeMeilleursCoups = new ArrayList<String>();

        // Ca va etre la valeur heuristique correspondante au meilleur coup.
        // C'est aussi ce qui va nous permettre de comparer pour effectivement savoir si un coup est mieux qu'un autres
        int max = IGame.MIN_VALUE;
        int profActu = 1;
        while (profActu < profMax) {
            System.out.println(elapsedTime);
            System.out.println("PROFONDEUR EXPLOREE : " + profActu);
            for (String move : coupsPossibles) {
                Date timeActu = new Date();
                elapsedTime = timeActu.getTime() - startTime.getTime();
                if (elapsedTime < 9000) {
                    IGame new_b = game.play(move, joueurMax);
                    int newVal = this.minMax(new_b, profActu, IGame.MIN_VALUE, IGame.MAX_VALUE);
                    System.out.println("Le coup " + move + " a pour valeur minimax " + newVal);
                    if (newVal > max) {
                        listeMeilleursCoups.clear();
                        listeMeilleursCoups.add(move);
                        max = newVal;
                    } else if (newVal == max) {
                        listeMeilleursCoups.add(move);
                    }
                }
            }
            profActu++;
        }
        String meilleurCoup = listeMeilleursCoups.get(rand.nextInt(listeMeilleursCoups.size()));

        // Affichage des informations importantes

        System.out.println("Le meilleur coup est : " + meilleurCoup);
        System.out.println("Nombre de noeuds parcourus : " + this.nbnoeuds);
        System.out.println("Nombre de noeuds decouvertes : " + this.nbfeuilles);

        return meilleurCoup;
    }


    /**
     * Evaluation de la valeur AlphaBeta du noeud AMI
     *
     * @param game le plateau correspondant au noeud
     * @return Un entier, la valeur max trouvee.
     **/
    public int maxMin(IGame game, int profondeur, int alpha, int beta) {
        Date actuTime = new Date();
        if (actuTime.getTime() - startTime.getTime() > 9900) {
            return IGame.MIN_VALUE;
        } else
        if (profondeur == 0 || game.isGameOver()) {
            // Astuce pour compter le nombre de feuilles
            this.nbfeuilles++;
            return game.getValue(this.joueurMax);
        } else {
            // Astuce pour compter le nombre de noeuds
            this.nbnoeuds++;
            for (IGame succ : game.successors(joueurMax)) {
                alpha = Math.max(alpha, minMax(succ, profondeur - 1, alpha, beta));
                if (alpha >= beta) {
                    return beta;
                }
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
    public int minMax(IGame game, int profondeur, int alpha, int beta) {
        Date actuTime = new Date();
        // L'ennemi est en fin de partie (plateau = feuille; joueur = ennemi)
        if (actuTime.getTime() - startTime.getTime() > 9900) {
            return IGame.MAX_VALUE;
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
                beta = Math.min(beta, maxMin(succ, profondeur - 1, alpha, beta));
                if (alpha >= beta) {
                    return alpha;
                }
            }
            return beta;
        }
    }
}

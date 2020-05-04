package iia.games.algo;

import iia.games.base.IGame;
import iia.games.squadro.ASquadroGame;

import java.util.ArrayList;
import java.util.Random;

public class Minimax implements IAlgo {
    private final static int DEPTHMAXDEFAUT = 4;
    private int depthMax = DEPTHMAXDEFAUT;
    private final String roleMax;
    private final String roleMin;
    private int nbNodes;
    private int nbLeaves;

    Random rand = new Random();

    public Minimax(String maxRole, String minRole) {
        roleMax = maxRole;
        roleMin = minRole;
    }

    public Minimax(String maxRole, String minRole, int maxDepth) {
        roleMax = maxRole;
        roleMin = minRole;
        depthMax = maxDepth;
    }

    @Override
    public String bestMove(IGame game, String role) {
        System.out.println("[MiniMax]");

        this.nbNodes = 1;
        this.nbLeaves = 0;
        ArrayList<String> listeMeilleursCoups = new ArrayList<String>();
        int bestMoveHeuristicValue = IGame.MIN_VALUE;
        // On calcul tous les coups possibles
        ArrayList<String> allMoves = game.possibleMoves(roleMax);
        for (String move : allMoves) {
            int heuristicMove;
            IGame new_b = game.play(move, roleMax);
            heuristicMove = minmax(new_b, depthMax - 1);
            System.out.println("Le coup " + move + " a pour valeur minimax " + heuristicMove);
            if (heuristicMove > bestMoveHeuristicValue) {
                bestMoveHeuristicValue = heuristicMove;
                listeMeilleursCoups.clear();
                listeMeilleursCoups.add(move);
            } else if (bestMoveHeuristicValue == heuristicMove) {
                listeMeilleursCoups.add(move);
            }
        }
        String bestMove = listeMeilleursCoups.get(rand.nextInt(listeMeilleursCoups.size()));
        return bestMove;
    }
    /*
    PUBLIC METHODS
    ==============
     */

    public String toString() {
        return "MiniMax(ProfMax=" + depthMax + ")";
    }

    public int getNbNodes() {
        return nbNodes;
    }

    public int getNbLeaves() {
        return nbLeaves;
    }

    /*
            PRIVATE METHODS
            ===============
             */
    public int maxmin(IGame game, int depth) {
        // C'est joueurMax qui joue
        if (depth == 0 || game.isGameOver()) {
            nbLeaves++;
            return game.getValue(roleMax);
        } else {
            nbNodes++;
            int maxValue = IGame.MIN_VALUE;
            // System.out.println("Coups Possibles for " + plateau + " :  "+ lesCoups);
            for (IGame succ : game.successors(roleMax)) {
                maxValue = Math.max(maxValue, minmax(succ, depth - 1));
                // System.out.println("maxValue for " + nPlateau + " =  "+ maxValue);
            }
            // System.out.println("maxValue " + plateau + " =  "+ maxValue);
            return maxValue;
        }
    }

    public int minmax(IGame game, int depth) {
        // C'est joueurMin qui joue
        if (depth == 0 || game.isGameOver()) {
            nbLeaves++;
            return game.getValue(roleMin);
        } else {
            nbNodes++;
            int minValue = IGame.MAX_VALUE;
            // System.out.println("Coups Possibles for " + plateau + " :  "+ lesCoups);
            for (IGame succ : game.successors(roleMin)) {
                minValue = Math.min(minValue, maxmin(succ, depth - 1));
                //System.out.println("minValue for " + nPlateau + " =  "+ minValue);
            }

            // System.out.println("minValue = " + minValue);
            return minValue;
        }
    }

}

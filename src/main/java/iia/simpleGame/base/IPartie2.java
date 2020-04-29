package iia.simpleGame.base;

import java.io.IOException;
import java.util.ArrayList;

public interface IPartie2 {
    /** Initialise un plateau a partir d'un fichier texte
     * @param fileName le nom du fichier
     */
    public void setFromFile(String fileName) throws IOException;

    /** Sauve la configuration de l'etat courant (plateau et pieces restantes) dans un fichier
     * @param fileName le nom du fichier a sauvegarder
     * Le format doit etre compatoble avec celui utilise pour la lecture
     */
    public void saveToFile(String fileName) throws IOException;

    /** Indique si le coup <move> est valide pour le joueur <player> sur le plateau courant
     * @param move le coup a jouer, sous la forme "A4-C4"
     * @param player le joueur qui joue, represente par "vertical" ou "horizontal"
     */
    public boolean isValidMove(String move, String player);

    /** Calcul les coups possibles pour le joueur <player> sur le plateau courant
     * @param player le joueur qui joue, represente par "vertical" ou "horizontal"
     */
    public ArrayList<String> possibleMoves(String player);

    /** Modifie le plateau en jouant le coup move avec la pieve choose
     * @param move le coup a jouer, sous la forme "A4-C4"
     * @param player le joueur qui joue, represente par "vertical" ou "horizontal"
     */
    public void play(String move, String player);

    /** Vrai lorsque le plateau correspond a une fin de partie
     */
    public boolean gameOver();
}

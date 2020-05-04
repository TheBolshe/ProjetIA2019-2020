/*
 * Nom de classe : MyChallenger
 *
 * Desciption : Cette classe sert a faire l'intermediaire entre le serveur arbitre et notre joueur implemnente dans ce code
 *
 * Version : 1.0
 *
 * Date : 3/5/2020
 *
 * Copyright : Simon Bibitchkov, Guy-Vianney Krakowiak, les profs d'IA pour le squellette du code.
 */

package iia.games.squadro;

import iia.games.algo.AlphaBeta;
import iia.games.algo.IAlgo;
import iia.games.algo.Minimax;
import iia.games.base.IChallenger;

public class MyChallenger2 implements IChallenger {
    ASquadroGame game;
    String my_side;
    String other_side;
    IAlgo algo;

    public MyChallenger2(){ }

    @Override
    public String teamName() {
        return "Bibitchkov_Krakowiak2";
    }

    @Override
    public void setRole(String role) {
        switch (role){
            case "HORIZONTAL":
                this.game = new SquadroGameH();
                this.my_side = "HORISONTAL";
                this.other_side = "VERTICAL";
                break;
            case "VERTICAL":
                this.game = new SquadroGameV();
                this.my_side = "VERTICAL";
                this.other_side = "HORISONTAL";
                break;
            default:
        }
        this.algo = new Minimax(my_side, other_side, 6);
    }

    @Override
    public void iPlay(String move) {
        this.game.updateBoard(move, this.my_side);
        // this.game.printBoard();
    }

    @Override
    public void otherPlay(String move) {
        this.game.updateBoard(move, this.other_side);
        // this.game.printBoard();
    }

    @Override
    public String bestMove() {
        return this.algo.bestMove(this.game,my_side);
    }

    @Override
    public String victory() {
        return "Merci à vous d’être là ce soir ! Vous êtes des dizaines de milliers et je ne vois que quelques visages. Merci, merci d’être là, de vous être battus avec courage et bienveillance pendant tant de mois. Parce que oui, ce soir, vous l’avez emporté ! La France l’a emporté ! Ce que nous avons fait, depuis tant et tant de mois, n’a ni précédent, ni équivalent. Tout le monde nous disait que c’était impossible, mais ils ne connaissaient pas la France !";
    }

    @Override
    public String defeat() {
        return "I had not drafted a concession speech. I had been working on a victory speech";
    }

    @Override
    public String tie() {
        return "Je suis content de ce que vous avez fait collectivement, en terme d’état d’esprit. Voilà, c’est un point de plus. Il faudra finir le job en novembre les mecs. Déçu? Oui. Je le répète, c’est normal quand on va au bout de soi-même et collectivement, qu’il n’y ait pas la récompense. Au mois de novembre, à nous de plier l’affaire, pour atteindre notre objectif. Bravo à tous les gars!";
    }
}

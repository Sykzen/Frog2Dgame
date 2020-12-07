package environnment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Static;


public class BonusScore {
    private boolean inGame = true;
    private Game game;
    private Case pos;


    BonusScore(Game game, int i){
        this.game = game;
        this.pos = new Case(game.randomGen.nextInt(game.width), i);
    }

    void addToGraphics() {
        if(this.inGame){
            this.game.getGraphic().add(new Element(this.pos.absc, this.pos.ord - this.game.score, Static.eat));
        }
    }

    boolean posFulFilled(Case pos) {
        return pos.absc == this.pos.absc && pos.ord == this.pos.ord && inGame;
    }

    void used(){
        this.inGame = false;
    }
}
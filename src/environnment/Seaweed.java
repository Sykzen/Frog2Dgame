package environnment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Direction;
import util.Static;


class Seaweed {
    private Game game;
    private Case pos;
    private boolean LeftToRight;
    private int length;

    Seaweed(Game game, int i){
        this.game = game;
        this.length = game.randomGen.nextInt(3) + 3;
        this.pos = new Case(game.randomGen.nextInt(game.width-8)+4, i);
        this.LeftToRight = game.randomGen.nextBoolean();
    }

    void move(boolean b) {
        if (b) {
            if(this.pos.absc == 0 || this.pos.absc == game.width-this.length){
                this.LeftToRight = !this.LeftToRight;
            }
            this.pos = new Case(this.pos.absc + (this.LeftToRight ? 1 : -1), this.pos.ord);
        }
        this.addToGraphics();
    }

    private void addToGraphics() {
        for(int i = 0; i < this.length; ++i) {
            this.game.getGraphic().add(new Element(this.pos.absc + i, this.pos.ord - this.game.score, Static.seaweed));
        }
    }

    Direction getLeftToRight(){
        if (this.LeftToRight){
            return Direction.right;
        }else{
            return Direction.left;
        }
    }

    boolean posFulFilled(Case pos) {
        if (pos.ord != this.pos.ord) {
            return false;
        } else {
            return pos.absc >= this.pos.absc && pos.absc < this.pos.absc + this.length;
        }
    }

}

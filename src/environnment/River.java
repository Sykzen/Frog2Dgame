package environnment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Static;


public class River {
    private Game game;
    private int i;
    private int speed;
    private int timer;
    private Seaweed seaweed; /* seaweed pour les algues ou la grenouille peut sauter */
    /* Constructeur----------------------------------------------------------- */
    public River(Game game, int i){
        this.game = game;
        this.i = i;
        this.speed = game.randomGen.nextInt(game.minSpeed) + 1;
        this.seaweed = new Seaweed(game, i);
    }
    /* Méthode----------------------------------------------------------- */
    /* update all  */
    public void update() {
        pop();
        ++this.timer;
        if (this.timer <= this.speed) {
            this.seaweed.move(false);
        } else {
            this.seaweed.move(true);

            if(this.game.getFrogPosition() == this.i){
                this.game.moveFrog(this.seaweed.getLeftToRight());
            }
            this.timer = 0;
        }


    }
    /* Check si la case est safe */
    public boolean isSafe(Case pos) {
        return seaweed.posFulFilled(pos);
    }
    /* Ajouter le logo */
    private void pop(){
        for(int i = 0; i < this.game.width; ++i) {
            Element waterOfRiver= new Element(i, this.i - this.game.score, Static.water);
            this.game.getGraphic().add(waterOfRiver);
        }
    }
}


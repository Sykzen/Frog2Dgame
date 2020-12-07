package FroggerInfini;

import util.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;
import util.Direction;
import util.Static;


public class FrogInf implements IFrog {
    private Case position;
    private Direction direction;
    private Game game;

    public FrogInf(Game game) {
        this.position = new Case(game.width/2, 1);
        this.direction = Direction.up;
        this.game = game;
    }

    public Case getPosition() {
        return this.position;
    }

    public void move(Direction key) {
        this.direction = key;
        switch (key){
            case up:
                Case toUp = new Case(this.position.absc, this.position.ord + 1);

                this.position = toUp;
                ++this.game.score;
                if (this.game.score > this.game.maxScore) {
                    this.game.maxScore = this.game.score;
                    this.game.addLane();
                }
                break;
            case down:
                Case toDown = new Case(this.position.absc, this.position.ord -1);

                if (this.position.ord > 1){
                    this.position = new Case(this.position.absc, this.position.ord -1);
                    --this.game.score;
                }
                break;
            case right:
                Case toRight = new Case(this.position.absc +1, this.position.ord);

                if (this.position.absc < this.game.width - 1){ /* pour éviter que la grenouille sorte de l'écran */
                    this.position = new Case(this.position.absc +1, this.position.ord);
                }
                break;
            case left:
                Case toLeft = new Case(this.position.absc -1, this.position.ord);

                if(this.position.absc > 0){
                    this.position = new Case(this.position.absc -1, this.position.ord);
                }
                break;
        }
        this.game.RiverInf = (this.game.score+1)/10;
        this.game.getBonus(position);
        this.game.getGraphic().add(new Element(this.position.absc, 1, Static.frog));
        this.game.testLose(game.endFrog);
        if(game.scoreInTable(this.game.score)){

        }
    }

    public int getScore(){
        return this.game.score+this.game.scoreBonus;
    }

    public boolean onWater(){
        return (this.position.ord / 10) % 2 == 1;
    }


}

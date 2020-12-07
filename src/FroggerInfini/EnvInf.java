package FroggerInfini;

import environnment.Lane;
import environnment.River;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import java.util.ArrayList;

public class EnvInf implements IEnvironment {
    private ArrayList<Lane> lanes;
    private ArrayList<River> rivers;
    private Game game;

    public EnvInf(Game game) {
        this.game = game;
        this.lanes = new ArrayList<>();
        this.rivers = new ArrayList<>();
        this.lanes.add(new Lane(game, 0, 0));
        this.lanes.add(new Lane(game, 1, 0));

        for(int i = 2; i < game.height; ++i) {
            if((i/10)%2 == 0){
                this.addRue(i);
            }else {
                this.addRiver(i);
            }
        }

    }

    private void addRue(int i) {
        this.lanes.add(new Lane(this.game, i));
    }

    private void addRiver(int i) { this.rivers.add(new River(this.game, i)); }

    public boolean isSafe(Case c, boolean river) {
        if(river){
            return (this.rivers.get(c.ord -(this.game.RiverInf /2+1)*10)).isSafe(c);
        }else{
            return (this.lanes.get(c.ord -(this.game.RiverInf /2)*10)).isSafe(c);
        }
    }

    public void update() {
        for (Lane lane : this.lanes) {
            lane.update();
        }
        for (River river : this.rivers) {
            river.update();
        }
    }

    public void addLane() {
        int idx = this.game.score+this.game.height-1;
        if((idx/10)%2==0){
            this.addRue(idx);
        }else {
            this.addRiver(idx);
        }

    }




    public boolean getBonus(Case pos) {
        for (Lane lane : this.lanes) {
            if(lane.getBonus(pos)){
                return true;
            }
        }
        return false;
    }


}

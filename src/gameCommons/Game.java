package gameCommons;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;
import util.Direction;
import util.Static;


public class Game {

	public final Random randomGen = new Random();

	public int width;
	public int height;
	public int minSpeed;
	public final double defaultMove;
	public int score = 0;
	public int RiverInf = 0; /* distance entre les deux rivières la même */
	public int maxScore = 0; /* Score Maximum atteint */
	public ArrayList top5MaxScore= new ArrayList();
	public int scoreBonus = 0;
	public int endFrog = 0;

	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;


	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultMove) {
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeed = minSpeedInTimerLoop;
		this.defaultMove = defaultMove;
	}
	/* Set the frog */
	void setFrog(IFrog frog) {
		this.frog = frog;
	}
	/* Move The frog */
	public void moveFrog(Direction d){
		this.frog.move(d);
	}
	/* get the Actual frog position */
	public int getFrogPosition(){
		return this.frog.getPosition().ord;
	}
	/* Set the Env */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}
	/* Set the Graphic*/
	public IFroggerGraphics getGraphic() {
		return graphic;
	}
	/* try if the Game is over */
	public boolean testLose(int i) {
		if(i == 0){
			if(!this.environment.isSafe(frog.getPosition(), frog.onWater())){
				this.graphic.endTemplate("Vous avez perdu Votre score est de: " + (this.maxScore + this.scoreBonus));
				return true;
			}
		}
		return false;
	}




	/* Get the bonus when frog catch the eat */
	public void getBonus(Case pos){
		if(environment.getBonus(pos)){
			scoreBonus += 5;
		}
	}
	public boolean scoreInTable(int scoreOfThisGame){
		for(int i=0 ; i<top5MaxScore.size();i++) {
			int y= (int) top5MaxScore.get(i);
			if(scoreOfThisGame > y){return true;
		}

	}
		return false;
	}
 public  int getMaxScore(){
		return this.maxScore;
 }
 public void setTop5MaxScore(int score){
		top5MaxScore.remove(top5MaxScore.size());
		top5MaxScore.add(score);
		Collections.sort(top5MaxScore);

 }
 public ArrayList<Integer> getTopMaxScore(){
		return this.top5MaxScore;

 }


	void update() {
		graphic.clear();
		environment.update();
		graphic.add(new Element(frog.getPosition().absc,1, Static.frog));
		if(testLose(endFrog)){
			endFrog++;
		}

	}

	public void addLane() {
		this.environment.addLane();
	}
}

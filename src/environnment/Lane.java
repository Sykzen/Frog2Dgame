package environnment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Static;

import java.util.ArrayList;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private ArrayList<BonusScore> bonus = new ArrayList<>();
	private boolean LeftRight; // True - right | False - left
	private double density;
	private double densityOther;
	private int timer;

	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeed) + 2;
		this.LeftRight = game.randomGen.nextBoolean();
		this.density = density;
		this.densityOther = 0.3D;
		this.addBonusScore();
		for(int i = 0; i < game.width; ++i) {
			this.carMove(true);
			this.addCar();
		}
	}

	public Lane(Game game, int ord)
	{
		this(game, ord, game.defaultMove);
	}
	//M�thodes

	/**
	 * Met � jour la ligne consid�r�e.
	 */

	public void update() {
		addToGraphics();
		++this.timer;
		if (this.timer <= this.speed) {
			this.carMove(false);
		} else {
			this.carMove(true);
			this.addCar();
			this.timer = 0;
		}
		pop();
	}

	private void addToGraphics() {
		for (int i = 0; i < this.game.width; ++i) {
			this.game.getGraphic().add(new Element(i, this.ord - this.game.score, Static.rue));
		}
	}

	private void pop(){

		for (BonusScore bonus : this.bonus) {
			bonus.addToGraphics();
		}
	}
	/**
	 * Fait bouger toutes les voitures de la ligne.
	 * @param yn
	 */
	private void carMove(boolean yn) {
		for (Car car : this.cars) {
			car.Move(yn);
		}
		this.removeOldCars();
	}

	private void removeOldCars() {
		this.cars.removeIf(c -> !c.isInBounds());
	}

	private void addCar() {
		if (this.isSafe(this.getFirstCase()) && this.isSafe(this.getBeforeFirstCase()) && this.game.randomGen.nextDouble() < this.density) {
			this.cars.add(new Car(this.game, this.getBeforeFirstCase(), this.LeftRight));
		}
	}






	private void addBonusScore(){
		if(this.game.randomGen.nextDouble() < this.densityOther *0.5){
			this.bonus.add(new BonusScore(this.game,this.ord));
		}
	}
	/**
	 * V�rifie si il y a une voiture sur la case consid�r�e.
	 * @param tile
	 * @return true or false
	 */
	public boolean isSafe(Case tile) {
		for (Car car : this.cars) {
			if (car.posFulFilled(tile)) {
				return false;
			}
		}

		return true;
	}





	public boolean getBonus(Case pos){
		for (BonusScore bonus : this.bonus) {
			if(bonus.posFulFilled(pos)){
				bonus.used();
				return true;
			}
		}
		return false;
	}



	private Case getFirstCase() {
		return this.LeftRight ? new Case(0, this.ord) : new Case(this.game.width - 1, this.ord);
	}

	private Case getBeforeFirstCase() {
		return this.LeftRight ? new Case(-1, this.ord) : new Case(this.game.width, this.ord);
	}
}
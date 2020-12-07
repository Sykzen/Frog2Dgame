package environnment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Static;


class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight; // True - right | False - left
	private int length;

	Car(Game game, Case frontPosition, boolean leftToRight) {
		this.game = game;
		this.length = game.randomGen.nextInt(2) + 1;
		this.leftToRight = leftToRight;
		this.leftPosition = new Case(leftToRight ? frontPosition.absc - this.length : frontPosition.absc, frontPosition.ord);
	}
	//M�thodes

	/**
	 * fait avancer la voiture d'une case a gauche ou a droite en fonction de leftToRight.
	 * @param yn
	 */
	void Move(boolean yn) {
		if(yn) {
			if (this.leftToRight) {
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			}
			else {
				this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
			}
		}
		this.addToGraphics();
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for(int i = 0; i < this.length; ++i) {

			if (this.leftToRight){
			}
			this.game.getGraphic()
					.add(new Element(this.leftPosition.absc + i, this.leftPosition.ord - this.game.score, Static.car));
		}
	}

	/**
	 * V�rifie si la voiture consid�r�e est dans les limites de l'�cran.
	 * @return true or false
	 */
	boolean isInBounds() {
		if (this.leftToRight) {
			if (this.leftPosition.absc > game.width) {
				return false;
			}
		}
		else {
			if (this.leftPosition.absc + (this.length - 1) < 0) {
				return false;
			}
		}
		return true;
	}
	/**
	 * V�rifie si il y a une voiture sur la case consid�r�e.
	 * @param tile
	 * @return true or false
	 */
	boolean posFulFilled(Case tile) {
		if (tile.ord == this.leftPosition.ord) {
			if (tile.absc < this.leftPosition.absc || tile.absc > this.leftPosition.absc + this.length - 1) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
}

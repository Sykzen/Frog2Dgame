package froggerPart2;

import util.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;
import util.Direction;
import util.Static;


public class froggerPart2 implements IFrog {
	private Case position;
	private Direction direction;
	private Game game;

		public froggerPart2(Game game) {
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

		}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public boolean onWater() {
		return false;
	}


}

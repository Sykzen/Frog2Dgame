package gameCommons;


import util.Case;

public interface IFrog {

	Case getPosition();
	void move(util.Direction key);
	int getScore();
	boolean onWater();

}

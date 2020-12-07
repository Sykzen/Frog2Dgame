package gameCommons;

import util.Case;

public interface IEnvironment {

	boolean isSafe(Case c, boolean river);
	void update();
	void addLane();
	boolean getBonus(Case pos);

}

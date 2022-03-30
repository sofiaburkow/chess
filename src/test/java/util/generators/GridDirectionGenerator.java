// @author Martin Vatshelle - martin.vatshelle@uib.no
package util.generators;

import java.util.List;

import grid.GridDirection;

public class GridDirectionGenerator extends ElementGenerator<GridDirection> {
	/**
	 * New DirectionGenerator, will generate directions between 0° and 360°
	 */
	public GridDirectionGenerator() {
		super(GridDirection.EIGHT_DIRECTIONS);
	}

	/**
	 * New DirectionGenerator, will generate directions between minValue and maxVaue
	 */
	public GridDirectionGenerator(List<GridDirection> dirs) {
		super(dirs);
	}
}

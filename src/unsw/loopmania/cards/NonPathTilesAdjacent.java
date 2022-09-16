package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

/**
 * Allows the Card to only be placed adjacent to a path tile
 */
public class NonPathTilesAdjacent implements CardPlacementStrategy {
    @Override
    public boolean cardPlacement(int nodeX, int nodeY, List<Pair<Integer, Integer>> orderedPath) {
        for (Pair<Integer, Integer> p : orderedPath) {
            int x = p.getValue0();
            int y = p.getValue1();
            if (isAdjacent(nodeX, nodeY, x, y) && 
                !orderedPath.contains(new Pair<Integer, Integer>(nodeX, nodeY))) {
                return true;
            }
        }
        return false;
    } 

    public static boolean isAdjacent(int x, int y, int tileX, int tileY) {
        if ((x == tileX && y == (tileY - 1)) ||     // UP
            (x == (tileX + 1) && y == tileY) ||     // RIGHT
            (x == tileX && y == (tileY + 1)) ||     // DOWN
            (x == (tileX - 1) && y == tileY)) {     // LEFT
            return true;
        }
        return false;
    }
}


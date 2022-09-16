package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

/**
 * Allows the Card to be placed on path tiles
 */
public class PathTiles implements CardPlacementStrategy {
    @Override
    public boolean cardPlacement(int nodeX, int nodeY, List<Pair<Integer, Integer>> orderedPath) {
        Pair<Integer, Integer> pair = new Pair<>(nodeX, nodeY);
        boolean onPath = false;
        for (Pair<Integer, Integer> p : orderedPath) {
            if (p.equals(pair)) {
                onPath = true;
            }
        }
        return onPath;
    }
}

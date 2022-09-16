package unsw.loopmania.cards;

import java.util.List;
import org.javatuples.Pair;

/**
 * Allows the Card to be placed on Non Path Tiles
 */
public class NonPathTiles implements CardPlacementStrategy {
    @Override
    public boolean cardPlacement(int nodeX, int nodeY, List<Pair<Integer, Integer>> orderedPath) {
        Pair<Integer, Integer> pair = new Pair<>(nodeX, nodeY);
        for (Pair<Integer, Integer> p : orderedPath) {
            if (p.equals(pair)) {
                return false;
            }
        }
        return true;
    }
}

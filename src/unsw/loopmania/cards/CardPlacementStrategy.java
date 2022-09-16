package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

/**
 * Strategy interface for the placement of card
 */
public interface CardPlacementStrategy {
    public boolean cardPlacement(int nodeX, int nodeY, List<Pair<Integer, Integer>> orderedPath);
}

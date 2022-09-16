package unsw.loopmania;

public class RadiusChecker {

    public static boolean isWithinRadius(Entity entity1, Entity entity2, double radius){
        double distance = calculateDistance(entity1, entity2);
        if (distance <= radius){
            return true;
        }
        return false;
    }

    public static double calculateDistance(Entity entity1, Entity entity2){
        return Math.sqrt(Math.pow((entity1.getX()-entity2.getX()), 2) +  Math.pow((entity1.getY()-entity2.getY()), 2));
    }
}

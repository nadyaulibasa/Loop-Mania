package unsw.loopmania.buildings;

import unsw.loopmania.MovingEntity;

public interface BuildingEffect<RME,PME extends MovingEntity> {
    /**
     * Applies effect on the passed moving entity.
     * @param movingEntity : the moving entity.
     * @return : the moving entity.
     */
    public RME applyEffect(PME movingEntity);
}

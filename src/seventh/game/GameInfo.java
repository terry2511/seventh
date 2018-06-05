/*
 * see license.txt 
 */
package seventh.game;

import java.util.List;
import java.util.Random;

import seventh.ai.AISystem;
import seventh.game.entities.BombTarget;
import seventh.game.entities.Door;
import seventh.game.entities.Entity;
import seventh.game.entities.Flag;
import seventh.game.entities.PlayerEntity;
import seventh.game.entities.vehicles.Vehicle;
import seventh.game.type.GameType;
import seventh.map.Map;
import seventh.map.MapGraph;
import seventh.math.Rectangle;
import seventh.math.Vector2f;
import seventh.shared.EventDispatcher;
import seventh.shared.SeventhConfig;

/**
 * Used for acquiring information about the current game state
 * 
 * @author Tony
 *
 */
public interface GameInfo {

    
    /**
     * @param entity
     * @return a random position anywhere in the game world
     */
    public Vector2f findFreeRandomSpot(Entity entity);
    
    
    /**
     * @param entity
     * @param bounds
     * @return a random position anywhere in the supplied bounds
     */
    public Vector2f findFreeRandomSpot(Entity entity, Rectangle bounds);
    
    /**
     * 
     * @param entity
     * @param x
     * @param y
     * @param width
     * @param height
     * @return a random position anywhere in the supplied bounds
     */
    public Vector2f findFreeRandomSpot(Entity entity, int x, int y, int width, int height);
    
    /**
     * 
     * @param entity
     * @param x
     * @param y
     * @param width
     * @param height
     * @param notIn
     * @return a random position anywhere in the supplied bounds and not in the supplied {@link Rectangle}
     */
    public Vector2f findFreeRandomSpotNotIn(Entity entity, int x, int y, int width, int height, Rectangle notIn);
    
    /**
     * @return the aiSystem
     */
    public abstract AISystem getAISystem();

    /**
     * @return the config
     */
    public abstract SeventhConfig getConfig();

    /**
     * @return the random
     */
    public abstract Random getRandom();

    /**
     * @return the dispatcher
     */
    public abstract EventDispatcher getDispatcher();
    
    /**
     * @return the game {@link Timers}
     */
    public abstract Timers getGameTimers();

    /**
     * @return the lastFramesSoundEvents
     */
    public abstract SoundEventPool getLastFramesSoundEvents();

    /**
     * @return the soundEvents
     */
    public abstract SoundEventPool getSoundEvents();

    /**
     * @param playerId
     * @return the player, or null if not found
     */
    public abstract PlayerInfo getPlayerById(int playerId);

    /**
     * @return the players
     */
    public abstract PlayerInfos getPlayerInfos();
    
    /**
     * @return the gameType
     */
    public abstract GameType getGameType();

    /**
     * @return the map
     */
    public abstract Map getMap();

    /**
     * @return the entities
     */
    public abstract Entity[] getEntities();

    /**
     * @return the playerEntities
     */
    public abstract PlayerEntity[] getPlayerEntities();

    /**
     * @return the graph
     */
    public abstract MapGraph<Void> getGraph();

    /**
     * @return the bomb targets
     */
    public abstract List<BombTarget> getBombTargets();

    /**
     * @return the vehicles
     */
    public abstract List<Vehicle> getVehicles();
    
    /**
     * @return the doors
     */
    public abstract List<Door> getDoors();
    
    /**
     * @return the flags
     */
    public abstract List<Flag> getFlags();
    
    /**
     * Gets a {@link BombTarget} if its in arms length from the {@link PlayerEntity}
     * @param entity
     * @return the {@link BombTarget} that is touching the {@link PlayerEntity}, null otherwise
     */
    public abstract BombTarget getArmsReachBombTarget(PlayerEntity entity);

    /**
     * Determines if the supplied Entity is close enough to a {@link Vehicle}
     * to operate it (and that the {@link Vehicle} can be driven).
     * @param operator
     * @return the {@link Vehicle} to be operated on
     */
    public abstract Vehicle getArmsReachOperableVehicle(Entity operator);
    
    /**
     * Determines if the supplied entity touches another
     * entity.  If the {@link Entity#onTouch} listener
     * is implemented, it will invoke it.
     * 
     * @param ent
     * @return true if it does.
     */
    public abstract boolean doesTouchOthers(Entity ent);
    public abstract boolean doesTouchOthers(Entity ent, boolean invokeTouch);

    public boolean doesTouchEntity(Rectangle bounds);
    
    /**
     * Determines if the supplied entity touches another
     * entity.  If the {@link Entity#onTouch} listener
     * is implemented, it will invoke it.
     * 
     * @param ent
     * @return true if it does.
     */
    public abstract boolean doesTouchPlayers(Entity ent);


    /**
     * Determines if the supplied entity touches a
     * {@link Vehicle}.  If the {@link Entity#onTouch} listener
     * is implemented, it will invoke it.
     * 
     * @param ent
     * @return true if it does.
     */
    public abstract boolean doesTouchVehicles(Entity ent);

    
    /**
     * Determines if the supplied entity touches a
     * {@link Door}.  If the {@link Entity#onTouch} listener
     * is implemented, it will invoke it.
     * 
     * @param ent
     * @return true if it does.
     */
    public boolean doesTouchDoors(Entity ent);
    
    /**
     * Determines if the supplied entity touches another
     * entity.  If the {@link Entity#onTouch} listener
     * is implemented, it will invoke it.
     * 
     * @param ent
     * @return true if it does.
     */
    public abstract boolean doesTouchPlayers(Entity ent, Vector2f origin, Vector2f dir);

    
    /**
     * @param vehicle
     * @return true if the supplied {@link Vehicle} touches a {@link PlayerEntity}
     */
    public abstract boolean doesVehicleTouchPlayers(Vehicle vehicle);
    
    /**
     * Determines if the supplied entity is reachable given the origin and direction.
     * 
     * @param other
     * @param origin
     * @param dir
     * @return true if reachable (i.e., in sight or projectile can pierce)
     */
    public abstract boolean isEntityReachable(Entity other, Vector2f origin, Vector2f dir);

}
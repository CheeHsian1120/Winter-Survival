package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.teleportable.DoorStore;
import game.grounds.teleportable.TeleDoor;

import java.util.List;
import java.util.Random;

/**
 * <h1>DimensionalGround class</h1>
 * <p>
 * The {@code DimensionalGround} represents a special type of {@link Ground} that serves as
 * a temporal gateway to alternate dimensions or store realms. After a short transformation
 * period, it materializes into a {@link DoorStore} that connects the current map to a store
 * dimension, while also spawning a corresponding {@link TeleDoor} within the destination
 * store to return players to the overworld.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 */
public class DimensionalGround extends Ground {
    /**
     * Random number generator for choosing door types.
     */
    private static final Random RAND = new Random();

    /**
     * Indicate the dimensional ground transform time.
     */
    private int duration;

    /**
     * Indicate time to end.
     */
    private static final int END = 0;

    /**
     * Default number of turns before the transformation occurs.
     */
    private static final int TRANSFORM_TIME = 3;

    /**
     * List of possible DoorSore templates that can be spawned.
     */
    private final List<DoorStore> DOOR_TYPE;

    /**
     * The source location from which this ground originated.
     */
    private Location sourceLocation;

    /**
     * Constructor of the DimensionalGround class.
     *
     * @param DOOR_TYPE the list of possible DoorSore templates that may replace this ground
     */
    public DimensionalGround(List<DoorStore> DOOR_TYPE) {
        super(GroundInfo.DIMENSIONAL_GROUND.getDISPLAY_CHAR(), GroundInfo.DIRT.getNAME());
        this.DOOR_TYPE = DOOR_TYPE;
        this.duration = TRANSFORM_TIME;
    }

    /**
     * Sets the source location for this dimensional ground.
     *
     * @param sourceLocation the location from which this ground was created
     */
    public void setSourceLocation(Location sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    /**
     * Advances the passage of time for this ground tile.
     *
     * @param location The location of the DimensionalGround
     */
    @Override
    public void tick(Location location) {
        if (duration == END) {
            // Pick a random DoorStore type (store variant)
            DoorStore template = DOOR_TYPE.get(RAND.nextInt(DOOR_TYPE.size()));

            // Create a fresh DoorStore instance (entrance)
            DoorStore entrance = new DoorStore(template.getDESTINATION());

            // Replace this dimensional ground with the new door entrance
            location.setGround(entrance);

            // Create an exit door inside the store
            Location storeLocation = template.getDESTINATION();
            Location randomReturn = RandomLocation.randomChooseLocation(sourceLocation.map());

            // Create TeleDoor (exit from store to random original map location)
            TeleDoor exitDoor = new TeleDoor(List.of(randomReturn), storeLocation);
            storeLocation.setGround(exitDoor);
        }

        duration--;
    }

}

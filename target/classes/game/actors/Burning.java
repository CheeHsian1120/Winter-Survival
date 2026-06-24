package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.items.currency.WalletFunction;
import game.mysteriostore.Merchandise;


import java.util.List;

/**
 * <h1>Seller</h1>
 * <p>
 * Abstract base class for stationary NPCs that offer merchandise to visiting actors.
 * Each item can be sold at a different price and the seller ensures that the buyer
 * is charged in diamonds.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 */
public abstract class Seller extends Actor {

    /**
     * The list of merchandise available for sale by this seller.
     */
    private final List<Merchandise> catalogue;

    private static final int HITPOINTS = 1;

    /**
     * Constructs a seller with the provided catalogue.
     *
     * @param name      seller name
     * @param display   display character
     * @param catalogue list of merchandise offered
     */
    protected Seller(String name, char display, List<Merchandise> catalogue) {
        super(name, display, HITPOINTS);
        this.catalogue = List.copyOf(catalogue);
    }

    /**
     * Determines the action that this seller performs during its turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     *                   interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a DoNothingAction since sellers are passive entities
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Determines which actions another actor can perform when adjacent to this seller.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList containing the available BuyAction(s) if the actor has a wallet, or
     * an empty list otherwise
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        List<WalletFunction> wallets = otherActor.getItemInventoryAs(WalletFunction.class);
        if (wallets.isEmpty()) {
            return actions;
        }

        for (Merchandise merchandise : catalogue) {
            actions.add(new BuyAction(this, merchandise));
        }

        return actions;
    }
}


package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Seller;
import game.items.currency.WalletFunction;
import game.mysteriostore.Merchandise;
import game.mysteriostore.Price;
import game.items.equipments.armors.Wearable;
import game.items.equipments.armors.Wearing;

import java.util.List;
import java.util.Optional;

/**
 * <h1>Buy Action</h1>
 * <p>
 * Enables an actor to purchase merchandise from a seller. The action ensures
 * that the buyer pays the required diamonds and receives the newly created
 * item.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 */
public class BuyAction extends Action {

    /**
     * The seller offers the merchandise.
     */
    private final Seller seller;

    /**
     * The merchandise to be purchased.
     */
    private final Merchandise merchandise;

    /**
     * Constructs a buy action targeting a specific seller and merchandise.
     *
     * @param seller      the seller offering the merchandise
     * @param merchandise the merchandise to be purchased
     */
    public BuyAction(Seller seller, Merchandise merchandise) {
        this.seller = seller;
        this.merchandise = merchandise;
    }

    /**
     * This method is used to perform buy action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return the string details after this execution of action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<WalletFunction> wallets = actor.getItemInventoryAs(WalletFunction.class);
        if (wallets.isEmpty()) {
            return actor + " needs a wallet to buy " + merchandise.getName() + ".";
        }

        int getFirst = 0;
        WalletFunction wallet = wallets.get(getFirst);
        Price price = merchandise.getPrice();

        if (!price.isAffordable(wallet)) {
            return actor + " cannot afford " + merchandise.getName() + ". Cost: " + price.describe();
        }

        Item item = merchandise.createItem();
        String paymentDescription = price.charge(actor, wallet);
        actor.addItemToInventory(item);
        Optional<String> autoWearMessage = autoWearIfApplicable(actor, item);

        String purchaseSummary = actor + " buys " + item + " from " + seller + " for "
                + price.describe() + ".\n" + paymentDescription;

        return autoWearMessage.map(message -> purchaseSummary + "\n" + message)
                .orElse(purchaseSummary);
    }

    /**
     * Describe the buy action will be performed in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + merchandise.getName() + " for " + merchandise.getPrice().describe();
    }

    /**
     * Optional method to see if it can directly wear armor onto actors.
     *
     * @param actor the actor to wear armor
     * @param item  an item to check if it supported to wearable interface
     * @return an Optional describing the given non-null value, else empty
     */
    private Optional<String> autoWearIfApplicable(Actor actor, Item item) {
        // The specification requires that any armour bought in a Mysterio store is
        // equipped immediately. Performing that logic here keeps the purchasing
        // workflow self-contained: BuyAction already handles payment and inventory
        // transfer, so it is the one place where we are guaranteed to have access to
        // the freshly created item, the buyer, and the seller.
        Optional<Wearable> wearable = item.asCapability(Wearable.class);
        if (wearable.isEmpty()) {
            return Optional.empty();
        }

        List<Wearing> armorHolders = actor.getItemInventoryAs(Wearing.class);
        if (armorHolders.isEmpty()) {
            return Optional.empty();
        }

        int firstHolder = 0;
        Wearing armorHolder = armorHolders.get(firstHolder);
        String message = wearable.get().wornBy(actor, armorHolder);
        actor.removeItemFromInventory(item);
        return Optional.of(message);
    }
}

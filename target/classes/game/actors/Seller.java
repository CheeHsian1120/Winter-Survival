package game.actors;

import edu.monash.fit2099.engine.items.Item;
import game.actors.statuses.StatusType;
import game.items.equipments.weapons.Axe;
import game.items.equipments.weapons.Bow;
import game.items.equipments.weapons.Torch;
import game.items.equipments.weapons.WeaponType;
import game.mysteriostore.Merchandise;
import game.mysteriostore.RandomPriceApi;

import java.util.List;
import java.util.function.Supplier;

/**
 * <h1>CuriositySeller class</h1>
 * <p>
 * The {@code CuriositySeller} represents a {@link Seller} NPC who specializing in rare and
 * unconventional weaponry. This seller's stock features unique armaments imbued with
 * elemental and status-based effects.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 */
public final class CuriositySeller extends Seller {

    /**
     * Constructs a new CuriositySeller named "Myst" with a catalogue of weapon-based
     * Merchandise.
     *
     * @param priceApi the RandomPriceApi used to generate randomized prices for each item
     */
    public CuriositySeller(RandomPriceApi priceApi) {
        super(
                "Myst",
                'M',
                List.of(
                        new Merchandise(
                                "Battle Axe",
                                new Supplier<Item>() {
                                    @Override
                                    public Item get() {
                                        return new Axe(WeaponType.AXE, StatusType.BLEEDING);
                                    }
                                },
                                priceApi.randomPrice(
                                        Amount.$2.getVALUE(),
                                        Amount.$3.getVALUE(),
                                        Amount.$1.getVALUE(),
                                        Amount.$2.getVALUE(),
                                        Amount.$0.getVALUE(),
                                        Amount.$0.getVALUE())
                        ),
                        new Merchandise(
                                "Hunting Bow",
                                new Supplier<Item>() {
                                    @Override
                                    public Item get() {
                                        return new Bow(WeaponType.BOW, null);
                                    }
                                },
                                priceApi.randomPrice(
                                        Amount.$0.getVALUE(),
                                        Amount.$1.getVALUE(),
                                        Amount.$2.getVALUE(),
                                        Amount.$3.getVALUE(),
                                        Amount.$0.getVALUE(),
                                        Amount.$0.getVALUE())
                        ),
                        new Merchandise(
                                "Blazing Torch",
                                new Supplier<Item>() {
                                    @Override
                                    public Item get() {
                                        return new Torch(WeaponType.TORCH, StatusType.BURNING);
                                    }
                                },
                                priceApi.randomPrice(
                                        Amount.$1.getVALUE(),
                                        Amount.$2.getVALUE(),
                                        Amount.$1.getVALUE(),
                                        Amount.$2.getVALUE(),
                                        Amount.$0.getVALUE(),
                                        Amount.$0.getVALUE())
                        )
                )
        );
    }
}

package game.actors;


import game.items.equipments.armors.DiamondArmor;
import game.items.equipments.armors.IronArmor;
import game.items.equipments.armors.LeatherArmor;
import game.mysteriostore.Merchandise;
import game.mysteriostore.RandomPriceApi;

import java.util.List;

/**
 * <h1>ArmourySeller class</h1>
 * <p>
 *     The {@code ArmourySeller} represents a {@link Seller} NPC who trades in defensive
 *     equipment such as armor. This seller provides protection-oriented merchandise designed to
 *     enhance the player's survivability in combat.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 */
public final class ArmourySeller extends Seller {

    /**
     * Constructs a new ArmourySeller named "Aegis" with a predefined set of armour Merchandise
     * items.
     *
     * @param priceApi the RandomPriceApi used to generate randomized prices for each item
     */
    public ArmourySeller(RandomPriceApi priceApi) {
        super(
                "Aegis",
                'A',
                List.of(
                        new Merchandise(
                                "Leather Armour",
                                LeatherArmor::new,
                                priceApi.randomPrice(Amount.$3.getVALUE(), Amount.$5.getVALUE(),
                                        Amount.$1.getVALUE(), Amount.$1.getVALUE(),
                                        Amount.$1.getVALUE(), Amount.$1.getVALUE())
                        ),
                        new Merchandise(
                                "Iron Armour",
                                IronArmor::new,
                                priceApi.randomPrice(Amount.$2.getVALUE(), Amount.$3.getVALUE(),
                                        Amount.$2.getVALUE(), Amount.$3.getVALUE(), Amount.$0.getVALUE(),
                                        Amount.$1.getVALUE())
                        ),
                        new Merchandise(
                                "Diamond Armour",
                                DiamondArmor::new,
                                priceApi.randomPrice(Amount.$0.getVALUE(), Amount.$1.getVALUE(), Amount.$3.getVALUE(),
                                        Amount.$4.getVALUE(),
                                        Amount.$1.getVALUE(), Amount.$2.getVALUE())
                        )
                )
        );
    }
}

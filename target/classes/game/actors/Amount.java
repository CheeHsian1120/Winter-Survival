package game.actors;


import game.items.fruits.Apple;
import game.items.fruits.Hazelnut;
import game.items.fruits.YewBerry;
import game.mysteriostore.Merchandise;
import game.mysteriostore.RandomPriceApi;

import java.util.List;

/**
 * <h1>AlchemySeller class</h1>
 * <p>
 * The {@code AlchemySeller} represents a {@link Seller} NPC who offers alchemy-themed
 * merchandise based on fruit ingredients.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 */
public final class AlchemySeller extends Seller {

    /**
     * Constructs a new AlchemySeller named "Aurora" with a pre-defined list of fruit-based
     * Merchandise.
     *
     * @param priceApi the RandomPriceApi used to generate randomized prices for each item
     */
    public AlchemySeller(RandomPriceApi priceApi) {
        super(
                "Aurora",
                'α',
                List.of(
                        new Merchandise(
                                "Apple",
                                Apple::new,
                                priceApi.randomPrice(Amount.$1.getVALUE(), Amount.$2.getVALUE(),
                                        Amount.$0.getVALUE(), Amount.$0.getVALUE(), Amount.$0.getVALUE(),
                                        Amount.$0.getVALUE())
                        ),
                        new Merchandise(
                                "Hazelnut",
                                Hazelnut::new,
                                priceApi.randomPrice(Amount.$2.getVALUE(),
                                        Amount.$3.getVALUE(), Amount.$0.getVALUE(),
                                        Amount.$1.getVALUE(), Amount.$0.getVALUE(), Amount.$0.getVALUE())
                        ),
                        new Merchandise(
                                "Yew Berry",
                                YewBerry::new,
                                priceApi.randomPrice(Amount.$0.getVALUE(), Amount.$0.getVALUE(),
                                        Amount.$1.getVALUE(), Amount.$2.getVALUE(), Amount.$0.getVALUE(), Amount.$0.getVALUE()))
                )
        );
    }
}
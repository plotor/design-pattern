package com.javapatterns.flyweight.coffeeshop;

public class FlavorFactorySingleton {
    /**
     * @label Creates
     */
    private static FlavorFactorySingleton myself = new FlavorFactorySingleton();
    /**
     * @label Flyweight
     * @link aggregation
     * @supplierCardinality 0..
     **/
    private Order[] flavors = new Flavor[10];
    private int ordersMade = 0;
    private int totalFlavors = 0;

    private FlavorFactorySingleton() {
    }

    public static FlavorFactorySingleton getInstance() {
        return myself;
    }

    public Order getOrder(String flavorToGet) {
        if (ordersMade > 0) {
            for (int i = 0; i < ordersMade; i++) {
                if (flavorToGet.equals((flavors[i]).getFlavor())) {
                    return flavors[i];
                }
            }
        }
        flavors[ordersMade] = new Flavor(flavorToGet);
        totalFlavors++;
        return flavors[ordersMade++];
    }

    public int getTotalFlavorsMade() {
        return totalFlavors;
    }
}


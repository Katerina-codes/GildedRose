package com.gildedrose;

import java.util.Objects;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String CONJURED = "Conjured";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (Objects.equals(item.name, SULFURAS)) {

            } else if (Objects.equals(item.name, CONJURED)) {
                item.quality -= 2;
            } else {
                if (Objects.equals(item.name, BACKSTAGE_PASSES)) {
                    getItemQualityForBackStagePasses(item);
                } else if (Objects.equals(item.name, AGED_BRIE)) {
                    getItemQualityForAgedBrie(item);
                } else {
                    getItemQualityForOtherItem(item);
                }
                item.sellIn = item.sellIn - 1;
            }

            if (daysLeftToSellItemIsLessThan(item, 0)) {
                if (Objects.equals(item.name, AGED_BRIE)) {
                    getItemQualityForAgedBrie(item);
                } else {
                    if (Objects.equals(item.name, BACKSTAGE_PASSES)) {
                        item.quality = 0;
                    } else {
                        getItemQualityForOtherItem(item);
                    }
                }
            }
        }
    }

    private boolean daysLeftToSellItemIsLessThan(Item item, int days) {
        return item.sellIn < days;
    }

    private void getItemQualityForOtherItem(Item item) {
        if (item.quality > 0) {
            subtractOneFromItemQuality(item);
        }
    }

    private void getItemQualityForAgedBrie(Item item) {
        if (itemQualityIsLessThanFifty(item)) {
            addOneToItemQuality(item);
        }
    }

    private void getItemQualityForBackStagePasses(Item item) {
        if (itemQualityIsLessThanFifty(item)) {
            addOneToItemQuality(item);

            if (daysLeftToSellItemIsLessThan(item, 11) && itemQualityIsLessThanFifty(item)) {
                addOneToItemQuality(item);
            }

            if (daysLeftToSellItemIsLessThan(item, 6) && itemQualityIsLessThanFifty(item)) {
                addOneToItemQuality(item);
            }
        }
    }

    private boolean itemQualityIsLessThanFifty(Item item) {
        return item.quality < 50;
    }

    private void subtractOneFromItemQuality(Item item) {
        item.quality = item.quality - 1;
    }

    private void addOneToItemQuality(Item item) {
        item.quality = item.quality + 1;
    }
}

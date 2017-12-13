package com.gildedrose;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals(SULFURAS)) {

            } else if (item.name.equals("Conjured")) {
                item.quality -= 2;
            } else {
                if (item.name.equals(BACKSTAGE_PASSES)) {
                    getItemQualityForBackStagePasses(item);
                } else if (item.name.equals(AGED_BRIE)) {
                    getItemQualityForAgedBrie(item);
                } else {
                    if (item.quality > 0) {
                        subtractOneFromItemQuality(item);
                    }
                }

                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.name.equals(AGED_BRIE)) {
                    if (item.quality < 50) {
                        addOneToItemQuality(item);
                    }
                } else {
                    if (item.name.equals(BACKSTAGE_PASSES)) {
                        item.quality = 0;
                    } else {
                        if (item.quality > 0) {
                            subtractOneFromItemQuality(item);
                        }
                    }
                }
            }
        }
    }

    private void getItemQualityForAgedBrie(Item item) {
        if (item.quality < 50) {
            addOneToItemQuality(item);
        }
    }

    private void getItemQualityForBackStagePasses(Item item) {
        if (item.quality < 50) {
            addOneToItemQuality(item);

            if (item.sellIn < 11 && item.quality < 50) {
                addOneToItemQuality(item);
            }

            if (item.sellIn < 6 && item.quality < 50) {
                addOneToItemQuality(item);
            }
        }
    }

    private void subtractOneFromItemQuality(Item item) {
        item.quality = item.quality - 1;
    }

    private void addOneToItemQuality(Item item) {
        item.quality = item.quality + 1;
    }
}

package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
}

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!itemTypeIs(AGED_BRIE, items[i])
                    && !itemTypeIs(BACKSTAGE_PASSES, items[i])) {
                if (isQualityGreaterThanZero(items[i])) {
                    if (!itemTypeIs(SULFURAS, items[i])) {
                        qualityMinusOne(items[i]);
                    }
                }
            } else {
                if (qualityIsLessThanFifty(items[i])) {
                    qualityPlusOne(items[i]);

                    if (itemTypeIs(BACKSTAGE_PASSES, items[i])) {
                        if (isSellByDateIsLessThan(11, items[i])) {
                            if (qualityIsLessThanFifty(items[i])) {
                                qualityPlusOne(items[i]);
                            }
                        }

                        if (isSellByDateIsLessThan(6, items[i])) {
                            if (qualityIsLessThanFifty(items[i])) {
                                qualityPlusOne(items[i]);
                            }
                        }
                    }
                }
            }

            if (!itemTypeIs(SULFURAS, items[i])) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (isSellByDateIsLessThan(0, items[i])) {
                if (!itemTypeIs(AGED_BRIE, items[i])) {
                    if (!itemTypeIs(BACKSTAGE_PASSES, items[i])) {
                        if (isQualityGreaterThanZero(items[i])) {
                            if (!itemTypeIs(SULFURAS, items[i])) {
                                qualityMinusOne(items[i]);
                            }
                        }
                    } else {
                        qualityEqualsZero(items[i]);
                    }
                } else {
                    if (qualityIsLessThanFifty(items[i])) {
                        qualityPlusOne(items[i]);
                    }
                }
            }
        }
    }

    private boolean itemTypeIs(String itemType, Item item) {
        return item.name.equals(itemType);
    }

    private boolean isSellByDateIsLessThan(int numberOfDays, Item item) {
        return item.sellIn < numberOfDays;
    }

    private void qualityEqualsZero(Item item) {
        item.quality = item.quality - item.quality;
    }

    private void qualityMinusOne(Item item) {
        item.quality = item.quality - 1;
    }

    private void qualityPlusOne(Item item) {
        item.quality = item.quality + 1;
    }

    private boolean qualityIsLessThanFifty(Item item) {
        return item.quality < 50;
    }

    private boolean isQualityGreaterThanZero(Item item) {
        return item.quality > 0;
    }
}
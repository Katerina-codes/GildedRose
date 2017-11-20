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
            if (!items[i].name.equals(AGED_BRIE)
                    && !items[i].name.equals(BACKSTAGE_PASSES)) {
                if (isQualityGreaterThanZero(items[i])) {
                    if (!items[i].name.equals(SULFURAS)) {
                        qualityMinusOne(items[i]);
                    }
                }
            } else {
                if (qualityIsLessThanFifty(items[i])) {
                    qualityPlusOne(items[i]);

                    if (items[i].name.equals(BACKSTAGE_PASSES)) {
                        if (items[i].sellIn < 11) {
                            if (qualityIsLessThanFifty(items[i])) {
                                qualityPlusOne(items[i]);
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (qualityIsLessThanFifty(items[i])) {
                                qualityPlusOne(items[i]);
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals(SULFURAS)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals(AGED_BRIE)) {
                    if (!items[i].name.equals(BACKSTAGE_PASSES)) {
                        if (isQualityGreaterThanZero(items[i])) {
                            if (!items[i].name.equals(SULFURAS)) {
                                qualityMinusOne(items[i]);
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (qualityIsLessThanFifty(items[i])) {
                        qualityPlusOne(items[i]);
                    }
                }
            }
        }
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
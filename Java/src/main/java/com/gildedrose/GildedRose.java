package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {

            } else {
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality < 50) {
                        addOneToItemQuality(item);

                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                addOneToItemQuality(item);
                            }

                            if (item.sellIn < 6) {
                                if (item.quality < 50) {
                                    addOneToItemQuality(item);
                                }
                            }
                        }
                    }
                } else if (item.name.equals("Aged Brie")) {
                    if (item.quality < 50) {
                        addOneToItemQuality(item);
                    }
                } else {
                    if (item.quality > 0) {
                        subtractOneFromItemQuality(item);
                    }
                }

                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.name.equals("Aged Brie")) {
                    if (item.quality < 50) {
                        addOneToItemQuality(item);
                    }
                } else {
                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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

    private void subtractOneFromItemQuality(Item item) {
        item.quality = item.quality - 1;
    }

    private void addOneToItemQuality(Item item) {
        item.quality = item.quality + 1;
    }
}

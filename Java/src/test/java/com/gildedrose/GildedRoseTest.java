package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void afterEachDaySellInDateAndQualityDecreaseByOne() {
        Item[] items = new Item[] { new Item("cake", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void afterSellInDatePassesQualityDecreasesTwiceAsFast() {
        Item[] items = new Item[] { new Item("cake", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void qualityOfAnItemIsNeverNegative() {
        Item[] items = new Item[] { new Item("cake", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void qualityOfAgedBrieIncreasesAsItGetsOlder() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
}

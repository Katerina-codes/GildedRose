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

    @Test
    public void qualityOfAgedBrieIsNeverOverFifty() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void qualityOfAnBackstagePassesIsNeverOverFifty() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
    }
}

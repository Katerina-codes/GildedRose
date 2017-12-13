package com.gildedrose;

import static com.gildedrose.GildedRose.AGED_BRIE;
import static com.gildedrose.GildedRose.BACKSTAGE_PASSES;
import static com.gildedrose.GildedRose.SULFURAS;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void afterEachDaySellInDateAndQualityDecreaseByOne() {
        GildedRose app = itemSetUp("cake", 1, 1);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void afterSellInDatePassesQualityDecreasesTwiceAsFast() {
        GildedRose app = itemSetUp("cake", 0, 2);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void qualityOfAnItemIsNeverNegative() {
        GildedRose app = itemSetUp("cake", 1, 0);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void qualityOfAgedBrieIncreasesAsItGetsOlder() {
        GildedRose app = itemSetUp(AGED_BRIE, 2, 1);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    public void qualityOfAgedBrieIsNeverOverFifty() {
        GildedRose app = itemSetUp(AGED_BRIE, 1, 50);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void qualityOfAnBackstagePassesIsNeverOverFifty() {
        GildedRose app = itemSetUp(BACKSTAGE_PASSES, 11, 50);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    public void sulfurasNeverDecreasesInQualityOrHasToBeSold() {
        GildedRose app = itemSetUp(SULFURAS, 0, 10);
        app.updateQuality();
        assertEquals(10, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesQualityIncreasesByTwoTenDaysBeforeEvent() {
        GildedRose app = itemSetUp(BACKSTAGE_PASSES, 10, 1);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesQualityIncreasesByThreeFiveOrLessDaysBeforeEvent() {
        GildedRose app = itemSetUp(BACKSTAGE_PASSES, 5, 4);
        app.updateQuality();
        assertEquals(7, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesQualityDropsToZeroAfterEvent() {
        GildedRose app = itemSetUp(BACKSTAGE_PASSES, 0, 10);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    private GildedRose itemSetUp(String cake, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(cake, sellIn, quality) };
        return new GildedRose(items);
    }
}

package com.gildedrose;

import static com.gildedrose.GildedRose.AGED_BRIE;
import static com.gildedrose.GildedRose.BACKSTAGE_PASSES;
import static com.gildedrose.GildedRose.SULFURAS;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        GildedRose app = getGildedRose("foo", 0, 0);

        app.updateQuality();

        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void forNormalProductAtEndOfDaySellinAndQualityOfItemAreLowered() {
        GildedRose app = getGildedRose("cake", 1, 1);

        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void whenSellInDateHasPassedQualityDegradesTwiceAsFast() {
        GildedRose app = getGildedRose("cake", 0, 2);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void theQualityOfAnItemIsNeverNegative() {
        GildedRose app = getGildedRose("cake", 1, 0);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void theQualityOfAnItemIsNeverNegativeEvenAfterSellByDate() {
        String name = "cake";
        int sellIn = 0;
        int quality = 0;
        GildedRose app = getGildedRose(name, sellIn, quality);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    private GildedRose getGildedRose(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        return new GildedRose(items);
    }

    @Test
    public void agedBrieIncreasesInQualityWithAge() {
        GildedRose app = getGildedRose(AGED_BRIE, 1, 0);

        app.updateQuality();

        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void agedBrieIncreasesInQualityTwiceWhenSellByDateHasPassed() {
        GildedRose app = getGildedRose(AGED_BRIE, 0, 0);

        app.updateQuality();

        assertEquals(2, app.items[0].quality);
    }

    @Test
    public void qualityOfAnItemIsNeverMoreThan50() {
        GildedRose app = getGildedRose(AGED_BRIE, 1, 50);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void sulfurasNeverHasToBeSoldAndNeverDecreasesInQuality() {
        GildedRose app = getGildedRose(SULFURAS, 0, 1);

        app.updateQuality();

        assertEquals(1, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesIncreasesByTwoIfSellInIsBetweenTenAndSixDays() {
        GildedRose app = getGildedRose(BACKSTAGE_PASSES, 10, 1);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void backstagePassesIncreasesByThreeIfSellInIsBetweenFiveAndOneDay() {
        GildedRose app = getGildedRose(BACKSTAGE_PASSES, 5, 1);

        app.updateQuality();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void qualityDropsToZeroAfterConcert() {
        GildedRose app = getGildedRose(BACKSTAGE_PASSES, 0, 0);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }
}

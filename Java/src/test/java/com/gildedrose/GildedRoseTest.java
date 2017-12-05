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

}

package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("regular Items should decrease by 1 in quality and sellin if not expired")
    public void regularItemNotExpired() {
        // GIVEN
        var items = new Item[] { new Item("Regular Item", 10, 5) };

        var app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Regular Item", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }
    @Test
    @DisplayName("ordinary expired item degrades twice as fast")
    public void ordinaryExpiredItem(){
        // GIVEN
        var items = new Item[] { new Item("Expired Item", 0, 4) };

        var app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Expired Item", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    @DisplayName("Quality of an item should always be positive")
    public void ordinaryItemQuality() {
        // GIVEN
        var items = new Item[] {
                new Item("Ordinary Item", 0, 1),
                new Item("Regular Item", 10, 0)
        };

        var app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Ordinary Item", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals("Regular Item", app.items[1].name);
        assertEquals(9, app.items[1].sellIn);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    @DisplayName("Aged Brie increses in quality as it gets older")
    public void agedBrieQuality() {
        // GIVEN
        var items = new Item[] {
                new Item("Aged Brie", 10, 1),
                new Item("Aged Brie", 0, 1),
                new Item("Aged Brie", -10, 1)
        };

        var app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        var brie2 = app.items[1];
        assertEquals("Aged Brie", brie2.name);
        assertEquals(-1, brie2.sellIn);
        assertEquals(3, brie2.quality);

        var brie3 = app.items[2];
        assertEquals("Aged Brie", brie3.name);
        assertEquals(-11, brie3.sellIn);
        assertEquals(3, brie3.quality);
    }

    @Test
    @DisplayName("Aged Brie does not increase after 50")
    public void agedBrieQualityNotOver50() {
        // GIVEN
        var items = new Item[] {
                new Item("Aged Brie", 10, 50),
                new Item("Aged Brie", 0, 49)
        };

        var app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        var brie2 = app.items[1];
        assertEquals("Aged Brie", brie2.name);
        assertEquals(-1, brie2.sellIn);
        assertEquals(50, brie2.quality);
    }

    @Test
    @DisplayName("Sulfuras quality doesnt not change")
    public void sulfuras(){
        // GIVEN
        var items = new Item[] {
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        };

        var app = new GildedRose(items);
        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[1].name);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(80, app.items[1].quality);
    }


    @Test
    @DisplayName("backstage pass increase until sellin is 0")
    public void backStagePassIncrease() {
        // GIVEN
        var items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 28),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 42)
        };

        var app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(14, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);

        var backstagePass1 = app.items[1];
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstagePass1.name);
        assertEquals(9, backstagePass1.sellIn);
        assertEquals(30, backstagePass1.quality);
        var backstagePass2 = app.items[2];
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstagePass2.name);
        assertEquals(4, backstagePass2.sellIn);
        assertEquals(33, backstagePass2.quality);
        var backstagePass3 = app.items[3];
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstagePass3.name);
        assertEquals(-1, backstagePass3.sellIn);
        assertEquals(0, backstagePass3.quality);
    }

}

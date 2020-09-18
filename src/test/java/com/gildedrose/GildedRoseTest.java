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
}

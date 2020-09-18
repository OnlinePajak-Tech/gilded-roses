package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("regular Items should decrease by 1 in quality and sellin if not expired")
    void regularItemNotExpired() {
        // GIVEN
        Item[] items = new Item[] { new Item("Regular Item", 10, 5) };

        GildedRose app = new GildedRose(items);

        // WHEN
        app.updateQuality();

        // THEN
        assertEquals("Regular Item", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

}

package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private void updateOrdinaryItem(Item item) {
        if (isExpired(item))
            item.quality -= 2;
        else
            item.quality -= 1;
        item.sellIn -= 1;
    }

    private void updateNonOrdinaryItem(Item item) {

    }

    private boolean isExpired(Item item) {
        return item.sellIn <= 0;
    }

    private boolean isOrdinaryItem(Item item) {
        switch (item.name) {
            case "Aged Brie":
            case "Sulfuras, Hand of Ragnaros":
            case "Backstage passes to a TAFKAL80ETC concert":
                return false;
            default:
                return true;
        }
    }

    public void updateQuality() {
        for (var item : items) {
//            if (isOrdinaryItem(item))
//                updateOrdinaryItem(item);
//            else
                processItem(item);
        }
    }

    private void processItem(Item item) {
        if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals("Aged Brie")) {
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}

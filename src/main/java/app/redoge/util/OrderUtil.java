package app.redoge.util;

import app.redoge.OrderBook;

public class OrderUtil {
    public void buyOrder(OrderBook orderBook, long size){
        var asks = orderBook.getAsk();
        var minPriceBySize = asks.keySet().stream().filter(price->asks.get(price) == size).min(Long::compareTo).orElse(0L);
        asks.put(minPriceBySize, 0L);
    }

    public void sellOrder(OrderBook orderBook, long size){
        var bids = orderBook.getBid();
        var minPriceBySize = bids.keySet().stream().filter(price->bids.get(price) == size).max(Long::compareTo).orElse(0L);
        bids.put(minPriceBySize, 0L);
    }
}

package app.redoge.util;

import app.redoge.OrderBook;

public class UpdateUtil {
    public void setBidSizeByPrice(OrderBook orderBook, long price, long size){
        var bid = orderBook.getBid();
        bid.put(price, size);
    }
    public void setAskSizeByPrice(OrderBook orderBook, long price, long size){
        var ask = orderBook.getAsk();
        ask.put(price, size);
    }
}

package app.redoge.util;

import app.redoge.OrderBook;

import static java.lang.String.format;

public class QueryUtil {
    public String getBestBidPriceAndSizeString(OrderBook orderBook){ //Найкраща пропозиція (найвищий рівень ціни непорожньої пропозиції)
        var bid = orderBook.getBid();
        var bestBidPrice = bid.keySet().stream().filter(t->bid.get(t)>0).max(Long::compareTo).orElse(0L);
        return format("%d,%d", bestBidPrice, bid.get(bestBidPrice));
    }
    public String getBestAskPriceAndSizeString(OrderBook orderBook){ //Найкращий запит (найнижчий рівень ціни, що не є порожнім)
        var ask = orderBook.getAsk();
        var bestAskPrice = ask.keySet().stream().filter(t->ask.get(t)>0).min(Long::compareTo).orElse(0L);
        if(bestAskPrice == 0){
            var maxPrice = ask.keySet().stream().max(Long::compareTo).orElse(0L);
            return format("%d,%d", maxPrice, ask.get(maxPrice));
        }else{
            return format("%d,%d", bestAskPrice, ask.get(bestAskPrice));
        }
    }

    public String getSizeAtSpecifiedPriceString(OrderBook orderBook, long price){ //розмір друку за вказаною ціною (bid, ask або спред).
        var sizeByBid = orderBook.getBid().getOrDefault(price, 0L);
        var sizeByAsk = orderBook.getAsk().getOrDefault(price, 0L);
        var neededSize = Math.max(sizeByAsk, sizeByBid);
        return format("%d", neededSize);
    }
}

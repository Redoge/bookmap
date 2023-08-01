package app.redoge.util;

import app.redoge.OrderBook;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class EventWorker {
    private static final String U = "u";
    private static final String Q = "q";
    private static final String O = "o";
    private static final String BID = "bid";
    private static final String ASK = "ask";
    private static final String BEST_ASK = "best_ask";
    private static final String BEST_BID = "best_bid";
    private static final String SIZE = "size";
    private static final String BUY = "buy";
    private static final String SELL = "SELL";
    private final OrderBook orderBook;
    private final UpdateUtil updateUtil;
    private final OrderUtil orderUtil;
    private final QueryUtil queryUtil;
    private final List<String> output;
    public EventWorker(OrderBook orderBook) {
        this.orderBook = orderBook;
        this.updateUtil = new UpdateUtil();
        this.orderUtil = new OrderUtil();
        this.queryUtil = new QueryUtil();
        this.output = new ArrayList<>();
    }

    public List<String> getOutput() {
        return output;
    }

    public void doByLine(String line){
        var splitLine = line.split(",");
        var workType = splitLine[0];
        switch (workType) {
            case U -> {
                var price = parseLong(splitLine[1]);
                var updateSize = parseLong(splitLine[2]);
                switch (splitLine[3]) {
                    case BID -> updateUtil.setBidSizeByPrice(orderBook, price, updateSize);
                    case ASK -> updateUtil.setAskSizeByPrice(orderBook, price, updateSize);
                    default -> {}
                }
            }
            case O -> {
                var work = splitLine[1];
                var orderSize = parseLong(splitLine[2]);
                switch (work) {
                    case BUY -> orderUtil.buyOrder(orderBook, orderSize);
                    case SELL -> orderUtil.sellOrder(orderBook, orderSize);
                    default -> {}
                }
            }
            case Q -> {
                var splitSize = splitLine.length;
                switch (splitSize){
                    case 2 -> {
                        var work = splitLine[1];
                        switch (work){
                            case BEST_BID -> output.add(queryUtil.getBestBidPriceAndSizeString(orderBook));
                            case BEST_ASK -> output.add(queryUtil.getBestAskPriceAndSizeString(orderBook));
                        }
                    }
                    case 3 -> {
                        var price = parseLong(splitLine[2]);
                        output.add(queryUtil.getSizeAtSpecifiedPriceString(orderBook, price));
                    }
                }
            }
            default -> {
            }
        }
    }
}

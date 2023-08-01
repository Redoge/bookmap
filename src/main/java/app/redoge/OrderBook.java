package app.redoge;


import java.util.HashMap;
import java.util.Map;

public class OrderBook {
    private Map<Long, Long> bid;
    private Map<Long, Long> ask;
    private Map<Long, Long> spread;

    public OrderBook() {
        spread = new HashMap<>();
        ask = new HashMap<>();
        bid = new HashMap<>();
    }

    public Map<Long, Long> getBid() {
        return bid;
    }

    public void setBid(Map<Long, Long> bid) {
        this.bid = bid;
    }

    public Map<Long, Long> getAsk() {
        return ask;
    }

    public void setAsk(Map<Long, Long> ask) {
        this.ask = ask;
    }

    public Map<Long, Long> getSpread() {
        return spread;
    }

    public void setSpread(Map<Long, Long> spread) {
        this.spread = spread;
    }
}

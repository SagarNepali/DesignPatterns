package observerPattern.observers;

import observerPattern.util.Observer;

public class SMSSender implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("SMS has been sent: "+msg);
    }
}

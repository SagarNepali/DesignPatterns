package observerPattern.observers;

import observerPattern.util.Observer;

public class Logger implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("Logger : "+msg);
    }
}

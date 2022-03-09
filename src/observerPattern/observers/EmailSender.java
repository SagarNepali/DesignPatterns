package observerPattern.observers;

import observerPattern.util.Observer;

public class EmailSender implements Observer {
    @Override
    public void update(String msg) {

        System.out.println("Email has been sent: "+msg);
    }
}

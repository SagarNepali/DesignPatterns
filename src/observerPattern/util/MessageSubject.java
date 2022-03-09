package observerPattern.util;

import java.util.ArrayList;
import java.util.List;

public class MessageSubject implements Subject{

    private List<Observer> observers;
    private String msg;

    public MessageSubject(){
        observers = new ArrayList<>();

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> {
            o.update(msg);
        });
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
}

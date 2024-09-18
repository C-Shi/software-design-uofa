// Subject.java

public interface Subject {
    public void registerObserver(Observer : observer);
    public void removeObserver(Observer : observer);
    public void notifyObservers();
}

// Channel.java

public class Channel implements Subject {
    private Observer[] observers;
    private String channelName;
    private String status;
  
    public Channel(String channelName, String status) {
        this.observers = [];
        this.channelName = channelName;
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer)
    }
  
    public void notify() {
        for (Observer observer : this.observers) {
            observer.update()
        }
    }
}

// Observer.java

public interface Observer {
	public void update(String status);
}

// Follower.java

public class Follower implements Observer {
    private String followerName;

    public void play() {
        // do somethig
    }

    public void update(String string) {
        // do somethign with string
    }
}

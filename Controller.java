import java.util.*;

public class Controller {
    private List<Events> eventList = new ArrayList<Events>();

    public void addEvent(Events c) {
        eventList.add(c);
    }

    public void removeEvent(int i) {
        eventList.remove(i);
    }

    public void run() {
        Iterator<Events> i = eventList.iterator();
        int j = 0;
        while (!eventList.isEmpty()) { // Loop until eventList is empty
            j = 0;
            while (i.hasNext()) {
                Events e = i.next();
                j++;
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    removeEvent(j-1);
                    i = eventList.iterator();
                    break;
                }
            }
            // Re-initialize the iterator for the next iteration
            i = eventList.iterator();
        }
    }

}
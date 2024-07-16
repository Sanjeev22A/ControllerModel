class GreenHouseController extends Controller {
    private boolean light = false;

    class lightOn extends Events {
        lightOn(long delayTime) {
            super(delayTime);
        }

        void action() {
            light = true;
        }

        public String toString() {
            return "Light is on";
        }
    }

    class lightOff extends Events {
        lightOff(long delayTime) {
            super(delayTime);
        }

        void action() {
            light = false;
        }

        public String toString() {
            return "Light is offf";
        }
    }

    class bulb extends Events {
        bulb(long delayTime) {
            super(delayTime);
        }

        void action() {
            addEvent(new bulb(delayTime));
        }

        public String toString() {
            return "Blink light is on";
        }
    }

    class restart extends Events {
        private Events[] eventList;

        restart(long delayTime, Events[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            action();

        }

        public void action() {
            for (int i = 0; i < eventList.length; i++) {
                eventList[i].start(); // rerun each event
                addEvent(eventList[i]);
            }
            start(); // start and add the restart event
            addEvent(this);
        }

        public String toString() {
            return "System restarted";
        }

    }

    public class terminate extends Events {
        terminate(long delayTime) {
            super(delayTime);
        }

        void action() {
            System.exit(0);
            //System.out.println("Terminated");
        }

        public String toString() {
            return "Terminating the system";
        }
    }

    public static void main(String[] args) {
        GreenHouseController greenhouse = new GreenHouseController();

        // Create some events with different delay times
        Events lightOnEvent = greenhouse.new lightOn(1000); // Turn light on after 1 second
        Events bulbEvent = greenhouse.new bulb(2000); // Blink light every 2 seconds
        Events lightOffEvent = greenhouse.new lightOff(3000); // Turn light off after 3 seconds
        Events[] eventList = { lightOnEvent, bulbEvent, lightOffEvent };

        // Add events to the greenhouse controller
        for (Events event : eventList) {
            greenhouse.addEvent(event);
        }

        // Add a restart event that restarts the provided eventList after 5 seconds
        Events restartEvent = greenhouse.new restart(5000, eventList);
        greenhouse.addEvent(restartEvent);

        // Add a termination event that terminates the system after 10 seconds
        Events terminateEvent = greenhouse.new terminate(10000);
        greenhouse.addEvent(terminateEvent);

        // Run the greenhouse controller to execute the events
        greenhouse.run();
    }
}
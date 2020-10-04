/**
 * Represents a CPU process that is allocated CPU time.
 * 
 * @author Steven Lineses
 * @version 1.0
 */

public class Process implements Comparable<Process> {
    private int priority;
    private int timeRemaining;
    private int timeNotProcessed;
    private int arrivalTime;

    public Process(int arrivalTime, int priority, int timeRemaining) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.timeRemaining = timeRemaining;
        this.timeNotProcessed = 0;
    }

    public void reduceTimeRemaining() {
        timeRemaining--;
    }

    public void resetTimeNotProcessed() {
        timeNotProcessed = 0;
    }

    public void increaseTimeNotProcessed() {
        timeNotProcessed++;
    }

    public void increasePriority() {
        priority++;
    }

    public int compareTo(Process otherProcess) {
        int result = 0;

        /**
         * Sometimes two processes will have the same priority values and
         * when that occurs, their arrival times are compared instead.
         * 
         * The process with the lower arrivalTime is greater.
         */
        if (priority > otherProcess.priority) {
            result = 1;
        } else if (priority < otherProcess.priority) {
            result = -1;
        } else {
            if (arrivalTime < otherProcess.arrivalTime) {
                result = 1;
            } else if (arrivalTime > otherProcess.arrivalTime) {
                result = -1;
            }
        }

        return result;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getTimeNotProcessed() {
        return timeNotProcessed;
    }

    public boolean finish() {
        return timeRemaining == 0;
    }
}

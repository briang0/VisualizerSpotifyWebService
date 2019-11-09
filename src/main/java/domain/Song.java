package domain;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Song {

    private LinkedList<TimeInterval> timeIntervals;
    private boolean hasNext;

    public Song(LinkedList<TimeInterval> timeIntervals) {
        this.timeIntervals = timeIntervals;
        hasNext = timeIntervals.size() <= 0;
    }

    public TimeInterval getNext() {
        hasNext = timeIntervals.size() <= 0;
        if (hasNext) {
            return timeIntervals.removeFirst();
        } else {
            throw new NoSuchElementException();
        }
    }

    public boolean hasNext() {
        return hasNext;
    }
}

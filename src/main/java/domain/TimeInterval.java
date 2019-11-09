package domain;

import java.util.LinkedList;

public class TimeInterval {

    private float start;
    private float duration;
    private float confidence;
    private float loudness_start;
    private float loudness_max_time;
    private float loudness_max;
    private LinkedList<Float> pitches;
    private LinkedList<Float> timbre;

    public TimeInterval(float start, float duration, float confidence, float loudness_start, float loudness_max_time,
                        float loudness_max, LinkedList<Float> pitches, LinkedList<Float> timbre){
        this.start = start;
        this.duration = duration;
        this.confidence = confidence;
        this.loudness_start = loudness_start;
        this.loudness_max_time = loudness_max;
        this.loudness_max_time = loudness_max_time;
        this.pitches = pitches;
        this.timbre = timbre;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public float getLoudness_start() {
        return loudness_start;
    }

    public void setLoudness_start(float loudness_start) {
        this.loudness_start = loudness_start;
    }

    public float getLoudness_max_time() {
        return loudness_max_time;
    }

    public void setLoudness_max_time(float loudness_max_time) {
        this.loudness_max_time = loudness_max_time;
    }

    public float getLoudness_max() {
        return loudness_max;
    }

    public void setLoudness_max(float loudness_max) {
        this.loudness_max = loudness_max;
    }

    public LinkedList<Float> getPitches() {
        return pitches;
    }

    public void setPitches(LinkedList<Float> pitches) {
        this.pitches = pitches;
    }

    public LinkedList<Float> getTimbre() {
        return timbre;
    }

    public void setTimbre(LinkedList<Float> timbre) {
        this.timbre = timbre;
    }

}

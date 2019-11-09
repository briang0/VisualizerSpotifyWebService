package domain;

public class Segment {

    private float start;
    private float duration;
    private float confidence;
    private float loudnessStart;
    private float loudnessMaxTime;
    private float loudnessMax;
    private float[] pitches;
    private float[] timbre;

    public Segment(float start, float duration, float confidence, float loudness_start, float loudness_max_time,
                   float loudness_max, float[] pitches, float[] timbre){
        this.start = start;
        this.duration = duration;
        this.confidence = confidence;
        this.loudnessStart = loudness_start;
        this.loudnessMaxTime = loudness_max;
        this.loudnessMaxTime = loudness_max_time;
        this.pitches = pitches;
        this.timbre = timbre;
    }

    public float getStart() {
        return start;
    }

    public float getDuration() {
        return duration;
    }

    public float getConfidence() {
        return confidence;
    }

    public float getLoudnessStart() {
        return loudnessStart;
    }

    public float getLoudnessMaxTime() {
        return loudnessMaxTime;
    }

    public float getLoudnessMax() {
        return loudnessMax;
    }

    public float[] getPitches() {
        return pitches;
    }

    public float[] getTimbre() {
        return timbre;
    }

}

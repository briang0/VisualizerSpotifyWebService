package domain;

public class Section {

    private float start;
    private float duration;
    private float confidence;
    private float loudness;
    private float tempo;
    private float tempoConfidence;
    private int key;
    private float keyConfidence;
    private int mode;
    private float modeConfidence;
    private int timeSignature;
    private float timeSignatureConfidence;

    public Section(float start, float duration, float confidence, float loudness, float tempo, float tempoConfidence,
                   int key, float keyConfidence, int mode, float modeConfidence, int timeSignature,
                   float timeSignatureConfidence){
        this.start = start;
        this.duration = duration;
        this.confidence = confidence;
        this.loudness = loudness;
        this.tempo = tempo;
        this.tempoConfidence = tempoConfidence;
        this.key = key;
        this.keyConfidence = keyConfidence;
        this.mode = mode;
        this.modeConfidence = modeConfidence;
        this.timeSignature = timeSignature;
        this.timeSignatureConfidence = timeSignatureConfidence;
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

    public float getLoudness() {
        return loudness;
    }

    public float getTempo() {
        return tempo;
    }

    public float getTempoConfidence() {
        return tempoConfidence;
    }

    public int getKey() {
        return key;
    }

    public float getKeyConfidence() {
        return keyConfidence;
    }

    public int getMode() {
        return mode;
    }

    public float getModeConfidence() {
        return modeConfidence;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    public float getTimeSignatureConfidence() {
        return timeSignatureConfidence;
    }


}

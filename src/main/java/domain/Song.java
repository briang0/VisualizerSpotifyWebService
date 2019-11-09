package domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysisSection;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysisSegment;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Song {

    private LinkedList<Segment> segments;
    private LinkedList<Section> sections;
    private boolean hasNext;

    public Song(AudioAnalysis audioAnalysis) {
        segments = new LinkedList<>();
        sections = new LinkedList<>();
        AudioAnalysisSection[] audioSections = audioAnalysis.getSections();
        AudioAnalysisSegment[] audioSegments = audioAnalysis.getSegments();
        for (AudioAnalysisSection section : audioSections) {
            float start = section.getMeasure().getStart();
            float duration = section.getMeasure().getDuration();
            float confidence = section.getMeasure().getConfidence();
            float loudness = section.getLoudness();
            float tempo = section.getTempo();
            float tempoConfidence = section.getTempoConfidence();
            int key = section.getKey();
            float keyConfidence = section.getKeyConfidence();
            int mode = 0;
            float modeConfidence = section.getModeConfidence();
            int timeSignature = section.getTimeSignature();
            float timeSignatureConfidence = section.getTimeSignatureConfidence();
            Section s = new Section(start, duration, confidence, loudness, tempo, tempoConfidence, key, keyConfidence,
                    mode, modeConfidence, timeSignature, timeSignatureConfidence);
            sections.add(s);
        }
        for (AudioAnalysisSegment segment : audioSegments) {
            float start = segment.getMeasure().getStart();
            float duration = segment.getMeasure().getDuration();
            float confidence = segment.getMeasure().getConfidence();
            float loudnessStart = segment.getLoudnessStart();
            float loudnessMaxTime = segment.getLoudnessMaxTime();
            float loudnessMax = segment.getLoudnessMax();
            float pitches[] = segment.getPitches();
            float timbre[] = segment.getTimbre();
            Segment audioSegment = new Segment(start, duration, confidence, loudnessStart, loudnessMaxTime, loudnessMax,
                    pitches, timbre);
            segments.add(audioSegment);
        }
        hasNext = segments.size() <= 0;
    }

    public Segment getNext() {
        hasNext = segments.size() <= 0;
        if (hasNext) {
            return segments.removeFirst();
        } else {
            throw new NoSuchElementException();
        }
    }

    public String getSegementsJson(){
        Gson gson = new Gson();
        String json = gson.toJson(segments);
        return json;
    }

    public String getSectionsJson(){
        Gson gson = new Gson();
        String json = gson.toJson(sections);
        return json;
    }

    public boolean hasNext() {
        return hasNext;
    }
}

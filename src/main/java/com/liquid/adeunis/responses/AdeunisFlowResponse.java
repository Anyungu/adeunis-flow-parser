package com.liquid.adeunis.responses;

import java.util.HashMap;

public class AdeunisFlowResponse {

    String frameMode;
    Float frameCount;
    HashMap<String, String> status;
    HashMap<String, Boolean> alerts;
    HashMap<String, Float> minMax;
    HashMap<String, Float> pulses;

    public HashMap<String, String> getStatus() {
        return status;
    }

    public void setStatus(HashMap<String, String> status) {
        this.status = status;
    }

    public HashMap<String, Boolean> getAlerts() {
        return alerts;
    }

    public void setAlerts(HashMap<String, Boolean> alerts) {
        this.alerts = alerts;
    }

    public HashMap<String, Float> getMinMax() {
        return minMax;
    }

    public void setMinMax(HashMap<String, Float> minMax) {
        this.minMax = minMax;
    }

    public HashMap<String, Float> getPulses() {
        return pulses;
    }

    public void setPulses(HashMap<String, Float> pulses) {
        this.pulses = pulses;
    }

    public String getFrameMode() {
        return frameMode;
    }

    public void setFrameMode(String frameMode) {
        this.frameMode = frameMode;
    }

    public Float getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(Float frameCount) {
        this.frameCount = frameCount;
    }

    
}
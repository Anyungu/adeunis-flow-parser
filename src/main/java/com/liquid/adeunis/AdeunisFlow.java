package com.liquid.adeunis;

import java.util.HashMap;

import com.liquid.adeunis.decoder.AdeunisFlowDecoder;
import com.liquid.adeunis.responses.AdeunisFlowResponse;

public class AdeunisFlow {

  public String frameMode;
  public Float frameCount;
  public HashMap<String, String> status;
  public HashMap<String, Boolean> alerts;
  public HashMap<String, Float> minMax;
  public HashMap<String, Float> pulses;

  public AdeunisFlow(String message) throws Exception {

    AdeunisFlowResponse decode = AdeunisFlowDecoder.decode(message);

    this.frameMode = decode.getFrameMode();
    this.frameCount = decode.getFrameCount();
    this.status = decode.getStatus();
    this.minMax = decode.getMinMax();
    this.alerts = decode.getAlerts();
    this.pulses = decode.getPulses();

  }

}
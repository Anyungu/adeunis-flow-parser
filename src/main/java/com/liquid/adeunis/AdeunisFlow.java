package com.liquid.adeunis;

import java.util.HashMap;

import com.liquid.adeunis.decoder.AdeunisFlowDecoder;
import com.liquid.adeunis.responses.AdeunisFlowResponse;

public class AdeunisFlow {

  String frameMode;
  Float frameCount;
  HashMap<String, String> status;
  HashMap<String, Boolean> alerts;
  HashMap<String, Float> minMax;
  HashMap<String, Float> pulses;

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
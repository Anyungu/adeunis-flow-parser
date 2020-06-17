
package com.liquid.adeunis.decoder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.liquid.adeunis.responses.AdeunisFlowResponse;


public class AdeunisFlowDecoder {

    public static AdeunisFlowResponse decode(final String message) {

        List<String> splitParts = splitParts(message, 2);

        String frameMode = splitParts.get(0);

        HashMap<String, String> status = new HashMap<>();
        HashMap<String, Boolean> alerts = new HashMap<>();
        HashMap<String, Float> minMax = new HashMap<>();
        HashMap<String, Float> pulses = new HashMap<>();

        AdeunisFlowResponse flowResponse = new AdeunisFlowResponse();

        switch (frameMode) {
            case "30":
                flowResponse.setFrameMode("keep-alive");
                //status
                // frame count
                String convertToBinaryString = convertToBinaryString(splitParts.get(1));
                List<String> splitPartsStatus = splitParts(convertToBinaryString, 3);
                Float frameCounter = convertToDecimal(splitPartsStatus.get(0));
                flowResponse.setFrameCount(frameCounter);

                // other status
                List<String> splitPartsStatus2 = splitParts(convertToBinaryString, 1);
                status.put("config", splitPartsStatus2.get(4).contentEquals("1") ? "Error" : "Good");
                status.put("HW", splitPartsStatus2.get(5).contentEquals("1") ? "Error" : "Good");
                status.put("bat", splitPartsStatus2.get(6).contentEquals("1") ? "Low" : "Good");
                status.put("config-done", splitPartsStatus2.get(7).contentEquals("1") ? "Done" : "Not");
                flowResponse.setStatus(status);

                // error
                String convertToBinaryStringError = convertToBinaryString(splitParts.get(2));
                List<String> splitPartsError = splitParts(convertToBinaryStringError, 1);
                alerts.put("over-flow-A", splitPartsError.get(7).contentEquals("1") ? true : false);
                alerts.put("over-flow-B", splitPartsError.get(6).contentEquals("1") ? true : false);
                alerts.put("tamper-A", splitPartsError.get(5).contentEquals("1") ? true : false);
                alerts.put("tamper-B", splitPartsError.get(4).contentEquals("1") ? true : false);
                alerts.put("leak-A", splitPartsError.get(3).contentEquals("1") ? true : false);
                alerts.put("leak-B", splitPartsError.get(2).contentEquals("1") ? true : false);
                flowResponse.setAlerts(alerts);

                // minmax
                Float maxFlowA = convertToDecimal(splitParts.get(3) + splitParts.get(4));
                Float maxFlowB = convertToDecimal(splitParts.get(5) + splitParts.get(6));
                Float minFlowA = convertToDecimal(splitParts.get(7) + splitParts.get(8));
                Float minFlowB = convertToDecimal(splitParts.get(9) + splitParts.get(10));
                minMax.put("maxFlowA", maxFlowA);
                minMax.put("maxFlowB", maxFlowB);
                minMax.put("minFlowA", minFlowA);
                minMax.put("minFlowB", minFlowB);
                flowResponse.setMinMax(minMax);
                break;

            case "46":
                flowResponse.setFrameMode("uplink");
                 //status
                // frame count
                String convertToBinaryString46 = convertToBinaryString(splitParts.get(1));
                List<String> splitPartsStatus46 = splitParts(convertToBinaryString46, 3);
                Float frameCounter46 = convertToDecimal(splitPartsStatus46.get(0));
                flowResponse.setFrameCount(frameCounter46);

                // other status
                List<String> splitPartsStatus246 = splitParts(convertToBinaryString46, 1);
                status.put("config", splitPartsStatus246.get(4).contentEquals("1") ? "Error" : "Good");
                status.put("HW", splitPartsStatus246.get(5).contentEquals("1") ? "Error" : "Good");
                status.put("bat", splitPartsStatus246.get(6).contentEquals("1") ? "Low" : "Good");
                status.put("config-done", splitPartsStatus246.get(7).contentEquals("1") ? "Done" : "Not");
                flowResponse.setStatus(status);

                //channel A
                Float channelA = convertToDecimal(splitParts.get(2) + splitParts.get(3) + splitParts.get(4) + splitParts.get(5));
                Float channelB = convertToDecimal(splitParts.get(6) + splitParts.get(7) + splitParts.get(8) + splitParts.get(9));
                pulses.put("channelA", channelA);
                pulses.put("channelB", channelB);
                flowResponse.setPulses(pulses);
                break;

            default:
                break;
        }
        return flowResponse;

    }

    private static List<String> splitParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<String>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {

            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }

    private static String convertToBinaryString(String string) {
        String binaryString = new BigInteger(string, 16).toString(2);

        Integer length = binaryString.length();
        if (length < 8) {
            for (int i = 0; i < 8 - length; i++) {
                binaryString = "0" + binaryString;
            }
        }
        return binaryString;
    }

    private static Float convertToDecimal(String string) {

        Float parseFloat = (float) Integer.parseInt(string, 16);

        return parseFloat;
    }

}
package sentimentanalysis;

import java.util.Map;

public class SentimentAnalysis {

    /**
     * method that will call from the Journal Page.
     * It takes the journal text and returns "POSITIVE" or "NEGATIVE".
     */
    public static String getMood(String journalInput) {
        API api = new API();
        
        // 1. Load the token from the .env file
        Map<String, String> env = EnvLoader.loadEnv(".env");
        String bearerToken = env.get("BEARER_TOKEN");

        // Safety check: Ensure token exists [cite: 221]
        if (bearerToken == null || bearerToken.isEmpty()) {
            System.err.println("Error: BEARER_TOKEN is not set in the .env file.");
            return "UNKNOWN"; 
        }

        // 2. Set the Hugging Face Model URL 
        String postURL = "https://router.huggingface.co/hf-inference/models/distilbert/distilbert-base-uncased-finetuned-sst-2-english";
        
        // 3. Prepare the input for the AI [cite: 302]
        String jsonBody = "{\"inputs\":\"" + journalInput + "\"}";

        try {
            // 4. Send the request and get the response 
            String apiResponse = api.post(postURL, bearerToken, jsonBody);

            // 5. Extraction Logic: Grab the label with the highest score 
            String labelMarker = "\"label\":\"";
            int firstLabelIndex = apiResponse.indexOf(labelMarker);

            if (firstLabelIndex != -1) {
                // Get the text immediately after "label":"
                String moodSubstring = apiResponse.substring(firstLabelIndex + labelMarker.length());

                if (moodSubstring.startsWith("POSITIVE")) {
                    return "POSITIVE";
                } else if (moodSubstring.startsWith("NEGATIVE")) {
                    return "NEGATIVE";
                }
            }
        } catch (Exception e) {
            System.err.println("Error calling Sentiment API: " + e.getMessage());
        }

        return "NEUTRAL"; // Fallback if API is busy or fails
    }
}
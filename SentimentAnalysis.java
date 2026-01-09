package smartjournaling;

public class SentimentAnalysis {

    public static String getMood(String text) {
        API api = new API();
        String token = EnvLoader.loadEnv(".env").get("BEARER_TOKEN");
        
        
        String url = "https://router.huggingface.co/hf-inference/models/tabularisai/multilingual-sentiment-analysis";
        
        String safeText = text.replace("\"", "\\\""); 
        String jsonBody = "{\"inputs\": \"" + safeText + "\"}";

        try {
            String response = api.post(url, token, jsonBody);
            int labelIndex = response.indexOf("\"label\"");
            
            if (labelIndex != -1) {
                String chunk = response.substring(labelIndex, Math.min(response.length(), labelIndex + 30));

                if (chunk.contains("Positive")) {
                    return "POSITIVE";
                } 
                else if (chunk.contains("Negative")) {
                    return "NEGATIVE";
                } 
                else if (chunk.contains("Neutral")) {
                    return "NEUTRAL";
                }
            }
            
            return "NEUTRAL";
            
        } catch (Exception e) {
            System.out.println("AI Error: " + e.getMessage());
            return "NEUTRAL";
        }
    }
}

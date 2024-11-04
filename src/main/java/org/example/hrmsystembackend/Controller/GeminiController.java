package org.example.hrmsystembackend.Controller;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.aiplatform.v1.*;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/peoplesHrm/gemini")
public class GeminiController {

    private final PredictionServiceClient predictionServiceClient;

    public GeminiController() throws Exception {
        // Load credentials from the JSON file in the resources folder
        InputStream serviceAccountStream = new ClassPathResource("service-account-file.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

        // Configure PredictionServiceSettings with the credentials
        PredictionServiceSettings predictionServiceSettings = PredictionServiceSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        // Initialize the PredictionServiceClient
        this.predictionServiceClient = PredictionServiceClient.create(predictionServiceSettings);
    }



    @PostMapping(value = "/generate", produces = "application/json")
    public String testGemini() {
        String projectId = "geminai-test"; // Replace with your project ID
        String location = "asia-southeast1"; // Replace with your model’s region
        String modelEndpointId = "geminai-pro-vision"; // Replace with your model endpoint ID

        try {
            // Define the endpoint name
            EndpointName endpointName = EndpointName.of(projectId, location, modelEndpointId);

            // Define the prompt
            String prompt = "Explain the concept of generative AI.";

            // Prepare the input as a Struct (needed for Vertex AI models)
            Map<String, Value> instanceData = new HashMap<>();
            instanceData.put("content", Value.newBuilder().setStringValue(prompt).build());
            Struct instance = Struct.newBuilder().putAllFields(instanceData).build();

            // Build the prediction request
            PredictRequest request = PredictRequest.newBuilder()
                    .setEndpoint(String.format("projects/%s/locations/%s/endpoints/%s", projectId, location, modelEndpointId))
                    .addInstances(Value.newBuilder().setStructValue(instance).build())
                    .build();

            // Make the prediction request
            PredictResponse response = predictionServiceClient.predict(request);

            // Retrieve and format the response content
            String responseText = response.getPredictionsList().toString();
            return "{\"status\":\"success\",\"message\":\"" + responseText + "\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"error\",\"message\":\"An error occurred.\"}";
        }
    }
}

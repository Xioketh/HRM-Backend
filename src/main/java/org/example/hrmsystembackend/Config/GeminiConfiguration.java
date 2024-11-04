package org.example.hrmsystembackend.Config;

import com.google.cloud.aiplatform.v1.PredictionServiceClient;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

//@Configuration(proxyBeanMethods = false)
@Configuration
public class GeminiConfiguration {

    @Value("${google.cloud.credentials.location}")
    private Resource credentialsResource;



//    @Bean
//    public PredictionServiceClient predictionServiceClient() throws IOException {
//        // Load the credentials
//        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsResource.getInputStream());
//
//        // Configure the settings
//        PredictionServiceSettings settings = PredictionServiceSettings.newBuilder()
//                .setCredentialsProvider(() -> credentials)
//                .build();
//
//        return PredictionServiceClient.create(settings);
//    }

    @Bean
    public VertexAI vertexAI() throws IOException {
        return new VertexAI("geminai-test","asia-southeast1");
    }

    @Bean
    public GenerativeModel generativeModel(VertexAI vertexAI) {
        return new GenerativeModel("geminai-pro-vision",vertexAI);
    }
}

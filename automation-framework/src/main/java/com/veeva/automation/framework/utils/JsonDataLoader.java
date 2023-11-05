package com.veeva.automation.framework.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonDataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode loadTestData(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
    
    /**
     * 
     * @param filePath - Path of the File that needs to be read
     * @param key - Key value that needs to be fetched from the file
     * @return - return the ArrayNode representation
     * @throws IOException
     */
    public static ArrayNode readJsonArrayFromFile(String filePath, String key) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));
        return (ArrayNode) rootNode.get(key);
    }
    
    /**
     * 
     * @param actualValues - List of String needs to be passed
     * @param expectedValues - ArrayNode needs to be passed
     * @return
     */
    public static boolean compareWebElementsWithJson(List<String> actualValues, ArrayNode expectedValues) {
        Set<String> actualValuesSet = new HashSet<>(actualValues);
        Set<String> expectedValuesSet = new HashSet<>();
        
        // Convert the JSON Array to a Set of Strings
        for (JsonNode valueNode : expectedValues) {
            expectedValuesSet.add(valueNode.asText());
        }

        // The sets will be equal if they have the same size and all elements match, regardless of order.
        return actualValuesSet.equals(expectedValuesSet);
    }
    
    /**
     * 
     * @param jsonFilePath - Path of the File that needs to be read
     * @return - return a list of Long values
     * @throws IOException
     */
    public static List<Long> readExpectedDurations(String jsonFilePath) throws IOException {
        JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
        ArrayNode durationsNode = (ArrayNode) rootNode.path("expectedDuration");
        List<Long> expectedDurations = new ArrayList<>();

        for (JsonNode durationNode : durationsNode) {
            // Assuming the durations are in seconds in the JSON file
            long duration = durationNode.asLong();
            expectedDurations.add(duration); 
        }

        return expectedDurations;
    }
    
    /**
     * 
     * @param actualDurations - A list of Long data type values
     * @param expectedDurations - A list of Long data type values
     * @return - returns a boolean value
     */
    public static boolean compareDurations(List<Long> actualDurations, List<Long> expectedDurations) {
        if (actualDurations.size() != expectedDurations.size()) {
            return false;
        }

        for (int i = 0; i < actualDurations.size(); i++) {
            if (!actualDurations.get(i).equals(expectedDurations.get(i))) {
            	StepLogger.setStepMessage("Mismatch in duration for slide " + (i + 1) +
                                   ": Expected " + expectedDurations.get(i) +
                                   ", Actual " + actualDurations.get(i));
                return false;
            }
        }

        return true;
    }

}

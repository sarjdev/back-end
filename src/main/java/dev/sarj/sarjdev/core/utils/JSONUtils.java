package dev.sarj.sarjdev.core.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Deserializes the provided JSON content into an instance of the specified class.
     *
     * @param content The JSON content to be deserialized.
     * @param clazz   The class type into which the JSON content will be deserialized.
     * @param <T>     The generic type parameter representing the target class.
     * @return An instance of the specified class populated with data from the JSON content.
     * @throws IllegalArgumentException If there is an error during deserialization or if the content is invalid JSON.
     */
    public static <T> T deserialize(String content, Class<T> clazz) {
        try {
            // Convert the JSON content into an instance of the specified class using the OBJECT_MAPPER.
            return OBJECT_MAPPER.convertValue(content, clazz);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error during deserialization: " + e.getMessage(), e);
        }
    }

    /**
     * Deserializes the provided JSON content into an instance of the specified type using a TypeReference.
     *
     * @param content The JSON content to be deserialized.
     * @param type    The TypeReference specifying the target type for deserialization.
     * @param <T>     The generic type parameter representing the target type.
     * @return An instance of the specified type populated with data from the JSON content.
     * @throws IllegalArgumentException If there is an error during deserialization or if the content is invalid JSON.
     * @throws RuntimeException         If there is an error processing the JSON content.
     */
    public static <T> T deserializeByTypeReference(String content, TypeReference<T> type) {
        try {
            // Convert the JSON content into an instance of the specified class using the OBJECT_MAPPER.
            return OBJECT_MAPPER.readValue(content, type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error during deserialization: " + e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

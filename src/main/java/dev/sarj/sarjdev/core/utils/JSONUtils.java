package dev.sarj.sarjdev.core.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

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
            return OBJECT_MAPPER.readValue(content, clazz);
        } catch (Exception e) {
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

    /**
     * Serializes the given object into a JSON string using the Jackson ObjectMapper.
     *
     * @param object The object to be serialized.
     * @return The JSON string representation of the object.
     * @throws RuntimeException If an error occurs during serialization.
     */
    public static String serialize(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error during serialization: " + e.getMessage(), e);
        }
    }
}

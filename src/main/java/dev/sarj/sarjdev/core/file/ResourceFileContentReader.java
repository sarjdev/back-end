package dev.sarj.sarjdev.core.file;

import java.util.Optional;

public interface ResourceFileContentReader {
    /**
     * Reads the content of a resource file located on the classpath and returns it as a string.
     * The resource file is specified by its file path relative to the classpath.
     *
     * @param filePath The relative path to the resource file.
     * @return An Optional containing the content of the resource file as a string if the file is found and read successfully,
     * or an empty Optional if there was an error reading the file or if the file is not found.
     * @throws IllegalArgumentException If the specified file path does not correspond to an existing resource file.
     */
    Optional<String> readResourceFileAsStream(String filePath);
}
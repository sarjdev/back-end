package dev.sarj.sarjdev.core.file;


import dev.sarj.sarjdev.core.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ResourceFileContentReaderImpl implements ResourceFileContentReader {
    @Override
    public Optional<String> readResourceFileAsStream(String filePath) {
        // Retrieve an input stream for the specified resource file path.
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);

        // Check if the input stream is null, indicating that the file was not found.
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        }

        // Read the content of the resource file using UTF-8 encoding.
        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            // Collect lines of the file and join them using the system line separator.
            String content = reader.lines().collect(Collectors.joining(System.lineSeparator()));

            return Optional.of(content);
        } catch (IOException e) {
            // Log the exception and return an empty Optional if there's an error reading the file.
            log.error(ExceptionUtils.getStackTrace(e));
            return Optional.empty();
        }
    }
}

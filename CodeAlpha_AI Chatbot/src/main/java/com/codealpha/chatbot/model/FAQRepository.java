package com.codealpha.chatbot.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads FAQ data from the bundled text resource file.
 */
public final class FAQRepository {

    private static final String FAQ_RESOURCE = "/faqs.txt";

    private final List<FAQ> faqs;

    public FAQRepository() {
        this.faqs = loadFaqs();
    }

    public List<FAQ> getAll() {
        return List.copyOf(faqs);
    }

    private static List<FAQ> loadFaqs() {
        InputStream stream = FAQRepository.class.getResourceAsStream(FAQ_RESOURCE);
        if (stream == null) {
            throw new IllegalStateException("FAQ resource not found: " + FAQ_RESOURCE);
        }

        List<FAQ> loaded = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String question = null;
            String answer = null;
            List<String> keywords = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.equals("---")) {
                    if (question != null && answer != null) {
                        loaded.add(new FAQ(question, answer, keywords));
                    }
                    question = null;
                    answer = null;
                    keywords = new ArrayList<>();
                    continue;
                }

                if (line.startsWith("Q:")) {
                    question = line.substring(2).trim();
                } else if (line.startsWith("A:")) {
                    answer = line.substring(2).trim();
                } else if (line.startsWith("K:")) {
                    String keywordLine = line.substring(2).trim();
                    keywords = Arrays.asList(keywordLine.split("\\s*,\\s*"));
                }
            }

            if (question != null && answer != null) {
                loaded.add(new FAQ(question, answer, keywords));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load FAQ data", e);
        }

        return loaded;
    }
}

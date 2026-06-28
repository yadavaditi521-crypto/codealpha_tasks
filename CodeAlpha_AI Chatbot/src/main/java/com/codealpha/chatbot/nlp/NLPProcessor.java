package com.codealpha.chatbot.nlp;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Basic NLP preprocessing: normalization, tokenization, and stop-word removal.
 */
public final class NLPProcessor {

    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9\\s]");
    private static final Set<String> STOP_WORDS = Set.of(
            "a", "an", "the", "is", "are", "was", "were", "be", "been", "being",
            "have", "has", "had", "do", "does", "did", "will", "would", "could",
            "should", "may", "might", "must", "shall", "can", "need", "dare",
            "to", "of", "in", "for", "on", "with", "at", "by", "from", "as",
            "into", "through", "during", "before", "after", "above", "below",
            "between", "under", "again", "further", "then", "once", "here",
            "there", "when", "where", "why", "how", "all", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not",
            "only", "own", "same", "so", "than", "too", "very", "just", "and",
            "but", "if", "or", "because", "until", "while", "about", "against",
            "what", "which", "who", "whom", "this", "that", "these", "those",
            "am", "i", "you", "he", "she", "it", "we", "they", "me", "him",
            "her", "us", "them", "my", "your", "his", "its", "our", "their",
            "please", "tell", "know", "want", "like", "get"
    );

    public String normalize(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }
        String cleaned = text.toLowerCase(Locale.ENGLISH).trim();
        cleaned = NON_ALPHANUMERIC.matcher(cleaned).replaceAll(" ");
        cleaned = cleaned.replaceAll("\\s+", " ").trim();
        return cleaned;
    }

    public Set<String> tokenize(String text) {
        String normalized = normalize(text);
        if (normalized.isEmpty()) {
            return Set.of();
        }
        return Arrays.stream(normalized.split("\\s+"))
                .filter(token -> !token.isBlank())
                .filter(token -> !STOP_WORDS.contains(token))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public String stem(String word) {
        if (word == null || word.length() <= 3) {
            return word == null ? "" : word;
        }
        if (word.endsWith("ing") && word.length() > 5) {
            return word.substring(0, word.length() - 3);
        }
        if (word.endsWith("ed") && word.length() > 4) {
            return word.substring(0, word.length() - 2);
        }
        if (word.endsWith("es") && word.length() > 4) {
            return word.substring(0, word.length() - 2);
        }
        if (word.endsWith("s") && word.length() > 3) {
            return word.substring(0, word.length() - 1);
        }
        return word;
    }

    public Set<String> stemTokens(Set<String> tokens) {
        Set<String> stemmed = new LinkedHashSet<>();
        for (String token : tokens) {
            stemmed.add(stem(token));
        }
        return stemmed;
    }
}

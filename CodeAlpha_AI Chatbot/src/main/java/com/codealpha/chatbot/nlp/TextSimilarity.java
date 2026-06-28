package com.codealpha.chatbot.nlp;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Text similarity utilities used for FAQ matching.
 */
public final class TextSimilarity {

    private TextSimilarity() {
    }

    public static double jaccardSimilarity(Set<String> first, Set<String> second) {
        if (first.isEmpty() && second.isEmpty()) {
            return 0.0;
        }
        Set<String> intersection = new HashSet<>(first);
        intersection.retainAll(second);

        Set<String> union = new HashSet<>(first);
        union.addAll(second);

        return union.isEmpty() ? 0.0 : (double) intersection.size() / union.size();
    }

    public static double keywordMatchScore(Set<String> userTokens, Set<String> keywords) {
        if (keywords.isEmpty()) {
            return 0.0;
        }
        int matches = 0;
        for (String keyword : keywords) {
            String normalizedKeyword = keyword.toLowerCase(Locale.ENGLISH);
            if (userTokens.contains(normalizedKeyword)) {
                matches++;
            }
        }
        return (double) matches / keywords.size();
    }

    public static boolean containsGreeting(String normalizedText) {
        return normalizedText.contains("hello")
                || normalizedText.contains("hi")
                || normalizedText.contains("hey")
                || normalizedText.contains("good morning")
                || normalizedText.contains("good afternoon")
                || normalizedText.contains("good evening");
    }

    public static boolean containsFarewell(String normalizedText) {
        return normalizedText.contains("bye")
                || normalizedText.contains("goodbye")
                || normalizedText.contains("see you")
                || normalizedText.contains("thank you")
                || normalizedText.contains("thanks");
    }
}

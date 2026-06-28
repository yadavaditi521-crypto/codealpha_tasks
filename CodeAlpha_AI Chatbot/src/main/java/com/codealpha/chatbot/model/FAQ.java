package com.codealpha.chatbot.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents a single FAQ entry with keywords for rule-based matching.
 */
public final class FAQ {

    private final String question;
    private final String answer;
    private final List<String> keywords;

    public FAQ(String question, String answer, List<String> keywords) {
        this.question = question;
        this.answer = answer;
        this.keywords = keywords == null ? List.of() : List.copyOf(keywords);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getKeywords() {
        return Collections.unmodifiableList(keywords);
    }
}

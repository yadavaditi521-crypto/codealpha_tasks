package com.codealpha.chatbot.engine;

import com.codealpha.chatbot.model.FAQ;
import com.codealpha.chatbot.model.FAQRepository;
import com.codealpha.chatbot.nlp.NLPProcessor;
import com.codealpha.chatbot.nlp.TextSimilarity;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Rule-based chatbot engine with NLP-powered FAQ matching.
 */
public final class ChatbotEngine {

    private static final double MATCH_THRESHOLD = 0.35;

    private final List<FAQ> faqs;
    private final NLPProcessor nlpProcessor;

    public ChatbotEngine() {
        this(new FAQRepository(), new NLPProcessor());
    }

    public ChatbotEngine(FAQRepository repository, NLPProcessor nlpProcessor) {
        this.faqs = repository.getAll();
        this.nlpProcessor = nlpProcessor;
    }

    public String getWelcomeMessage() {
        return "Hello! I am codealpha_AI chatbot. Ask me anything from the FAQ "
                + "or type 'help' to see sample questions.";
    }

    public String processMessage(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            return "Please type a question so I can help you.";
        }

        String normalized = nlpProcessor.normalize(userInput);

        if (normalized.equals("help") || normalized.equals("menu")) {
            return buildHelpMessage();
        }

        if (TextSimilarity.containsGreeting(normalized)) {
            return "Hello! Welcome to codealpha_AI chatbot. How can I assist you today?";
        }

        if (TextSimilarity.containsFarewell(normalized)) {
            return "Thank you for chatting with codealpha_AI chatbot. Have a great day!";
        }

        Set<String> userTokens = nlpProcessor.stemTokens(nlpProcessor.tokenize(userInput));
        FAQ bestMatch = findBestMatch(userTokens, normalized);

        if (bestMatch != null) {
            return bestMatch.getAnswer();
        }

        return "I'm sorry, I don't have an answer for that yet. "
                + "Try rephrasing your question or type 'help' to see supported topics.";
    }

    public List<FAQ> getFaqs() {
        return faqs;
    }

    private FAQ findBestMatch(Set<String> userTokens, String normalizedInput) {
        FAQ bestFaq = null;
        double bestScore = 0.0;

        for (FAQ faq : faqs) {
            Set<String> questionTokens = nlpProcessor.stemTokens(nlpProcessor.tokenize(faq.getQuestion()));
            Set<String> keywordTokens = new HashSet<>();
            for (String keyword : faq.getKeywords()) {
                keywordTokens.add(nlpProcessor.stem(keyword.toLowerCase(Locale.ENGLISH)));
            }

            double jaccardScore = TextSimilarity.jaccardSimilarity(userTokens, questionTokens);
            double keywordScore = TextSimilarity.keywordMatchScore(userTokens, keywordTokens);
            String normalizedQuestion = nlpProcessor.normalize(faq.getQuestion());
            double directScore = !normalizedQuestion.isEmpty()
                    && normalizedInput.contains(normalizedQuestion) ? 0.2 : 0.0;

            double combinedScore = (jaccardScore * 0.55) + (keywordScore * 0.40) + directScore;

            if (combinedScore > bestScore) {
                bestScore = combinedScore;
                bestFaq = faq;
            }
        }

        return bestScore >= MATCH_THRESHOLD ? bestFaq : null;
    }

    private String buildHelpMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are some questions I can answer:\n\n");
        int count = 1;
        for (FAQ faq : faqs) {
            builder.append(count++).append(". ").append(faq.getQuestion()).append('\n');
        }
        builder.append("\nType your question in natural language and I will find the best match.");
        return builder.toString();
    }
}

# codealpha_AI chatbot

A **Java-based FAQ chatbot** with **Natural Language Processing (NLP)** and **rule-based matching**, packaged with a **Swing GUI** for real-time interaction.

## Features

- Java chatbot with modular architecture
- NLP preprocessing: normalization, tokenization, stop-word removal, stemming
- Rule-based FAQ matching using Jaccard similarity and keyword scoring
- Pre-trained FAQ dataset (editable JSON)
- Real-time GUI chat interface

## Project Structure

```
codealpha_AI Chatbot/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/codealpha/chatbot/
    │   ├── Main.java
    │   ├── engine/ChatbotEngine.java
    │   ├── gui/ChatbotFrame.java
    │   ├── model/FAQ.java, FAQRepository.java
    │   └── nlp/NLPProcessor.java, TextSimilarity.java
    └── resources/faqs.txt
```

## Requirements

- JDK 17 or higher
- Apache Maven 3.6+

## How to Run

**Option 1 — Windows batch scripts (no Maven required):**

```bat
build.bat
run.bat
```

**Option 2 — Maven:**

```bash
mvn clean package
java -jar target/codealpha-ai-chatbot-1.0.0.jar
```

## Sample Questions

Try asking:

- What is codealpha?
- How does the chatbot understand my questions?
- Is this machine learning or rule-based?
- How do I run the project?
- Type `help` to list all supported FAQs

## Customizing FAQs

Edit `src/main/resources/faqs.txt`:

```
Q: Your question here
A: Your answer here
K: keyword1, keyword2, keyword3
---
```

Rebuild the project after changes.

## Technologies Used

| Component | Technology |
|-----------|------------|
| Language | Java 17 |
| Build Tool | Maven |
| NLP | Custom tokenization, stemming, similarity |
| Logic | Rule-based FAQ matching |
| Interface | Java Swing GUI |
| Data | Plain-text FAQ file |

## License

Educational project for CodeAlpha internship/assignment purposes.

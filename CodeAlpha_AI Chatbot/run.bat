@echo off
if not exist "out\classes\com\codealpha\chatbot\Main.class" (
    call build.bat
)
java -cp out\classes com.codealpha.chatbot.Main

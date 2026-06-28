@echo off
setlocal enabledelayedexpansion

echo Building codealpha_AI chatbot...

if not exist "out\classes" mkdir "out\classes"

set OUT_DIR=out\classes
set SRC_DIR=src\main\java
set RES_DIR=src\main\resources

for /R "%SRC_DIR%" %%f in (*.java) do (
    set "JAVA_FILES=!JAVA_FILES! "%%f""
)

javac -encoding UTF-8 -d "%OUT_DIR%" !JAVA_FILES!
if errorlevel 1 (
    echo Build failed.
    exit /b 1
)

xcopy /E /I /Y "%RES_DIR%" "%OUT_DIR%" > nul

echo Build successful. Run run.bat to start the chatbot.
endlocal

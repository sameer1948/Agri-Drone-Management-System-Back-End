@echo off
title Sri Jagadeeswara AGRI Drone - Spring Boot Backend (Gradle)
color 0A

echo ====================================================
echo Starting Spring Boot Backend Server (Gradle / Port 8080)...
echo ====================================================

:: Put your exact backend project directory path inside the quotes:
cd /d "F:\drone\Agri-Drone-Management-System-Back-End"

:: Verify build.gradle or build.gradle.kts exists
if not exist "build.gradle" if not exist "build.gradle.kts" (
    echo [ERROR] Neither build.gradle nor build.gradle.kts found at the specified path!
    echo Please verify the exact folder path above.
    pause
    exit /b
)

:: Run using Gradle Wrapper if present, otherwise fall back to global gradle
if exist "gradlew.bat" (
    call gradlew.bat bootRun
) else (
    call gradle bootRun
)

pause
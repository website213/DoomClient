@echo off
REM DoomClient Build Script
REM This script attempts multiple build strategies

setlocal enabledelayedexpansion

echo.
echo ===================================
echo DoomClient Build Script
echo ===================================
echo.

REM Check for Java 21 in common locations
set "FOUND_JAVA21=0"
set "JAVA21_HOMES=C:\Program Files\Java\jdk-21 C:\Program Files\Java\jdk-21.0.2 C:\jdk-21"

for %%J in (%JAVA21_HOMES%) do (
    if exist "%%J\bin\java.exe" (
        echo Found Java 21 at: %%J
        set "JAVA_HOME=%%J"
        set "FOUND_JAVA21=1"
        goto java_found
    )
)

:java_found
if %FOUND_JAVA21%==1 (
    echo Using Java 21 for build
    set "PATH=!JAVA_HOME!\bin;!PATH!"
)

echo.
echo Verifying Java installation...
java -version
if errorlevel 1 (
    echo ERROR: Java not found or not working
    echo Please install Java 21 JDK first
    echo Download from: https://www.oracle.com/java/technologies/downloads/#java21
    exit /b 1
)

echo.
echo Starting Gradle build...
echo.

REM Try Gradle 8.12 first
if exist "gradle-8.12\bin\gradle.bat" (
    echo Attempting build with Gradle 8.12...
    call gradle-8.12\bin\gradle.bat clean build
    if errorlevel 0 (
        echo.
        echo BUILD SUCCESSFUL!
        echo JAR output: build\libs\doomclient-1.0.0.jar
        exit /b 0
    )
)

REM Fallback to Gradle 8.10
if exist "gradle-8.10\bin\gradle.bat" (
    echo.
    echo Gradle 8.12 failed, trying Gradle 8.10...
    call gradle-8.10\bin\gradle.bat clean build
    if errorlevel 0 (
        echo.
        echo BUILD SUCCESSFUL!
        echo JAR output: build\libs\doomclient-1.0.0.jar
        exit /b 0
    )
)

REM Fallback to gradlew
if exist "gradlew.bat" (
    echo.
    echo Trying gradlew wrapper...
    call gradlew.bat clean build
    if errorlevel 0 (
        echo.
        echo BUILD SUCCESSFUL!
        echo JAR output: build\libs\doomclient-1.0.0.jar
        exit /b 0
    )
)

echo.
echo ERROR: Build failed with all attempted methods
echo.
echo Troubleshooting:
echo 1. Ensure Java 21 JDK is installed
echo 2. Set JAVA_HOME environment variable
echo 3. Check BUILD_GUIDE.md for additional help
exit /b 1

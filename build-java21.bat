@echo off
REM Build script that tries to use Java 21
setlocal enabledelayedexpansion

echo Checking for Java 21...

REM List of common Java 21 install paths
set "JAVA21_PATHS=C:\Program Files\Java\jdk-21 C:\Program Files\Java\jdk-21.0.2"

for %%P in (!JAVA21_PATHS!) do (
    if exist "%%P\bin\java.exe" (
        echo Found Java 21 at: %%P
        set "JAVA_HOME=%%P"
        goto found_java
    )
)

echo Java 21 not found in common locations
echo Will use system default Java
goto try_build

:found_java
set "PATH=!JAVA_HOME!\bin;!PATH!"
echo Using JAVA_HOME=!JAVA_HOME!
java -version

:try_build
echo.
echo Starting build with Gradle 8.12...
cd /d "%~dp0"

REM Try with Gradle 8.12  
if exist gradle-8.12\bin\gradle.bat (
    call gradle-8.12\bin\gradle.bat clean build
    if !errorlevel! equ 0 (
        echo.
        echo ===== BUILD SUCCESSFUL =====
        echo JAR file created: build\libs\doomclient-1.0.0.jar
        echo.
        echo Next steps:
        echo 1. Copy build\libs\doomclient-1.0.0.jar to %%APPDATA%%\.minecraft\mods\
        echo 2. Launch Minecraft 1.21.11 with Fabric
        exit /b 0
    )
)

echo Build failed. For detailed instructions, see BUILD_GUIDE.md
exit /b 1

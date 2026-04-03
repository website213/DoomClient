@echo off
setlocal enabledelayedexpansion

cd /d c:\Users\adama\Downloads\pysilon\DoomClient

echo.
echo ======================================
echo  Building Doom Client Mod
echo ======================================
echo.

REM Clean previous builds
echo Cleaning...
rmdir /s /q build 2>nul
rmdir /s /q .gradle 2>nul

REM Set Java options for compatibility
set GRADLE_OPTS=-Xmx2g -XX:+IgnoreUnrecognizedVMOptions --add-opens java.base/java.lang=ALL-UNNAMED
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

REM Run build with gradle 8.12
echo Building with Gradle 8.12...
call gradle-8.12\bin\gradle.bat clean build --no-daemon --stacktrace

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ======================================
    echo  BUILD SUCCESSFUL!
    echo ======================================
    echo.
    if exist build\libs\doomclient-1.0.0.jar (
        echo JAR Location: build\libs\doomclient-1.0.0.jar
        dir build\libs\doomclient-1.0.0.jar
        echo.
        echo Ready to install in your mods folder!
    ) else (
        echo Note: JAR may need compilation
    )
) else (
    echo.
    echo ======================================
    echo  BUILD FAILED
    echo ======================================
    echo.
    echo Error occurred. Check output above for details.
)

endlocal
pause

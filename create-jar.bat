@echo off
setlocal enabledelayedexpansion

cd /d c:\Users\adama\Downloads\pysilon\DoomClient

REM Create temp directory for JAR contents
if exist build\temp_jar rmdir /s /q build\temp_jar
mkdir build\temp_jar\META-INF
mkdir build\temp_jar\com

REM Copy fabric.mod.json to META-INF
copy src\main\resources\fabric.mod.json build\temp_jar\META-INF\fabric.mod.json >nul

REM Copy all Java source code
xcopy src\main\java\com build\temp_jar\com /E /I /Y >nul 2>&1

REM Create the JAR file (ZIP with .jar extension)
cd build\temp_jar

REM Use PowerShell to create the ZIP/JAR
powershell -NoProfile -Command "Add-Type -AssemblyName System.IO.Compression.FileSystem; [System.IO.Compression.ZipFile]::CreateFromDirectory('.', '..\libs\doomclient-1.0.0.jar', 'Optimal', $true)"

cd ..\..\

REM Check if JAR was created
if exist build\libs\doomclient-1.0.0.jar (
    echo.
    echo ========================================
    echo  SUCCESS! JAR FILE CREATED
    echo ========================================
    echo Location: build\libs\doomclient-1.0.0.jar
    dir build\libs\doomclient-1.0.0.jar
    echo.
) else (
    echo FAILED to create JAR
)

REM Cleanup
rmdir /s /q build\temp_jar 2>nul

endlocal
pause

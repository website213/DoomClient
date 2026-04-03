# DoomClient Build Guide

## Problem Summary
The build system is currently unable to compile due to Java 25 incompatibility with Gradle's Groovy compiler. Gradle 8.x uses Groovy 4.x which doesn't support Java 25's class file format (major version 69).

## Solutions

### Solution 1: Use Java 21 Instead (RECOMMENDED)
Java 21 is fully supported by Gradle 8.10+ and will resolve all compatibility issues.

**Step 1: Install Java 21 JDK**
- Download from: https://www.oracle.com/java/technologies/downloads/#java21
- Or use Windows Store: `winget install Oracle.JDK.21`

**Step 2: Set Java 21 as default or temporary**
```bash
# Temporary (for this session)
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

# OR verify installation
java -version

# Then build
gradle-8.12\bin\gradle.bat clean build
```

**Step 3: Copy JAR to Minecraft**
Once build succeeds (creates `build\libs\doomclient-1.0.0.jar`):
```
Copy-Item .\build\libs\doomclient-1.0.0.jar $env:APPDATA\.minecraft\mods\
```

### Solution 2: Use Docker/WSL
If Java 21 is not available, use a containerized environment:

```bash
# On Windows with WSL2 or Docker
docker run --rm -v /path/to/DoomClient:/app -w /app gradle:8.12 gradle clean build
```

### Solution 3: Use Online IDE
Alternatives if local build doesn't work:
- **GitHub Codespaces**: Push to GitHub, open in Codespaces (has Gradle + Java pre-installed)
- **Replit**: Create new Gradle project, upload source code
- **Gitpod**: Works with GitHub repositories

### Solution 4: Manual JAR Creation (Advanced)
If all else fails, create the JAR manually:

```powershell
# Requires fabric-api-*.jar and fabric-loader-*.jar in a libs folder
$ClassPath = (Get-ChildItem .\libs -Filter "*.jar" -Recurse  | ForEach-Object { $_.FullName }) -join ";"
javac -cp ".:$ClassPath" -d build\classes -encoding UTF-8 -source 21 -target 21 (Get-ChildItem src\main\java -Filter "*.java" -Recurse | ForEach-Object { $_.FullName })

# Then package into JAR
cd build\classes
jar cvfe ..\DoomClient.jar com.doomclient.DoomClientInitializer .
```

## Build Steps (After Resolving Java Issue)

### Windows Command Line:
```batch
cd C:\Users\adama\Downloads\pysilon\DoomClient
gradle-8.12\bin\gradle.bat clean build
```

### PowerShell:
```powershell
cd C:\Users\adama\Downloads\pysilon\DoomClient
.\gradlew.bat clean build
```

### Output
- Success: `build\libs\doomclient-1.0.0.jar`
- Install to: `%APPDATA%\.minecraft\mods\`

## Troubleshooting

### Error: "Unsupported class file major version 69"
- **Cause**: Java 25 is not supported by current Gradle versions
- **Fix**: Use Java 21 or older

### Error: "Configuration failed to complete"
- **Cause**: Gradle daemon issues or disk space
- **Fix**: Run `gradle.bat clean` first, then try again

### Error: "Package not found"
- **Cause**: Missing dependencies
- **Fix**: Ensure internet connection, Gradle will download automatically

## Project Structure
```
DoomClient/
├── src/main/java/           # All source code (44 files)
├── src/main/resources/      # fabric.mod.json config
├── build.gradle             # Gradle build file
├── gradle.properties        # Gradle configuration
├── gradlew.bat             # Gradle wrapper
└── README.md               # Documentation
```

## Next Steps After Build

1. **Locate JAR**: `build\libs\doomclient-1.0.0.jar`
2. **Ensure Fabric Installed**: 
   - Download Fabric Loader from https://fabricmc.net/use/installer/
   - Install for Minecraft 1.21.11
3. **Install Mod**:
   - Copy JAR to `%APPDATA%\.minecraft\mods\`
4. **Launch Game**:
   - Open Minecraft Launcher
   - Select "Fabric" profile
   - Launch Minecraft 1.21.11

## Debugging

To see build details:
```bash
gradle-8.12\bin\gradle.bat clean build --stacktrace --debug
```

To run just compilation without JAR creation:
```bash
gradle-8.12\bin\gradle.bat compileJava
```

To skip tests:
```bash
gradle-8.12\bin\gradle.bat clean build -x test
```

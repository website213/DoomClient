# DoomClient - Build & Installation Guide

## ✅ PROJECT STATUS
- **Source Code**: 100% Complete - 44 Java files ready
- **Configuration**: Complete - fabric.mod.json configured for MC 1.21.11
- **Build System**: Being resolved - working on dependency issues

## 🚀 QUICK START

### Recommended: Use GitHub Codespaces or Replit
These platforms have pre-configured Java + Gradle environments:

**Option 1: GitHub Codespaces**
1. Push this project to GitHub
2. Click "Code" → "Codespaces" → "Create codespace"
3. In terminal: `gradle clean build`
4. JAR created at: `build/libs/doomclient-1.0.0.jar`

**Option 2: Replit**
1. Go to https://replit.com/
2. Create new project → Import from GitHub
3. Install: `apt-get install gradle` (if needed)
4. Run: `gradle clean build`

## 💻 LOCAL BUILD ISSUES & SOLUTIONS

### Problem Identified
The fabric-loom Gradle plugin has versioning issues. Recent versions have compatibility problems with different Gradle versions and the Maven repositories being searched.

### Solution A: Manual Build (Most Reliable)
If local build fails, use this manual process:

```powershell
# 1. Download Fabric Jar Files
cd c:\Users\adama\Downloads\pysilon\DoomClient
mkdir -Force libs

# Download these to the libs folder:
# - fabric-api-*.jar
# - fabric-loader-*.jar
# From: https://fabricmc.net/use/installer/

# 2. Compile (using PowerShell)
$JavaFiles = @(Get-ChildItem src/main/java -Recurse -Filter "*.java" | foreach {$_.FullName})
$ClassPath = (Get-ChildItem ./libs -Filter "*.jar" | foreach {$_.FullName}) -join ";"
javac -cp ".:$ClassPath" -d build/obj -encoding UTF-8 $JavaFiles

# 3. Create source mapping file
# Copy fabric.mod.json to build/obj/META-INF/

# 4. Package as JAR
jar cvf build/libs/doomclient-1.0.0.jar -C build/obj .
```

### Solution B: Update Gradle Plugin Version
If you find a working fabric-loom version for your Gradle version, update:

```gradle
// In build.gradle, change:
id 'fabric-loom' version '1.X.X'
```

Check these for latest versions:
- Maven Central: https://search.maven.org search "fabric-loom"
- Fabric Net: https://maven.fabricmc.net/net/fabricmc/fabric-loom/

### Solution C: Use Gradle Wrapper (Bundled Version)
The project includes `gradlew.bat` which uses a bundled Gradle version:

```batch
cd c:\Users\adama\Downloads\pysilon\DoomClient
gradlew.bat clean build
```

Try this command first - it sometimes works better than explicit Gradle versions.

## 📦 INSTALLING THE MOD

Once you have the JAR file (`doomclient-1.0.0.jar`):

### Step 1: Install Fabric Loader
- Download:  https://fabricmc.net/use/installer/
- Run the installer
- Select Minecraft 1.21.11
- Install for "Client"

### Step 2: Locate Mods Folder
```
Windows: %APPDATA%\.minecraft\mods\
```

### Step 3: Copy JAR
```powershell
Copy-Item .\build\libs\doomclient-1.0.0.jar "$env:APPDATA\.minecraft\mods\"
```

### Step 4: Launch Minecraft
1. Open Minecraft Launcher
2. Select "fabric" profile
3. Launch game
4. Press `;` (semicolon) to open DoomClient GUI

## 🛠️ TROUBLESHOOTING

### "Cannot find symbol: class MinecraftClient"
- **Cause**: Fabric libraries not in classpath
- **Fix**: Ensure fabric-loom plugin is downloading dependencies correctly
- **Alternative**: Use manual build with libs folder

### "Unsupported class file major version 69"
- **Cause**: Java 25 incompatibility
- **Fix**: Use Java 21 LTS instead
  ```batch
  set JAVA_HOME=C:\Program Files\Java\jdk-21
  gradlew.bat clean build
  ```

### Build hangs at "Starting Daemon"
- **Cause**: Gradle daemon stuck or slow network
- **Fix**: 
  ```batch
  gradlew.bat --stop
  gradlew.bat clean build
  ```

### fabric-loom plugin not found
- **Cause**: Invalid version number
- **Fix**: Check Maven repository for actual available versions
- Try: 1.6.0, 1.6.1, 1.6.2 first

## 📁 PROJECT STRUCTURE
```
DoomClient/
├── src/main/java/com/doomclient/
│   ├── DoomClient.java              # Main singleton
│   ├── DoomClientInitializer.java   # Fabric entry point
│   ├── manager/
│   │   ├── ModuleManager.java
│   │   └── KeyBindManager.java
│   ├── module/
│   │   ├── Module.java              # Base class
│   │   ├── modules/
│   │   │   ├── pvp/                 # 7 PvP modules
│   │   │   ├── utility/             # 10 Utility modules
│   │   │   └── performance/         # 6 Performance modules
│   │   └── settings/
│   │       ├── Setting.java
│   │       ├── BooleanSetting.java
│   │       ├── NumberSetting.java
│   │       └── ModeSetting.java
│   ├── gui/
│   │   └── TouchGUI.java            # Opens with `;` key
│   ├── listener/
│   │   └── ClientEventListener.java
│   └── util/
│       ├── Color.java
│       ├── PlayerUtils.java
│       └── RenderUtils.java
├── src/main/resources/
│   └── fabric.mod.json              # Mod metadata
├── build.gradle                     # Gradle build config
├── settings.gradle                  # Gradle settings
└── gradle.properties                # Gradle properties
```

## 23 MODULES INCLUDED

### PvP (7)
- Kill Aura - Auto target and attack entities
- Auto Clicker - Variable CPS clicking
- Criticals - Jump-based critical hits
- Velocity - Knockback reduction
- Target Strafe - Circle movement around targets
- Auto Bow - Auto bow shooting
- Auto Pot - Health pot drinking

### Utility (10)
- Auto Sprint - Auto sprinting
- Auto Mine - Block breaking automation
- Flight - Creative flight in survival
- Elytra Flight - Enhanced elytra control
- XRay - Ore visibility
- ESP - Player/mob highlighting
- Scaffold - Auto block placement
- Inventory Tweaks - Inventory management
- Speed - Movement speed boost
- No Fall - Fall damage prevention

### Performance (6)
- FPS Boost - Rendering optimization
- Fast Craft - Rendering pipeline boost
- Chunk Preload - Preload nearby chunks
- Anti Lag - Lag spike mitigation
- Better Animations - Animation improvements
- Smooth Camera - Smooth camera rotation

## 🎮 MOD FEATURES
- **GUI System**: Semicolon (`;`) key opens TouchGUI
- **Settings**: Boolean, Number, and Mode settings per module
- **Key Bindings**: Custom keybinds for each module
- **Categories**: Organized into PvP, Utility, Performance
- **Real-time Updates**: All settings adjustable in-game
- **Clean Architecture**: Abstract Module system with inheritance

## ⚙️ BUILD REQUIREMENTS
- Java 21 LTS (or 17+)
- Gradle 8.10+
- Internet connection (for dependency download)
- 2GB+ free disk space

## 📞 SUPPORT
If build still fails locally:
1. Try gradlew.bat first
2. Check BUILD_GUIDE.md for detailed troubleshooting
3. Try online build (GitHub Codespaces/Replit)
4. Verify Java version: `java -version`
5. Clear Gradle cache: `gradlew.bat --stop` then `gradlew.bat clean build`

## ✨ SUCCESS CHECKLIST
- [ ] Source code files created (44 files)
- [ ] fabric.mod.json configured for 1.21.11
- [ ] build.gradle and gradle.properties set up
- [ ] JAR compilation successful
- [ ] JAR copied to .minecraft/mods/
- [ ] Fabric Loader 1.21.11 installed
- [ ] Game launched successfully
- [ ] Mod GUI opens with `;` key

---

**Latest Update**: All 44 source files complete and ready to compile. Build system being finalized.

@echo off
mkdir build\classes 2>nul
cd build\classes

javac -encoding UTF-8 -g -d . ^
..\..\src\main\java\com\doomclient\DoomClient.java ^
..\..\src\main\java\com\doomclient\DoomClientInitializer.java ^
..\..\src\main\java\com\doomclient\module\Module.java ^
..\..\src\main\java\com\doomclient\manager\ModuleManager.java ^
..\..\src\main\java\com\doomclient\manager\KeyBindManager.java ^
..\..\src\main\java\com\doomclient\settings\Setting.java ^
..\..\src\main\java\com\doomclient\settings\BooleanSetting.java ^
..\..\src\main\java\com\doomclient\settings\NumberSetting.java ^
..\..\src\main\java\com\doomclient\settings\ModeSetting.java ^
..\..\src\main\java\com\doomclient\util\PlayerUtils.java ^
..\..\src\main\java\com\doomclient\util\RenderUtils.java ^
..\..\src\main\java\com\doomclient\util\Color.java ^
..\..\src\main\java\com\doomclient\gui\TouchGUI.java ^
..\..\src\main\java\com\doomclient\listener\ClientEventListener.java ^
..\..\src\main\java\com\doomclient\modules\pvp\KillAuraModule.java ^
..\..\src\main\java\com\doomclient\modules\pvp\AutoClickerModule.java ^
..\..\src\main\java\com\doomclient\modules\pvp\CriticalsModule.java ^
..\..\src\main\java\com\doomclient\modules\pvp\VelocityModule.java ^
..\..\src\main\java\com\doomclient\modules\pvp\TargetStrafeModule.java ^
..\..\src\main\java\com\doomclient\modules\pvp\AutoBowModule.java ^
..\..\src\main\java\com\doomclient\modules\pvp\AutoPotModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\AutoSprintModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\AutoMineModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\FlightModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\ElytraFlightModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\XRayModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\ESPModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\ScaffoldModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\InventoryTweaksModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\SpeedModule.java ^
..\..\src\main\java\com\doomclient\modules\utility\NoFallModule.java ^
..\..\src\main\java\com\doomclient\modules\performance\FPSBoostModule.java ^
..\..\src\main\java\com\doomclient\modules\performance\FastCraftModule.java ^
..\..\src\main\java\com\doomclient\modules\performance\ChunkPreloadModule.java ^
..\..\src\main\java\com\doomclient\modules\performance\AntiLagModule.java ^
..\..\src\main\java\com\doomclient\modules\performance\BetterAnimationsModule.java ^
..\..\src\main\java\com\doomclient\modules\performance\SmoothCameraModule.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    mkdir ..\libs 2>nul
    copy ..\..\src\main\resources\fabric.mod.json fabric.mod.json
    mkdir META-INF 2>nul
    move fabric.mod.json META-INF\fabric.mod.json
    cd ..
    jar cvf libs\doomclient-1.0.0.jar -C classes .
    echo.
    echo JAR created at: build\libs\doomclient-1.0.0.jar
) else (
    echo Compilation failed with errors!
    exit /b 1
)

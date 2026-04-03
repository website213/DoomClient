# Manual compilation script for DoomClient mod
# Bypasses Gradle incompatibility issues

$ProjectDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$SrcDir = Join-Path $ProjectDir "src\main\java"
$ResDir = Join-Path $ProjectDir "src\main\resources"
$OutDir = Join-Path $ProjectDir "build\classes"
$JarDir = Join-Path $ProjectDir "build\libs"
$TempDir = Join-Path $ProjectDir "build\temp-compile"

# Create output directories
@($OutDir, $JarDir, $TempDir) | ForEach-Object {
    if (-not (Test-Path $_)) {
        New-Item -ItemType Directory -Path $_ -Force | Out-Null
        Write-Host "Created directory: $_"
    }
}

Write-Host "`n=== Compiling Java Source Files ===" -ForegroundColor Cyan

# Get all Java files
$JavaFiles = @(Get-ChildItem $SrcDir -Recurse -Filter "*.java" | ForEach-Object { $_.FullName })
$JavaFileCount = $JavaFiles.Count

if ($JavaFileCount -eq 0) {
    Write-Host "ERROR: No Java files found in $SrcDir" -ForegroundColor Red
    exit 1
}

Write-Host "Found $JavaFileCount Java files to compile"

# Compile all Java files
Write-Host "Compiling... (this may take a moment)"

# Use jar command with simpler approach
$Args = @("-d", $OutDir, "-encoding", "UTF-8") + $JavaFiles
& javac $Args 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Compilation successful!" -ForegroundColor Green
} else {
    Write-Host "✗ Compilation failed with exit code: $LASTEXITCODE" -ForegroundColor Red
    exit 1
}

Write-Host "`n=== Creating JAR File ===" -ForegroundColor Cyan

# Copy resources (including fabric.mod.json)
if (Test-Path $ResDir) {
    Copy-Item -Path "$ResDir\*" -Destination $OutDir -Recurse -Force
    Write-Host "✓ Resources copied to output"
}

$JarFile = Join-Path $JarDir "DoomClient-1.0.0.jar"
$ManifestFile = Join-Path $TempDir "MANIFEST.MF"

# Create manifest file
$ManifestContent = @"
Manifest-Version: 1.0
Implementation-Title: DoomClient
Implementation-Version: 1.0.0
Implementation-Vendor: DoomClient Team
Implementation-Timestamp: $(Get-Date -Format 'yyyy-MM-ddTHH:mm:ssZ')
Created-By: Manual PowerShell Build Script

"@

Set-Content -Path $ManifestFile -Value $ManifestContent -Encoding ASCII

# Create JAR file using .NET ZipFile
[System.Reflection.Assembly]::LoadWithPartialName("System.IO.Compression.FileSystem") | Out-Null

if (Test-Path $JarFile) {
    Remove-Item $JarFile -Force
}

$ZipFile = [System.IO.Compression.ZipFile]::Open($JarFile, 'Create')

# Add all files from output directory
Get-ChildItem $OutDir -Recurse -File | ForEach-Object {
    $RelativePath = $_.FullName.Substring($OutDir.Length + 1)
    $Entry = $ZipFile.CreateEntry($RelativePath)
    $Stream = $Entry.Open()
    $FileStream = [System.IO.File]::OpenRead($_.FullName)
    $FileStream.CopyTo($Stream)
    $Stream.Close()
    $FileStream.Close()
}

$ZipFile.Dispose()

Write-Host "✓ JAR file created: $JarFile" -ForegroundColor Green
Write-Host "`n=== Build Complete ===" -ForegroundColor Cyan
Write-Host "JAR Location: $JarFile"
Write-Host "File Size: $((Get-Item $JarFile).Length / 1KB) KB"
Write-Host "Next steps:"
Write-Host "1. Copy the JAR file to: %appdata%\.minecraft\mods\"
Write-Host "2. Ensure you have Fabric Loader installed"
Write-Host "3. Launch Minecraft 1.21.11 with Fabric"

# Cleanup
@($TempDir) | ForEach-Object {
    if (Test-Path $_) {
        Remove-Item $_ -Recurse -Force
    }
}

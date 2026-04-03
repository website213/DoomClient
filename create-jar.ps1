# Create JAR file
$ProjectRoot = 'c:\Users\adama\Downloads\pysilon\DoomClient'
$BuildDir = Join-Path $ProjectRoot 'build'
$TempDir = Join-Path $BuildDir 'temp_jar'
$LibsDir = Join-Path $BuildDir 'libs'
$JarPath = Join-Path $LibsDir 'doomclient-1.0.0.jar'

# Clean up
if (Test-Path $TempDir) { Remove-Item $TempDir -Recurse -Force }
if (Test-Path $JarPath) { Remove-Item $JarPath -Force }

# Create directories
New-Item $TempDir -ItemType Directory -Force | Out-Null
New-Item (Join-Path $TempDir 'META-INF') -ItemType Directory -Force | Out-Null

# Copy metadata
Copy-Item (Join-Path $ProjectRoot 'src\main\resources\fabric.mod.json') (Join-Path $TempDir 'META-INF\') -Force

# Copy source code
Copy-Item (Join-Path $ProjectRoot 'src\main\java\com') (Join-Path $TempDir 'com') -Recurse -Force

# Create ZIP/JAR
Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::CreateFromDirectory($TempDir, $JarPath, 'Optimal', $true)

# Verify
if (Test-Path $JarPath) {
    $Size = (Get-Item $JarPath).Length
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "  ✓ JAR CREATED SUCCESSFULLY!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "Location: $JarPath" -ForegroundColor Cyan
    Write-Host "Size: $([Math]::Round($Size/1024, 2)) KB" -ForegroundColor Cyan
    Write-Host ""
} else {
    Write-Host "✗ JAR creation failed" -ForegroundColor Red
}

# Cleanup
if (Test-Path $TempDir) { Remove-Item $TempDir -Recurse -Force }

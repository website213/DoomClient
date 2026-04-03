$ProjectDir = Split-Path -Parent $MyInvocation.MyCommand.Path  
$SrcDir = Join-Path $ProjectDir "src\main\java"
$ResDir = Join-Path $ProjectDir "src\main\resources"
$OutDir = Join-Path $ProjectDir "build\classes"
$JarDir = Join-Path $ProjectDir "build\libs"

mkdir -Force $OutDir, $JarDir | Out-Null

Write-Host "Compiling Java files..."
$JavaFiles = Get-ChildItem $SrcDir -Recurse -Filter "*.java"
javac -d $OutDir -encoding UTF-8 $JavaFiles.FullName
if ($LASTEXITCODE -ne 0) { Write-Host "Compilation failed"; exit 1 }
Write-Host "Compilation complete!"

if (Test-Path $ResDir) {
    Copy-Item -Path "$ResDir\*" -Destination $OutDir -Recurse -Force
}

$JarFile = Join-Path $JarDir "DoomClient-1.0.0.jar"
Remove-Item -Force $JarFile -ErrorAction SilentlyContinue

Write-Host "Creating JAR file..."
[System.Reflection.Assembly]::LoadWithPartialName("System.IO.Compression.FileSystem") | Out-Null
$ZipFile = [System.IO.Compression.ZipFile]::Open($JarFile, 'Create')

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

Write-Host "Build complete! JAR: $JarFile"
Get-Item $JarFile | Select-Object -ExpandProperty Length | ForEach-Object { Write-Host "Size: $($_/1KB) KB" }

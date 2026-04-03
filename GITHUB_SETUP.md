# GitHub Setup Guide for DoomClient

## 🚀 Quick GitHub Setup (5 minutes)

### Step 1: Create GitHub Repository
1. Go to https://github.com/new
2. Enter Repository name: `DoomClient`
3. Choose: **Public** (so you can use Codespaces free tier)
4. Add description: "Complete Fabric Minecraft 1.21.11 client mod with 23 modules"
5. Click **Create repository**

You'll see a page with instructions. Follow the "...or push an existing repository from the command line" section.

---

## Step 2: Push from Command Line

Copy and run these commands in PowerShell in your project directory:

```powershell
cd "c:\Users\adama\Downloads\pysilon\DoomClient"

# Initialize git (if not already done)
git init

# Configure git  
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Add all files
git add .

# Create initial commit
git commit -m "Initial commit: Complete DoomClient mod for Minecraft 1.21.11"

# Add remote (replace USERNAME with your GitHub username)
git remote add origin https://github.com/USERNAME/DoomClient.git

# Rename branch to main
git branch -M main

# Push to GitHub
git push -u origin main
```

**Note**: You may be prompted to authenticate:
- If using HTTPS: Enter GitHub username and personal access token
- If using SSH: Ensure SSH key is configured

---

## Step 3: Use GitHub Codespaces (To Build)

Once pushed to GitHub:

1. Go to your GitHub repo: `https://github.com/YOUR_USERNAME/DoomClient`
2. Click **Code** button (green) → **Codespaces** tab
3. Click **Create codespace on main**
4. Wait for VS Code to load (30-60 seconds)
5. In the terminal that opens, run:
   ```bash
   gradle clean build
   ```
6. Wait for build to complete (2-5 minutes)
7. JAR will be at: `build/libs/doomclient-1.0.0.jar`

### Download JAR from Codespaces
1. In Codespaces left sidebar, click **Explorer**
2. Navigate to `build/libs/`
3. Right-click `doomclient-1.0.0.jar`
4. Select **Download**
5. Copy JAR to `%APPDATA%\.minecraft\mods\`

---

## 📋 What's in Your GitHub Repo

After pushing, your repository will have:

```
DoomClient/
├── src/main/java/          (44 Java source files)
├── src/main/resources/     (fabric.mod.json)
├── gradle/                 (Gradle wrapper)
├── build.gradle
├── gradle.properties
├── README.md               (Full documentation)
├── BUILD_GUIDE.md          (Build troubleshooting)
├── FINAL_INSTRUCTIONS.md   (Complete setup)
├── 00-START-HERE.md        (Quick reference)
├── LICENSE                 (MIT)
└── .gitignore
```

---

## 🔑 Personal Access Token (If Needed)

If GitHub asks for authentication with HTTPS:

1. Go to: https://github.com/settings/tokens/new
2. Select scopes: `repo` (full control of private repositories)
3. Click **Generate token**
4. Copy the token (you'll only see it once)
5. Use `https_token` when Git asks for password

Alternatively, use SSH:
- Set up SSH key if you haven't: https://github.com/settings/keys
- Use SSH URL: `git@github.com:USERNAME/DoomClient.git`

---

## ✅ Verify Push Success

After running `git push -u origin main`, you should see:
```
Enumerating objects: ...
Counting objects: ...
Compressing objects: ...
Writing objects: ...
Total ... (delta ...), reused ...
...
 * [new branch]          main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

Visit your repo to confirm:
- All files are there
- Docs readable on GitHub
- Ready for Codespaces

---

## 🎯 Next: Build in Codespaces

1. **Repo on GitHub** ✅ (What you're doing now)
2. **Open Codespaces** ← Next
3. **Run `gradle clean build`** ← Builds JAR
4. **Download JAR** ← Get the mod
5. **Install to mods folder** ← Play the mod!

---

## 🆘 Troubleshooting

### "fatal: not a git repository"
```powershell
cd "c:\Users\adama\Downloads\pysilon\DoomClient"
git init
```

### "Permission denied (publickey)"
- Use HTTPS instead of SSH
- Or set up SSH key: https://github.com/settings/keys

### "rejected ... (fetch first)"
```powershell
git pull origin main
git push origin main
```

### Files not showing on GitHub after push
- Verify push completed (look for "main -> main")
- Refresh GitHub page
- Check `.gitignore` isn't hiding files

---

## 📚 Resources

- GitHub: https://github.com
- Codespaces Docs: https://docs.github.com/en/codespaces
- Git Guide: https://git-scm.com/book/en/v2

---

## Command Reference

| Command | Purpose |
|---------|---------|
| `git init` | Initialize git repo |
| `git add .` | Stage all files |
| `git commit -m "msg"` | Create commit |
| `git remote add origin <url>` | Add GitHub remote |
| `git push -u origin main` | Push to GitHub |
| `git status` | Check git status |
| `git log` | View commit history |

---

**Ready to push? Run the commands from Step 2 above!** 🚀

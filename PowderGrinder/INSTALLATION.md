# Powder Grinder Mod - Complete Installation Guide

## System Requirements

- **Minecraft Version**: 1.21.1
- **Java Version**: 21 or higher
- **RAM**: 2GB minimum (4GB+ recommended)
- **Mod Loader**: Fabric

## Step-by-Step Installation

### Phase 1: Java Setup

1. **Check Java Version**
   ```bash
   java -version
   ```
   You should see "21" or higher in the output.

2. **Install Java 21** (if needed)
   - **Windows**: Download from https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
   - **macOS**: `brew install openjdk@21`
   - **Linux**: `sudo apt install openjdk-21-jdk`

### Phase 2: Fabric Installation

1. **Download Fabric Installer**
   - Visit https://fabricmc.net/use/
   - Click "Download" for the installer

2. **Run the Installer**
   - **Windows**: Double-click `fabric-installer.exe`
   - **macOS/Linux**: `java -jar fabric-installer.jar`

3. **Configure Installer**
   - Select Minecraft version: **1.21.1**
   - Loader version: **Latest** (0.16.9+)
   - Installer version: **Latest**
   - Installation directory: **Default** (auto-detects)
   - Check: ✓ "Create profile"

4. **Click "Install"**
   - Wait for completion
   - You'll see "Installation complete!"

### Phase 3: Mod Building

1. **Extract the Mod Files**
   - Extract the `PowderGrinder.zip` folder
   - Navigate into it: `cd PowderGrinder`

2. **Build the Mod**
   ```bash
   # Windows
   gradlew.bat build
   
   # macOS/Linux
   ./gradlew build
   ```
   
   This will take 2-5 minutes on first run.
   Wait for the message: **"BUILD SUCCESSFUL"**

3. **Locate the JAR**
   The compiled mod will be at:
   ```
   PowderGrinder/build/libs/powder-grinder-1.0.0.jar
   ```

### Phase 4: Installation

1. **Find Your Mods Folder**
   - **Windows**: `%appdata%\.minecraft\mods`
   - **macOS**: `~/Library/Application Support/minecraft/mods`
   - **Linux**: `~/.minecraft/mods`

   If the `mods` folder doesn't exist, create it.

2. **Copy the JAR**
   - Copy `powder-grinder-1.0.0.jar`
   - Paste into the `mods` folder

3. **Launch Minecraft**
   - Open Minecraft Launcher
   - Select the **"Fabric 1.21.1"** profile
   - Click "Play"
   - Wait for the game to load

4. **Verify Installation**
   - Open the Mods menu (from title screen)
   - Look for "Powder Grinder" in the list
   - Check version: 1.0.0

## Configuration After Installation

### Keybind Setup (Optional)

1. Launch Minecraft
2. Go to: **Options → Controls**
3. Search for "Powder Grinder" category
4. Customize keybinds as desired

Default binds:
- **P**: Toggle Mod
- **M**: Toggle Mining
- **N**: Toggle Pathfinding
- **C**: Toggle Chest Detection
- **V**: Toggle Particles

### Initial Testing

1. **Create a Test World**
   - Superflat world recommended (easier to find blocks)
   - Copy the world to avoid losing progress

2. **Test Each Feature**
   ```
   P → Mod enabled (you'll see chat message)
   M → Auto mining enabled
   C → Chest detection enabled
   V → Particles enabled
   ```

3. **Place some stone blocks**
   - Find hard stone or stone blocks
   - Stand near them
   - The mod should automatically start mining

## Troubleshooting Installation

### Problem: "Failed to download dependencies"
**Solution:**
- Check internet connection
- Run: `./gradlew clean build`
- Delete `.gradle` folder and retry

### Problem: "Gradle not found"
**Solution:**
- Use the gradle wrapper: `./gradlew` (not `gradle`)
- Ensure gradlew is executable: `chmod +x gradlew`

### Problem: "BUILD FAILED" during compilation
**Solution:**
- Check Java version: `java -version` (must be 21+)
- Delete: `build/` and `.gradle/` folders
- Run: `./gradlew clean build`

### Problem: Mod doesn't appear in Minecraft
**Solution:**
1. Verify JAR is in mods folder
2. Check Minecraft version matches (1.21.1)
3. Verify Fabric is installed
4. Try: Delete mod, restart MC, reinstall

### Problem: Game crashes on startup
**Solution:**
1. Remove the mod JAR temporarily
2. Launch Minecraft to confirm it works
3. Check crash log in `logs/latest.log`
4. Ensure no mod conflicts

### Problem: Keybinds not working
**Solution:**
1. Go to Controls menu
2. Look for "Powder Grinder" category
3. Rebind keys if needed
4. Try different keybinds

## Updating the Mod

To update to a newer version:

1. Build the new version: `./gradlew build`
2. Delete old JAR: `mods/powder-grinder-1.0.0.jar`
3. Copy new JAR to mods folder
4. Restart Minecraft

## Uninstalling the Mod

1. Delete `powder-grinder-1.0.0.jar` from mods folder
2. Restart Minecraft
3. (Optional) Delete `.minecraft/powder_grinder/` config folder

## Advanced Setup

### Custom Minecraft Directory

If you use a custom launcher directory:

1. Locate your mods folder
2. Copy JAR to that location
3. Launch through that launcher

### Server Installation

For server-side testing (single-player):
- Same process as above
- Use single-player worlds only
- Don't use on public multiplayer servers

### Performance Optimization

If experiencing lag:

1. Disable particles: Press V
2. Reduce particle intensity in config
3. Increase pathfinding cooldown
4. Allocate more RAM to Java

## Getting Help

**Common Questions:**

Q: Can I use this on Hypixel?
A: Not recommended - check their ToS first

Q: Will I get banned?
A: No guarantees - use at own risk

Q: Can I modify the code?
A: Yes! The source is editable in src/main/java/

Q: How do I add custom blocks?
A: Edit AutoMiner.java's MINABLE_BLOCKS set

## Next Steps

After successful installation:

1. **Read QUICKSTART.md** - Quick overview of features
2. **Check README.md** - Full documentation
3. **Launch Minecraft** - Test the mod
4. **Customize settings** - Adjust to your preference
5. **Start grinding!** - Use it in your world

## Important Notes

⚠️ **This mod is powerful** - Use responsibly!
- It can mine blocks rapidly
- It bypasses normal game mechanics
- Check server rules before using
- Don't use on public multiplayer without permission

✅ **What's safe:**
- Single-player worlds
- Private servers with permission
- Testing worlds
- Worlds owned by the player

❌ **What's unsafe:**
- Public multiplayer servers (unless allowed)
- Servers with anti-cheat enabled
- Hypixel (check their policies)
- Servers you don't own

---

**Installation successful?** 
Start with the QUICKSTART.md file for basic usage! 🎮

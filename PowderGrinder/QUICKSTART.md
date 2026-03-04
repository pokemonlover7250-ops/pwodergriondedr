# Quick Start Guide - Powder Grinder Mod

## 5-Minute Setup

### Step 1: Install Fabric
1. Download Fabric Installer from https://fabricmc.net/
2. Run the installer (select Minecraft 1.21.1)
3. Click "Install"

### Step 2: Build the Mod
```bash
cd PowderGrinder
./gradlew build
```

### Step 3: Install JAR
- Copy `build/libs/powder-grinder-1.0.0.jar`
- Paste into `.minecraft/mods/`
- Launch with Fabric profile

## Quick Controls

| Key | Function |
|-----|----------|
| **P** | On/Off |
| **M** | Mining |
| **N** | Pathfinding |
| **C** | Chest Detector |
| **V** | Particles |

## First Run Checklist

- [ ] Fabric Loader installed
- [ ] Mod JAR in mods folder
- [ ] Running Minecraft 1.21.1
- [ ] Java 21+ installed
- [ ] Launched with Fabric profile

## Common Issues

**Mod won't load?**
- Verify Fabric is installed correctly
- Check you're using the right Minecraft version
- Update Java to version 21

**Mining not working?**
- Press M to enable auto mining
- Press P to enable mod
- Stand near hard stone blocks
- Check you're in the right area

**Can't find chests?**
- Press C to enable chest detection
- Get closer to chests (within 32 blocks)
- Look for particles around chests

## Performance Settings

For better FPS:
1. Press V to disable particles
2. Reduce particle intensity in config
3. Increase pathfinding cooldown

## What's It Do?

✅ **Auto Mines** - Finds and mines hard stone automatically
✅ **Smart Path** - Uses AI to move around obstacles
✅ **Finds Chests** - Detects and highlights nearby chests
✅ **Cool Effects** - Shows particles for visual feedback

## Safety Note

⚠️ Server Detection Risk - Use responsibly!

This is designed for single-player or trusted servers. Always check server rules before using automation mods.

## Need Help?

See the full README.md for:
- Detailed configuration options
- Troubleshooting guide
- Advanced customization
- Building from source

---

**Ready to grind?** Press P to toggle and M to start mining! 🔨

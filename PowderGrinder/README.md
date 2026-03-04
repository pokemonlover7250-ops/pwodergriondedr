# Powder Grinder Mod

A powerful Minecraft 1.21.11 Fabric mod for Hypixel Skyblock powder grinding automation. Features AI pathfinding, auto mining of hard stone, intelligent chest detection, and beautiful particle effects.

## Features

### 🔨 Auto Mining
- **Hard Stone Detection**: Automatically mines hard stone blocks in Hypixel Skyblock
- **Smart Block Selection**: Prioritizes nearest minable blocks
- **Auto-Rotation**: Automatically rotates player to face target blocks
- **Configurable Speed**: Adjust mining speed from 0.1x to 2.0x
- **Range Control**: Customizable mining distance (default 6 blocks)

### 🧭 AI Pathfinding
- **A* Pathfinding Algorithm**: Efficient path calculation to targets
- **Collision Avoidance**: Avoids lava, cliffs, and obstacles
- **Real-time Updates**: Dynamic pathfinding recalculation
- **Configurable Speed**: Adjust walking speed independently

### 📦 Chest Detection
- **Automatic Scanning**: Detects nearby chests within 32 blocks
- **Auto-Opening**: Optional automatic chest interaction
- **Chest Tracking**: Highlights and tracks all nearby chests
- **Efficient Scanning**: Cooldown-based detection to reduce lag

### ✨ Particle Effects
- **Mining Particles**: Visual feedback during block mining
- **Ore Highlighting**: Glow particles around detected ore
- **Chest Indicators**: Special particles around detected chests
- **Path Visualization**: Optional path particle trail
- **Intensity Control**: Adjust particle density from 0 to 2.0x

## Installation

### Prerequisites
- Java 21 or higher
- Minecraft 1.21.1
- Fabric Loader 0.16.9+

### Steps

1. **Download Fabric Installer**
   - Go to https://fabricmc.net/use/
   - Download the installer for Minecraft 1.21.1

2. **Install Fabric**
   - Run the installer and select "Install"
   - Ensure "Create profile" is checked

3. **Build the Mod**
   ```bash
   cd PowderGrinder
   ./gradlew build
   ```

4. **Install the Mod**
   - Copy `build/libs/powder-grinder-1.0.0.jar` to your `.minecraft/mods` folder
   - Launch Minecraft with the Fabric profile

## Keybinds

| Key | Action |
|-----|--------|
| **P** | Toggle Mod Enable/Disable |
| **M** | Toggle Auto Mining |
| **N** | Toggle Pathfinding |
| **C** | Toggle Chest Detection |
| **V** | Toggle Particles |

All keybinds can be remapped in Minecraft's Controls menu.

## Configuration

### Mining Settings
- **Mining Speed**: 0.1x - 2.0x (default: 1.0x)
- **Max Mining Distance**: 1-6 blocks (default: 6)
- **Auto Rotate**: Enable/disable automatic rotation

### Pathfinding Settings
- **Walk Speed**: 0.1x - 2.0x (default: 1.0x)
- **Update Frequency**: Configurable tick rate (default: 20 ticks = 1 second)
- **Avoid Lava**: Enable/disable lava avoidance

### Chest Detection
- **Scan Distance**: 1-64 blocks (default: 32)
- **Auto Open**: Enable/disable automatic opening
- **Detection Range**: Full 3D scanning

### Particle Settings
- **Intensity**: 0.0x - 2.0x (default: 1.0x)
- **Highlight Ore**: Toggle ore highlighting
- **Show Path**: Toggle path visualization

## How It Works

### Auto Mining
1. Scans the area for hard stone blocks within configured range
2. Selects the nearest valid target
3. Auto-rotates to face the block (if enabled)
4. Continuously damages the block until destruction
5. Automatically finds the next target

### Pathfinding
- Uses A* algorithm for optimal path calculation
- Considers player movement cost and distance to target
- Avoids hazardous blocks (lava, water traps)
- Updates path every 1 second or when blocked
- Supports diagonal movement

### Chest Detection
- Performs full 3D scan every 500ms
- Identifies all chest types (normal, trapped, ender)
- Tracks nearest chest for quick access
- Optional auto-interaction with chests

## Performance Tips

1. **Reduce Particle Intensity**: Lower particles for better FPS
2. **Increase Pathfinding Cooldown**: Reduce path recalculation frequency
3. **Decrease Chest Scan Distance**: Smaller area = faster scanning
4. **Disable Unused Features**: Toggle off features you don't need

## Troubleshooting

### Mod Not Loading
- Verify Fabric Loader is installed
- Check that you're using Minecraft 1.21.1
- Ensure Java 21+ is installed

### Not Mining Blocks
- Check that Auto Mining is enabled (press M)
- Verify blocks are Hard Stone (Skyblock specific)
- Ensure you're in the correct mining area

### Particles Not Showing
- Press V to toggle particles on
- Adjust particle intensity in config
- Check Minecraft particle settings aren't at minimum

### Lag Issues
- Disable particles (press V)
- Increase pathfinding cooldown
- Reduce chest scan distance
- Disable features you don't use

## Advanced Usage

### Custom Block Mining
Edit `AutoMiner.java` to add custom blocks:
```java
private static final Set<String> MINABLE_BLOCKS = new HashSet<>(Arrays.asList(
    "your_custom_block",
    "another_block"
));
```

### Pathfinding Customization
Modify `Pathfinder.java` to adjust:
- Search radius
- Movement cost calculations
- Block collision detection
- Path length limits

## Security & Legitimacy

⚠️ **Important**: This mod may violate Hypixel's Terms of Service. Use at your own risk!

The mod includes:
- No anticheat bypass mechanisms
- No inventory manipulation
- No unauthorized network packets
- Standard Minecraft block interactions only

Always check server rules before using automation mods.

## Building from Source

```bash
cd PowderGrinder

# Build the mod
./gradlew build

# Output: build/libs/powder-grinder-1.0.0.jar

# Build and run client
./gradlew runClient

# Build with source jar
./gradlew build --no-daemon
```

## File Structure

```
PowderGrinder/
├── src/main/java/com/skyblock/powder/
│   ├── PowderGrinderMod.java          # Main mod class
│   ├── automation/
│   │   ├── AutoMiner.java            # Block mining logic
│   │   └── Pathfinder.java           # A* pathfinding
│   ├── detection/
│   │   └── ChestDetector.java        # Chest finding
│   ├── particles/
│   │   └── ParticleEffects.java      # Visual effects
│   ├── ui/
│   │   └── GuiToggle.java            # Keybind handling
│   ├── config/
│   │   └── ModConfig.java            # Settings management
│   └── mixin/
│       ├── ClientTickMixin.java      # Tick events
│       └── GameRendererMixin.java    # Rendering
├── build.gradle                       # Build configuration
├── settings.gradle                    # Gradle settings
└── gradle/wrapper/                    # Gradle wrapper
```

## Future Improvements

- [ ] Configuration GUI menu
- [ ] Ore type detection (Iron, Gold, etc.)
- [ ] Multi-target pathfinding
- [ ] Inventory management
- [ ] Chest sorting automation
- [ ] Custom particle effects
- [ ] Statistics tracking
- [ ] Hot reload configuration

## Support & Issues

For issues or questions:
1. Check the Troubleshooting section
2. Review configuration settings
3. Ensure Minecraft/Fabric are up to date
4. Check Java version (21+)

## License

This mod is provided as-is. Use responsibly and check server terms of service.

## Credits

- Developed for Hypixel Skyblock powder grinding
- Uses Fabric Mod Loader
- Implements A* pathfinding algorithm
- Particle rendering via Minecraft API

---

**Remember**: This is a powerful automation tool. Use it responsibly and respect server rules!

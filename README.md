# Mambo Store - Java GUI Top Up

A desktop Java Swing application simulating top-up transactions for games, mobile prepaid credit (pulsa), and e-money. Features a modern dark/light violet interface, automatic fee calculations, and payment processing via QRIS and Virtual Accounts.

## Features

- **Game Top-Up**: Valorant, Honkai: Star Rail, Mobile Legends: Bang Bang, and Wuthering Waves (with automatic in-game currency rate conversion).
- **Prepaid Credit (Pulsa)**: Telkomsel, Axis, 3 (Tri), XL, and Smartfren.
- **E-Money**: GoPay, DANA, OVO, and ShopeePay.
- **Payment Methods**: Dynamic QRIS (powered by ZXing QR generator) and Virtual Accounts (BCA, Mandiri, BNI, BRI).
- **Validation**: Strict validation for nominal amounts, phone numbers, and game IDs.

## Screenshots

### Main Window
![Main Window](docs/screenshots/main-window.png)

### Game Top-Up Window
![Top Up Game Window](docs/screenshots/game-topup-window.png)

### Pulsa & E-Money Window
![Pulsa & E-Money Window](docs/screenshots/pulsa-emoney-window.png)

## Transaction Rules

| Category | Minimum Amount | Admin Fee |
| :--- | :--- | :--- |
| **Game** | None | Calculated based on game currency rates |
| **Pulsa** | Rp 5,000 | Rp 500 (+ Rp 500 per Rp 50,000 increment) |
| **E-Money** | Rp 10,000 | Rp 1,500 (+ Rp 1,500 per Rp 100,000 increment) |

## Project Structure

```text
Colej-GroupProject/
├── .vscode/                 # VS Code workspace settings & launch configurations
├── lib/                     # Bundled ZXing libraries (offline builds)
├── src/main/
│   ├── java/                # Application source code
│   └── resources/assets/    # UI images and banners
├── docs/screenshots/        # Project screenshot assets
├── build.gradle             # Gradle build configuration
├── pom.xml                  # Maven build configuration
└── README.md
```

## How to Run

### 1. Command Line

**Using Gradle Wrapper (Recommended):**
```bash
./gradlew run
```
*Windows:* `.\gradlew.bat run`

*Note for Linux Wayland users (Niri / Sway / Hyprland): `_JAVA_AWT_WM_NONREPARENTING=1` is automatically configured in `build.gradle`.*

**Using Apache Maven:**
```bash
mvn compile exec:java
```

**Direct Java (No Build Tool Required):**
```bash
mkdir -p build/classes
javac -d build/classes -cp "lib/*" src/main/java/*.java
cp -r src/main/resources/* build/classes/
java -cp "build/classes:lib/*" MainWindow
```

### 2. From IDEs (VS Code, IntelliJ IDEA, Eclipse, NetBeans)

- **VS Code**: Open project folder, open `src/main/java/MainWindow.java`, press `F5` or click **Run**.
- **IntelliJ / Eclipse / NetBeans**: Open or import project (automatically detected via Gradle/Maven), run `MainWindow.java`.

## Requirements

- Java Development Kit (JDK) 17 or higher

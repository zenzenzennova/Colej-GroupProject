# Mambo Store - Java GUI Top Up

Mambo Store adalah aplikasi Java Swing untuk simulasi top up game, pulsa, dan e-money. Project ini dibuat sebagai tugas GUI Java dengan background putih-violet yang bersih, banner visual besar di setiap halaman utama, validasi nominal, dan dialog pembayaran QRIS atau Virtual Account.

## Fitur

- Halaman utama untuk memilih layanan top up.
- Top up game untuk Valorant, Honkai: Star Rail, Mobile Legends: Bang Bang, dan Wuthering Waves.
- Top up pulsa untuk Telkomsel, Axis, 3 (Tri), XL, dan Smartfren.
- Top up e-money untuk GoPay, DANA, OVO, dan ShopeePay.
- Scroll list pada halaman Pulsa & E-Money agar pilihan produk tetap rapi.
- Validasi nominal dan perhitungan admin otomatis.
- Dialog pembayaran dengan pilihan QRIS atau Virtual Account.
- Generate QR Code pembayaran memakai library ZXing.

## Tampilan GUI Terbaru

Main Window dan Pulsa & E-Money Window memakai background polos light violet/white agar konten lebih rapi dan banner lebih menonjol. Top Up Game Window memakai theme cream, coral, dan biru yang mengikuti warna `bgGame.jpg`.

- Main Window memakai banner `src/assets/bannerMain.png`.
- Top Up Game Window memakai banner `src/assets/bgGame.jpg` dengan warna tombol dan panel yang selaras dengan banner.
- Pulsa & E-Money Window memakai banner `src/assets/bannerPulsa.png`.
- Area banner dibuat lebih besar supaya gambar terlihat lebih proporsional di setiap window.

## Screenshot

### Main Window

![Main Window](docs/screenshots/main-window.png)

### Top Up Game Window - bgGame Theme

![Top Up Game Window bgGame Theme](docs/screenshots/game-topup-window.png)

### Pulsa & E-Money Window

![Pulsa & E-Money Window](docs/screenshots/pulsa-emoney-window.png)

## Aturan Top Up

### Game

Nominal top up game dihitung menjadi estimasi mata uang game sesuai rate yang ada di program.

Produk game yang tersedia:

- Valorant
- Honkai: Star Rail
- Mobile Legends: Bang Bang
- Wuthering Waves

### Pulsa

Minimal top up pulsa adalah Rp 5.000.

Biaya admin pulsa:

- Admin awal Rp 500
- Tambahan Rp 500 untuk setiap kelipatan Rp 50.000

Produk pulsa yang tersedia:

- Pulsa Telkomsel
- Pulsa Axis
- Pulsa 3 (Tri)
- Pulsa XL
- Pulsa Smartfren

### E-Money

Minimal top up e-money adalah Rp 10.000.

Biaya admin e-money:

- Admin awal Rp 1.500
- Tambahan Rp 1.500 untuk setiap kelipatan Rp 100.000

Produk e-money yang tersedia:

- GoPay
- DANA
- OVO
- ShopeePay

## Pembayaran

Setelah data top up valid, aplikasi menampilkan ringkasan pesanan dan meminta nomor tujuan atau ID game. Pembayaran bisa dilakukan melalui:

- QRIS
- Virtual Account BCA, Mandiri, BNI, atau BRI

Setelah pembayaran dikonfirmasi, aplikasi menampilkan status pembayaran berhasil.

## Struktur Project

```text
src/
  MainWindow.java
  TopUpWindow.java
  PulsaWindow.java
  PaymentDialog.java
  FuturisticUI.java
  assets/
    bannerMain.png
    bannerPulsa.png
    bgGame.jpg
    bgGameViolet.png
docs/
  screenshots/
    main-window.png
    game-topup-window.png
    pulsa-emoney-window.png
dist/
  lib/
    core-3.5.2.jar
    javase-3.5.2.jar
```

## Cara Menjalankan

### Lewat NetBeans

1. Buka project ini di NetBeans.
2. Pastikan library ZXing dan AbsoluteLayout terbaca.
3. Jalankan project dari `MainWindow`.

### Lewat Terminal Windows

```powershell
javac -d build\classes -cp "dist\lib\javase-3.5.2.jar;dist\lib\core-3.5.2.jar" src\*.java
java -cp "build\classes;src;dist\lib\javase-3.5.2.jar;dist\lib\core-3.5.2.jar" MainWindow
```

## Teknologi

- Java Swing
- NetBeans GUI Form
- ZXing Core
- ZXing JavaSE

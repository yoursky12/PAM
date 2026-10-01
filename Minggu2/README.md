# News Feed Simulator

**Nama:** Frichintia Niken Gita Natasyah
**NIM:** 124140106
**Kelas:** Pengembangan Aplikasi Mobile RB

## Deskripsi

News Feed Simulator adalah aplikasi sederhana yang mensimulasikan feed berita menggunakan Kotlin dan Jetpack Compose, Berita baru akan muncul secara otomatis setiap 2 detik dan user dapat memilih kategori berita yang ingin ditampilkan

## Fitur

* **Simulasi Berita**
  Berita dibuat secara asynchronous menggunakan `Flow` dan `delay(2000)` sehingga berita baru muncul setiap 2 detik.

* **Filter Kategori**
  Berita dapat difilter berdasarkan kategori menggunakan `.filter {}`. Kategori yang tersedia yaitu Teknologi, Olahraga, Politik, dan Hiburan.

* **Transformasi Data**
  Data berita diubah menggunakan `.map {}` sebelum ditampilkan pada feed.

* **StateFlow Counter**
  `MutableStateFlow` digunakan untuk menyimpan jumlah berita yang sudah dibaca dan memperbarui tampilannya secara real-time.

* **Detail Berita**
  Saat berita dipilih, aplikasi mengambil detail berita secara asynchronous menggunakan `coroutineScope`, `async`, `Dispatchers.IO`, dan `delay(1000)`.

* **Jetpack Compose UI**
  Tampilan aplikasi dibuat menggunakan Jetpack Compose dengan `LazyColumn`, `Card`, `FilterChip`, dan `MaterialTheme`.

## Cara Menjalankan

Clone repository:

```bash
git clone https://github.com/yoursky12/NewsFeedSimulator_PAM2.git
```

Kemudian:

1. Buka project menggunakan Android Studio.
2. Pilih **Trust Project** jika muncul.
3. Tunggu proses **Gradle Sync** selesai.
4. Pastikan **Gradle JDK** menggunakan JDK 21.
5. Pilih emulator atau perangkat Android.
6. Jalankan aplikasi dengan tombol **Run ▶** atau tekan `Shift + F10`.

## Screenshot
<img width="179" height="349" alt="image" src="https://github.com/user-attachments/assets/f5303544-db6b-42ac-b79a-fda1e5d6ca0f" />

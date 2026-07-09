# ⚔️ Colony War — Java'da Nesne Yönelimli Benzetim

![Language](https://img.shields.io/badge/language-Java-blue.svg)
![JDK](https://img.shields.io/badge/JDK-17%2B-brightgreen.svg)
![Platform](https://img.shields.io/badge/platform-cross--platform-lightgrey.svg)
![Build](https://img.shields.io/badge/build-Eclipse%20%2F%20javac-orange.svg)

Kullanıcının girdiği sayılara göre otomatik "koloni"ler oluşturan, bu kolonileri
tur tur birbiriyle savaştırıp tek bir koloni kalana kadar simüle eden bir
konsol programı.

Bu proje, [C'de yazılmış olan sürümüyle](https://github.com/mlhonbasi/colony-war-oop-c)
aynı savaş simülasyonunun **Java'ya taşınmış** halidir. C sürümünde soyut sınıf,
kalıtım ve polimorfizm; struct'lar içine gömülen fonksiyon göstericileriyle
*taklit edilirken*, bu sürümde Java'nın **gerçek `abstract class` ve kalıtım**
mekanizması kullanılır — yani aynı tasarımın dilin kendi OOP desteğiyle
yazılmış hali.

## 📖 İçindekiler

- [Nasıl Çalışır — Oyun Kuralları](#-nasıl-çalışır--oyun-kuralları)
- [Örnek Çalıştırma](#-örnek-çalıştırma)
- [Mimari — Java'da OOP](#-mimari--javada-oop)
- [Proje Yapısı](#-proje-yapısı)
- [Gereksinimler](#-gereksinimler)
- [Derleme ve Çalıştırma](#-derleme-ve-çalıştırma)
- [Notlar](#-notlar)

---

## 🎮 Nasıl Çalışır — Oyun Kuralları

1. **Girdi:** Boşlukla ayrılmış sayılar girilir; her sayı bir koloninin
   başlangıç popülasyonudur (sayı adedi ve basamak sayısı serbesttir).
2. Her koloniye rastgele benzersiz bir **sembol**, rastgele bir **taktik**
   türü (A/B) ve rastgele bir **üretim** türü (A/B) atanır.
3. **Başlangıç yemek stoğu** = popülasyonun karesi.
4. **Her turda:**
   - Bütün canlı koloniler ikili ikili birbiriyle savaşır (`n` koloni varsa
     `n*(n-1)/2` savaş yapılır).
   - Savaş gücü, koloninin taktiğinin `Savas()` metodundan dönen **0–1000**
     arası bir değerdir (`ATaktik` popülasyona, `BTaktik` yemek stoğu +
     popülasyona göre hesaplar).
   - Güçlü olan kolonisi kazanır. Eşitlik durumunda önce popülasyonu fazla
     olan, o da eşitse kura ile belirlenen koloni kazanır.
   - Kaybeden koloni, güç farkına bağlı bir oranda **popülasyon** kaybeder;
     aynı oranda **yemek stoğu** kazanana geçer.
   - Savaş sonrasında popülasyonu veya yemek stoğu **0 veya altına** düşen
     koloni elenir (`canlilik = false`).
   - Sağ kalan koloniler, üretim tekniğinin `Uret()` metoduyla **0–10** arası
     yemek üretir. Popülasyon **%20** artar, yemek stoğu **(yeni popülasyon × 2)**
     kadar azalır.
5. **Tek koloni** kalınca oyun sona erer ve son tablo ekrana basılır.

## 🖥️ Örnek Çalıştırma

```text
Sayilari girin: 12 8 162 35 7 9

Tur Sayisi: 0
Koloni     Populasyon      Yemek Stogu          Kazanma         Kaybetme
  ø            12                144                0               0
  i             8                 64                0               0
  ¹           162              26244                0               0
  å            35               1225                0               0
  ~             7                 49                0               0
  Ú             9                 81                0               0
------------------------------------------------------------------------
...
Tur Sayisi: 9
Koloni     Populasyon      Yemek Stogu          Kazanma         Kaybetme
  ø           ---                ---              ---             ---
  i           ---                ---              ---             ---
  ¹           ---                ---              ---             ---
  å           ---                ---              ---             ---
  ~           ---                ---              ---             ---
  Ú            31              25951               15               8
------------------------------------------------------------------------
```

Elenen koloniler tabloda `---` ile gösterilir; son ayakta kalan koloni
kazananı belirler. (Koloni sembolleri genişletilmiş bir karakter kümesinden
seçilir; terminalin kod sayfasına göre farklı glif olarak görünebilirler —
gerçek bir Windows konsolunda C sürümündeki gibi düzgün karakterler olarak
gözükür.)

## 🏗️ Mimari — Java'da OOP

C sürümünün aksine burada gerçek dil desteğiyle OOP kurulur:

- **`Taktik` / `Uretim`** — `abstract class`. Sırasıyla `Savas(Koloni)` ve
  `Uret(Koloni)` adında birer soyut metot tanımlarlar, gövde içermezler.
- **`ATaktik` / `BTaktik`**, **`AUretim` / `BUretim`** — bu soyut sınıflardan
  `extends` ile **gerçekten kalıtım alan** somut sınıflar; `@Override` ile
  kendi hesaplama mantıklarını yazarlar. **Polimorfizm**, Java'nın kendi
  dinamik metot çağrısı (dynamic dispatch) üzerinden sağlanır — C sürümündeki
  gibi elle fonksiyon göstericisi atamaya gerek yoktur.
- **`Koloni`** — bir koloninin durumunu tutar: popülasyon, yemek stoğu,
  zafer/yenilgi sayısı, sembolü, canlılık durumu ve elindeki `Taktik` /
  `Uretim` referansları (composition).
- **`KoloniYonetim`** — sembol atama, tur sonu güncelleme (üretim, büyüme,
  tüketim), canlılık kontrolü ve "tek koloni kaldı mı" kontrolü gibi statik
  yardımcı metotları barındırır.
- **`Oyun`** — tur döngüsünü, ikili savaşları ve savaş sonuçlandırmayı
  yöneten orkestratör.
- **`SayiOkuma`** — kullanıcı girdisini (boşlukla ayrılmış string) `int[]`
  dizisine ayrıştırır.
- **`Test`** — `main()` giriş noktası: girdiyi okur, `Oyun` nesnesini
  oluşturur ve başlatır. Konsolu temizlerken işletim sistemine göre dallanır
  (Windows'ta `cmd /c cls`, diğerlerinde ANSI escape kodu) — bu yüzden C
  sürümünün aksine **yalnızca Windows'a bağımlı değildir.**

## 📂 Proje Yapısı

```
colony-war-java/
├── assignment.pdf              # Proje metni (Türkçe) — kurallar
├── .classpath / .project       # Eclipse proje yapılandırması (JavaSE-17)
├── src/                        # Kaynak dosyaları (.java)
│   ├── Paket/
│   │   ├── Test.java           # main() giriş noktası
│   │   ├── Oyun.java           # tur döngüsü, savaş orkestrasyonu
│   │   └── SayiOkuma.java      # girdi ayrıştırma
│   ├── Koloni/
│   │   ├── Koloni.java         # koloni durumu (popülasyon, yemek stoğu, sembol...)
│   │   └── KoloniYonetim.java  # sembol atama, tur güncellemesi, canlılık kontrolü
│   ├── Taktik/
│   │   ├── Taktik.java         # abstract class — Savas(Koloni) imzası
│   │   ├── ATaktik.java        # somut taktik türü A
│   │   └── BTaktik.java        # somut taktik türü B
│   └── Uretim/
│       ├── Uretim.java         # abstract class — Uret(Koloni) imzası
│       ├── AUretim.java        # somut üretim türü A
│       └── BUretim.java        # somut üretim türü B
├── bin/                        # Derlenmiş .class dosyaları (git'e dahil)
└── dist/
    └── Program.jar             # Çalıştırılabilir jar (Main-Class: Paket.Test)
```

## ⚙️ Gereksinimler

- **JDK 17 veya üzeri** (proje JavaSE-17 hedefiyle yapılandırılmış; JDK 21 ile
  de sorunsuz çalışır).
- İsteğe bağlı: **Eclipse IDE** (proje `.classpath` / `.project` dosyalarıyla
  doğrudan içe aktarılabilir).

## ▶️ Derleme ve Çalıştırma

En hızlısı, hazır derlenmiş jar'ı çalıştırmak:

```sh
java -jar dist/Program.jar
```

Kaynak koddan derleyip çalıştırmak için:

```sh
javac -d bin -sourcepath src src/Paket/Test.java
java -cp bin Paket.Test
```

Eclipse ile: projeyi *File → Import → Existing Projects into Workspace* ile
aç, `Test.java` üzerinde *Run As → Java Application* de.

## 📝 Notlar

- Denge/oran sabitleri (yemek stoğu formülü, %20 büyüme, savaş/üretim
  aralıkları vb.) ödev gereği doğrudan kaynak kodun içine gömülüdür; dışarıdan
  ayarlanabilir bir veri dosyası yoktur.
- Her çalıştırmada koloni sembolleri ile taktik/üretim türü seçimi (A/B)
  rastgeledir; bu yüzden aynı girdiyle bile her çalıştırma farklı sonuçlanır.
- Kuralların tam ve resmi tanımı için [`assignment.pdf`](./assignment.pdf)
  dosyasına bakınız.

---

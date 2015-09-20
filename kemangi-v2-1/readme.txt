cara pakai:
1. Letakkan file kamus.txt, stopword.txt, substitusi.txt, dan stopchar.txt di folder yang sama dengan kemangi.exe
2. Jalankan kemangi.exe
3. Masukkan informasi sesuai perintah. Untuk nama file, masukkan dengan lengkap (meliputi .txt dibelakangnya). Pastikan berada di folder yang sama dengan kemangi.exe. Tekan enter untuk setiap informasi yang dimasukkan.
4. Proses akan dijalankan dan berakhir dengan dihasilkannya berkas hasil.txt

urutan pengerjaan:
1. Tokenisasi
2. Lakukan substitusi
3. Hapus semua kata dengan awalan @ (mention), # (hashtag), dan http (URL), jika diperlukan
4. Ubah semua karakter yang muncul pada stopchar.txt menjadi spasi (jika berurutan, hanya menjadi 1 spasi. Contoh: "..." => " ")
5. Hapus semua kata yang muncul pada stopword.txt
6. Stemming
7. Hapus semua kata dengan frekuensi kurang dari atau lebih dari yang dibatasi

output:
1. hasil.txt => hasil pemrosesan
2. frekuensi_kata.txt => pasangan (kata, frekuensi) untuk setiap kata yang ada

Bila ada error, hubungi Gozali di will.gozali@gmail.com

============================================================
Format kamus.txt:
satu kata per baris, tanpa spasi, dan semua huruf kecil
banyaknya baris tidak dibatasi

Contoh:
aku
akua
bandrek
cumi

============================================================
Format stoplist.txt:
satu frase per baris, tanpa spasi, dan semua huruf kecil
banyaknya baris tidak dibatasi

Contoh:
yang
yg
yng
yank
dan
and
&
+
&amp;
!
wow
atau
ato
atw
4t4u
ataw
ke mana
riset ui

============================================================
Format stopchar.txt:
satu karakter per baris, tanpa spasi, dan semua huruf kecil
banyaknya baris tidak dibatasi

Contoh:
.
,
!
~
?

============================================================
Format substitusi.txt:
dua frase per baris, dipisahkan karakter '~' (asal~tujuan).
Semua huruf kecil dan boleh mengandung 1 spasi sebagai pemisah kata.

Contoh:
mkn~makan
gw~gua
mr baso~mister bakso
kpkp mlm~kupu kupu malam

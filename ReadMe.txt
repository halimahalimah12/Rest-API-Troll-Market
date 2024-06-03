Terimaksih sudah berkunjung dan mendowload/clone github ini. 
Semoga bermanfaat. ^_^
.
.
Informasi sedikit, ini merupakan Rest API dari Aplikasi web yang saya buat yaitu TrollMarket. 
Rest API TrollMarket ini merupakan Case kecil bagi pemula dalam mengaplikasikan Rest API.
anda juga bisa mendowload Aplikasi webnya, berikut linknya:
https://github.com/halimahalimah12/TrollMarket 
Jangan lupa baca file Readmenya ya.
btw Rest API ini menggunakan JWT (JSON Web Token) untuk auhtorization, dan authentication.
jadi didalam rest ini ada beberapa menu yang hanya bisa diakses oleh user tertentu saja.
.
Sebelum menjalankan Rest API ada baiknya membuat database terlebih dahulu
di xammp atau MS.SQL server atau web server yang lain. Disamakan ya nama databasenya anda dengan nama
database yang aku kasih. supaya mudah mengikutinya.
.
Nah didalam git ini ada file TrollMarket2.bak itu merupakan database yang aku gunakan ya
anda bisa melakukan import database ke dalam web server yang di punyai.
Anda bisa melakukan testnya di postman atau pakai swagger atau aplikasi lainnya. 
.
.
Ada 3 role yaitu: admin, seller, dan buyer.
user bisa memiliki lebih dari satu role.
Didalam database ada beberapa username yang bisa diakses 
atau bisa create akun seller atau buyer di halaman registrasi.
Untuk seller yang bisa diakses:
username: seller
password: seller123
untuk buyer:
username: buyer
password: buyer123
untuk yang multi role (seller,buyer):
username: halimah
password: halimah123
untuk admin:
username: admin
password: admin123
.
.
untuk nomor port server ini : 8081
contoh: http://localhost:8081/api/v1/cart
.
Selamat Belajar ^_^


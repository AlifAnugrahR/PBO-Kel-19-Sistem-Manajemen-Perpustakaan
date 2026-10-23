package com.mycompany.perpustakaankampus;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Perpustakaan perpus = new Perpustakaan();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println();
            System.out.println("===== SISTEM PERPUSTAKAAN KAMPUS =====");
            System.out.println("1. Kategori Buku (Array 1D)");
            System.out.println("2. Statistik Peminjaman (Array 2D)");
            System.out.println("3. Lokasi Buku (Array 3D)");
            System.out.println("4. Kelola Buku (ArrayList)");
            System.out.println("5. Peminjaman & Pengembalian");
            System.out.println("6. Riwayat Transaksi (Stack)");
            System.out.println("7. Antrean Peminjaman (Queue)");
            System.out.println("0. Keluar");
            pilihan = bacaAngka("Pilih menu: ", 0, 7);
            System.out.println();

            switch (pilihan) {
                case 1:
                    perpus.tampilkanKategori();
                    break;
                case 2:
                    perpus.tampilkanPeminjaman();
                    break;
                case 3:
                    menuLokasi();
                    break;
                case 4:
                    menuBuku();
                    break;
                case 5:
                    menuPeminjaman();
                    break;
                case 6:
                    menuRiwayat();
                    break;
                case 7:
                    menuAntrean();
                    break;
                case 0:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
            }
        } while (pilihan != 0);
    }

    private static int bacaAngka(String pesan, int min, int max) {
        while (true) {
            System.out.print(pesan);
            String input = sc.nextLine().trim();
            try {
                int angka = Integer.parseInt(input);
                if (angka >= min && angka <= max) {
                    return angka;
                }
                System.out.println("Angka harus antara " + min + " dan " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private static String bacaTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input tidak boleh kosong.");
        }
    }

    private static void menuLokasi() {
        int pilihan;
        do {
            System.out.println();
            System.out.println("--- Menu Lokasi Buku (Array 3D) ---");
            System.out.println("1. Tampilkan peta lokasi");
            System.out.println("2. Cari lokasi buku");
            System.out.println("3. Pindahkan buku");
            System.out.println("0. Kembali ke menu utama");
            pilihan = bacaAngka("Pilih: ", 0, 3);

            if (pilihan == 1) {
                perpus.tampilkanPetaLokasi();
            } else if (pilihan == 2) {
                String kode = bacaTeks("Kode buku: ");
                perpus.tampilkanLokasiBuku(kode);
            } else if (pilihan == 3) {
                String kode = bacaTeks("Kode buku: ");
                System.out.println("Masukkan lokasi tujuan:");
                int lantai = bacaAngka("Lantai (1-" + Perpustakaan.JUMLAH_LANTAI + "): ", 1, Perpustakaan.JUMLAH_LANTAI);
                int rak = bacaAngka("Rak (1-" + Perpustakaan.JUMLAH_RAK + "): ", 1, Perpustakaan.JUMLAH_RAK);
                int posisi = bacaAngka("Posisi (1-" + Perpustakaan.JUMLAH_POSISI + "): ", 1, Perpustakaan.JUMLAH_POSISI);
                perpus.pindahBuku(kode, lantai, rak, posisi);
            }
        } while (pilihan != 0);
    }

    private static void menuBuku() {
        int pilihan;
        do {
            System.out.println();
            System.out.println("--- Menu Kelola Buku (ArrayList) ---");
            System.out.println("1. Tambah buku");
            System.out.println("2. Tampilkan daftar buku");
            System.out.println("3. Hapus buku");
            System.out.println("0. Kembali ke menu utama");
            pilihan = bacaAngka("Pilih: ", 0, 3);

            if (pilihan == 1) {
                tambahBuku();
            } else if (pilihan == 2) {
                perpus.tampilkanBuku();
            } else if (pilihan == 3) {
                String kode = bacaTeks("Kode buku yang dihapus: ");
                perpus.hapusBuku(kode);
            }
        } while (pilihan != 0);
    }

    private static void tambahBuku() {
        String judul = bacaTeks("Judul buku: ");
        perpus.tampilkanKategori();
        int kat = bacaAngka("Pilih kategori (1-" + perpus.getJumlahKategori() + "): ", 1, perpus.getJumlahKategori());

        System.out.println("Tentukan lokasi penyimpanan:");
        int lantai = 0;
        int rak = 0;
        int posisi = 0;
        boolean kosong = false;
        while (!kosong) {
            lantai = bacaAngka("Lantai (1-" + Perpustakaan.JUMLAH_LANTAI + "): ", 1, Perpustakaan.JUMLAH_LANTAI);
            rak = bacaAngka("Rak (1-" + Perpustakaan.JUMLAH_RAK + "): ", 1, Perpustakaan.JUMLAH_RAK);
            posisi = bacaAngka("Posisi (1-" + Perpustakaan.JUMLAH_POSISI + "): ", 1, Perpustakaan.JUMLAH_POSISI);
            kosong = perpus.lokasiKosong(lantai, rak, posisi);
            if (!kosong) {
                System.out.println("Lokasi sudah terisi, pilih lokasi lain.");
            }
        }
        Buku b = perpus.tambahBuku(judul, kat - 1, lantai, rak, posisi);
        System.out.println("Buku berhasil ditambahkan dengan kode " + b.getKode() + ".");
    }

    private static void menuPeminjaman() {
        int pilihan;
        do {
            System.out.println();
            System.out.println("--- Menu Peminjaman & Pengembalian ---");
            System.out.println("1. Pinjam buku");
            System.out.println("2. Kembalikan buku");
            System.out.println("3. Tampilkan daftar buku");
            System.out.println("0. Kembali ke menu utama");
            pilihan = bacaAngka("Pilih: ", 0, 3);

            if (pilihan == 1) {
                String kode = bacaTeks("Kode buku: ");
                String nama = bacaTeks("Nama mahasiswa: ");
                System.out.println("Hari peminjaman:");
                perpus.tampilkanHari();
                int hari = bacaAngka("Pilih hari (1-7): ", 1, 7);
                perpus.pinjamBuku(kode, nama, hari - 1);
            } else if (pilihan == 2) {
                String kode = bacaTeks("Kode buku: ");
                perpus.kembalikanBuku(kode);
            } else if (pilihan == 3) {
                perpus.tampilkanBuku();
            }
        } while (pilihan != 0);
    }

    private static void menuRiwayat() {
        int pilihan;
        do {
            System.out.println();
            System.out.println("--- Menu Riwayat Transaksi (Stack) ---");
            System.out.println("1. Lihat transaksi terakhir");
            System.out.println("2. Tampilkan semua riwayat");
            System.out.println("3. Batalkan transaksi terakhir");
            System.out.println("0. Kembali ke menu utama");
            pilihan = bacaAngka("Pilih: ", 0, 3);

            if (pilihan == 1) {
                perpus.lihatTransaksiTerakhir();
            } else if (pilihan == 2) {
                perpus.tampilkanRiwayat();
            } else if (pilihan == 3) {
                perpus.batalkanTransaksi();
            }
        } while (pilihan != 0);
    }

    private static void menuAntrean() {
        int pilihan;
        do {
            System.out.println();
            System.out.println("--- Menu Antrean Peminjaman (Queue) ---");
            System.out.println("1. Tambah mahasiswa ke antrean");
            System.out.println("2. Tampilkan antrean");
            System.out.println("3. Proses mahasiswa terdepan");
            System.out.println("0. Kembali ke menu utama");
            pilihan = bacaAngka("Pilih: ", 0, 3);

            if (pilihan == 1) {
                String nama = bacaTeks("Nama mahasiswa: ");
                perpus.tambahAntrean(nama);
            } else if (pilihan == 2) {
                perpus.tampilkanAntrean();
            } else if (pilihan == 3) {
                prosesAntrean();
            }
        } while (pilihan != 0);
    }

    private static void prosesAntrean() {
        String depan = perpus.lihatDepanAntrean();
        if (depan == null) {
            System.out.println("Antrean kosong.");
            return;
        }
        System.out.println("Mahasiswa terdepan: " + depan);
        String kode = bacaTeks("Kode buku yang dipinjam: ");
        System.out.println("Hari peminjaman:");
        perpus.tampilkanHari();
        int hari = bacaAngka("Pilih hari (1-7): ", 1, 7);
        boolean berhasil = perpus.prosesAntrean(kode, hari - 1);
        if (berhasil) {
            System.out.println(depan + " sudah diproses dan keluar dari antrean.");
        } else {
            System.out.println(depan + " tetap berada di antrean.");
        }yuo
    }
}

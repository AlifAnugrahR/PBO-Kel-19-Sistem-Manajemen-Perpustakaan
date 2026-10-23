package com.mycompany.perpustakaankampus;

public class Transaksi {
    private String jenis;        // TAMBAH, HAPUS, PINJAM, KEMBALI, atau PINDAH
    private Buku buku;           // buku yang terkena transaksi
    private int indeks;          // posisi buku di ArrayList (dipakai untuk membatalkan HAPUS)
    private String mahasiswa;    // nama peminjam (untuk PINJAM)
    private int hari;            // indeks hari peminjaman 0-6 (untuk PINJAM)
    private int lantaiLama;      // lokasi lama (untuk PINDAH)
    private int rakLama;
    private int posisiLama;
    private String keterangan;   // teks tambahan yang ditampilkan di riwayat

    public Transaksi(String jenis, Buku buku) {
        this.jenis = jenis;
        this.buku = buku;
        this.indeks = -1;
        this.mahasiswa = "-";
        this.hari = -1;
        this.keterangan = "";
    }

    public String getJenis() {
        return jenis;
    }

    public Buku getBuku() {
        return buku;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    public String getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(String mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public void setLokasiLama(int lantai, int rak, int posisi) {
        this.lantaiLama = lantai;
        this.rakLama = rak;
        this.posisiLama = posisi;
    }

    public int getLantaiLama() {
        return lantaiLama;
    }

    public int getRakLama() {
        return rakLama;
    }

    public int getPosisiLama() {
        return posisiLama;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    // Teks satu baris yang tampil di riwayat transaksi
    public String toString() {
        String teks = jenis + " | " + buku.getKode() + " - " + buku.getJudul();
        if (!keterangan.isEmpty()) {
            teks = teks + " | " + keterangan;
        }
        return teks;
    }
}
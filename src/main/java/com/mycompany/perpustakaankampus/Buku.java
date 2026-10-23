package com.mycompany.perpustakaankampus;

public class Buku {
    private String kode;
    private String judul;
    private int kategori;  
    private boolean tersedia; 
    private int lantai;         
    private int rak;           
    private int posisi;       

    public Buku(String kode, String judul, int kategori, int lantai, int rak, int posisi) {
        this.kode = kode;
        this.judul = judul;
        this.kategori = kategori;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
        this.tersedia = true; 
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public int getKategori() {
        return kategori;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public int getLantai() {
        return lantai;
    }

    public int getRak() {
        return rak;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setLokasi(int lantai, int rak, int posisi) {
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
    }
}
package com.a7c.reportsa7c.TeknisiActivity;

public class Teknisi {

    String nama_teknisi;
    String nama_customer;
    String alamat_customer;
    String telpon_customer;
    String jenis_pekerjaan;
    String jumlah_PC;
    String tanggal_dikerjakan;
    String total_biaya;

    public Teknisi(){
    }

    public Teknisi(String nama_teknisi, String nama_customer, String alamat_customer, String telpon_customer, String jenis_pekerjaan, String jumlah_PC, String tanggal_dikerjakan, String total_biaya) {
        this.nama_teknisi = nama_teknisi;
        this.nama_customer = nama_customer;
        this.alamat_customer = alamat_customer;
        this.telpon_customer = telpon_customer;
        this.jenis_pekerjaan = jenis_pekerjaan;
        this.jumlah_PC = jumlah_PC;
        this.tanggal_dikerjakan = tanggal_dikerjakan;
        this.total_biaya = total_biaya;
    }

    public String getNama_teknisi() {
        return nama_teknisi;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getAlamat_customer() {
        return alamat_customer;
    }

    public String getTelpon_customer() {
        return telpon_customer;
    }

    public String getJenis_pekerjaan() {
        return jenis_pekerjaan;
    }

    public String getJumlah_PC() {
        return jumlah_PC;
    }

    public String getTanggal_dikerjakan() {
        return tanggal_dikerjakan;
    }

    public String getTotal_biaya() {
        return total_biaya;
    }
}



public class Transaksi implements java.io.Serializable {
    String NoRekTransaksi, jenisTransaksi, tanggalTransaksi;
    double jumlahTransaksi;

    Transaksi() {
    }

    Transaksi(String NoRekTransaksi, String jenisTransaksi, String tanggalTransaksi, double jumlahTransaksi) {
        this.NoRekTransaksi = NoRekTransaksi;
        this.jenisTransaksi = jenisTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.jumlahTransaksi = jumlahTransaksi;
    }
    public String getNoRekTransaksi() {
        return NoRekTransaksi;
    }
    public void setNoRekTransaksi(String NoRekTransaksi) {
        this.NoRekTransaksi = NoRekTransaksi;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }
    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }
    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public double getJumlahTransaksi() {
        return jumlahTransaksi;
    }
    public void setJumlahTransaksi(double jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}

public class Nasabah implements java.io.Serializable {
    String Nama, Alamat, NoRekening, PIN;
    double Saldo;

    Nasabah() {
    }

    Nasabah(String Nama, String NoRekening, String PIN, String Alamat, double Saldo) {
        this.Nama = Nama;
        this.NoRekening = NoRekening;
        this.PIN = PIN;
        this.Alamat = Alamat;
        this.Saldo = Saldo;
    }

    public String getNama() {
        return Nama;
    }
    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getNoRekening() {
        return NoRekening;
    }
    public void setNoRekening(String NoRekening) {
        this.NoRekening = NoRekening;
    }

    public String getPIN() {
        return PIN;
    }
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getAlamat() {
        return Alamat;
    }
    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public double getSaldo() {
        return Saldo;
    }
    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }
}
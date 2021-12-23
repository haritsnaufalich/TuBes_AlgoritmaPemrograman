import java.io.*;
import java.util.*;

public class MainATM {
    Scanner scInput = new Scanner(System.in);
    int N;

     public String loginATM() {
        String noRekening, pin;
        boolean cek = false;
        Nasabah U = new Nasabah();

        System.out.print("\n[+]========== Login ATM ==========[+]\n");
        System.out.print("\nNo Rekening : ");
        noRekening = scInput.nextLine();
        System.out.print("PIN : ");
        pin = scInput.nextLine();
        ObjectInputStream In = null;

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/User.dat"));
            Object objInput = In.readObject();

            try {
                while (!cek) {
                    U = (Nasabah) objInput;
                    if (noRekening.equals(U.getNoRekening()) && pin.equals(U.getPIN())) {
                        System.out.print("\nSelamat Datang " + U.getNama() + "\n");
                        cek = true;
                    } else {
                        objInput = In.readObject();
                    }
                }
            } catch (EOFException e) {
                System.out.print("");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Class Not Found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }

        if (cek) {
            return U.getNoRekening();
        } else {
            return "";
        }
    }

    public int menuATM() {
        System.out.print("\n[+]========== Menu ==========[+]\n\n");
        System.out.println("1. Cek Saldo");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Mutasi Rekening");
        System.out.println("5. Exit");
        System.out.print("\nPilih Menu : ");
        return scInput.nextInt();
    }

    public void cekSaldo(String NoRek) {
        Nasabah U = new Nasabah();
        ObjectInputStream In = null;

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/User.dat"));
            Object objInput = In.readObject();

            try {
                while (true) {
                    U = (Nasabah) objInput;
                    if (NoRek.equals(U.getNoRekening())) {
                        System.out.print("Saldo Anda : Rp. " + U.getSaldo() + "\n");
                        break;
                    } else {
                        objInput = In.readObject();
                    }
                }
            } catch (EOFException e) {
                System.out.print("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setorTunai(String NoRek) {
        Nasabah U = new Nasabah();
        Transaksi T = new Transaksi();
        String jenisTransaksi = "Setor Tunai";
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
        Date tanggalTransaksi = new Date();
        double jumlahTransaksi = 0;
        boolean found = false;
        ObjectInputStream In = null;
        ObjectOutputStream Out = null;

        try {
            In = new ObjectInputStream(new FileInputStream("src/User.dat"));
            Out = new ObjectOutputStream(new FileOutputStream("src/UserTem.dat" + ""));
            Object objInput = In.readObject();
            BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));

            try {
                while (true) {
                    U = (Nasabah) objInput;
                    if (NoRek.equals(U.getNoRekening())) {
                        System.out.print("\nSaldo Anda     : Rp. " + U.getSaldo() + "\n");
                        System.out.print("Jumlah Setoran : Rp. ");
                        jumlahTransaksi = scInput.nextDouble();
                        U = new Nasabah(U.getNama(), U.getNoRekening(), U.getPIN(), U.getAlamat(), U.getSaldo() + jumlahTransaksi);
                        Out.writeObject(U);
                        System.out.print("Saldo Baru     : Rp. " + U.getSaldo() + "\n");
                        found = true;
                    } else {
                        Out.writeObject(U);
                    }
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }

            Out.close();

            if (!found) {
                System.out.print("\nNo Rekening Tidak Ditemukan\n");
            } else {
                try {
                    In = new ObjectInputStream(new FileInputStream("src/UserTem.dat"));
                    Out = new ObjectOutputStream(new FileOutputStream("src/User.dat"));
                    objInput = In.readObject();

                    try {
                        while (true) {
                            U = (Nasabah) objInput;
                            Out.writeObject(U);
                            objInput = In.readObject();
                        }
                    } catch (EOFException e) {
                        System.out.print("");
                    }
                    Out.close();
                    System.out.print("Setor Tunai Berhasil\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("src/Transaksi"+NoRek+".dat");
        if (!file.exists()) {
            saveTransaksi(NoRek, jenisTransaksi, String.valueOf(tanggalTransaksi), jumlahTransaksi);
        } else {
            addRecordTransaksi(NoRek, jenisTransaksi, String.valueOf(tanggalTransaksi), jumlahTransaksi);
        }
    }

    public void tarikTunai(String NoRek) {
        Nasabah U = new Nasabah();
        Transaksi T = new Transaksi();
        String jenisTransaksi = "Tarik Tunai";
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
        Date tanggalTransaksi = new Date();
        double jumlahTransaksi = 0;
        boolean found = false;
        ObjectInputStream In = null;
        ObjectOutputStream Out = null;

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/User.dat"));
            Out = new ObjectOutputStream(new java.io.FileOutputStream("src/UserTem.dat" + ""));
            Object objInput = In.readObject();
            BufferedReader brInput = new BufferedReader(new java.io.InputStreamReader(System.in));

            try {
                while (true) {
                    U = (Nasabah) objInput;
                    if (NoRek.equals(U.getNoRekening())) {
                        System.out.print("\nSaldo Anda       : Rp. " + U.getSaldo() + "\n");
                        System.out.print("Jumlah Penarikan : Rp. ");
                        jumlahTransaksi = scInput.nextDouble();
                        if (jumlahTransaksi > U.getSaldo()) {
                            System.out.print("Saldo Anda Tidak Mencukupi");
                        } else {
                            U = new Nasabah(U.getNama(), U.getNoRekening(), U.getPIN(), U.getAlamat(), U.getSaldo() - jumlahTransaksi);
                            Out.writeObject(U);
                            System.out.print("Saldo Baru       : Rp. " + U.getSaldo() + "\n");
                            found = true;
                        }
                    } else {
                        Out.writeObject(U);
                    }
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }

            Out.close();

            if (!found) {
                System.out.print("");
            } else {
                try {
                    In = new ObjectInputStream(new java.io.FileInputStream("src/UserTem.dat"));
                    Out = new ObjectOutputStream(new java.io.FileOutputStream("src/User.dat"));
                    objInput = In.readObject();

                    try {
                        while (true) {
                            U = (Nasabah) objInput;
                            Out.writeObject(U);
                            objInput = In.readObject();
                        }
                    } catch (EOFException e) {
                        System.out.print("");
                    }
                    Out.close();
                    System.out.print("Tarik Tunai Berhasil\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("src/Transaksi"+NoRek+".dat");
        if (!file.exists()) {
            saveTransaksi(NoRek, jenisTransaksi, String.valueOf(tanggalTransaksi), jumlahTransaksi);
        } else {
            addRecordTransaksi(NoRek, jenisTransaksi, String.valueOf(tanggalTransaksi), jumlahTransaksi);
        }
    }

    public void saveTransaksi(String NoRekTransaksi, String jenisTransaksi, String tanggalTransaksi, double jumlahTransaksi) {
        Transaksi T = new Transaksi();

        ObjectOutputStream Out = null;

        try {
            Out = new ObjectOutputStream(new java.io.FileOutputStream("src/Transaksi"+NoRekTransaksi+".dat"));
            T = new Transaksi(NoRekTransaksi, jenisTransaksi, tanggalTransaksi, jumlahTransaksi);
            Out.writeObject(T);
            Out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewTransaksi(String fileName, String NoRek) {
        Transaksi T = new Transaksi();
        int TotalTransaksi = 0;

//        System.out.print("\n[+]========== Data Nasabah ==========[+]\n");
        ObjectInputStream In = null;

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/" + fileName));
            Object objInput = In.readObject();

            try {
                while (true) {
                    T = (Transaksi) objInput;
                    System.out.println("");
                    System.out.println("No Rekening : " + T.getNoRekTransaksi());
                    System.out.println("Jenis       : " + T.getJenisTransaksi());
                    System.out.println("Tanggal     : " + T.getTanggalTransaksi());
                    System.out.println("Jumlah      : " + T.getJumlahTransaksi());
                    TotalTransaksi++;
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }
            System.out.println("");
            System.out.println("Transaksi : " + TotalTransaksi);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRecordTransaksi(String NoRekTransaksi, String jenisTransaksi, String tanggalTransaksi, double jumlahTransaksi) {
        Transaksi T = new Transaksi();

        ObjectInputStream In = null;
        ObjectOutputStream Out = null;

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/Transaksi"+NoRekTransaksi+".dat"));
            Out = new ObjectOutputStream(new java.io.FileOutputStream("src/Transaksi"+NoRekTransaksi+"Temp.dat" + ""));
            Object objInput = In.readObject();
            BufferedReader brInput = new BufferedReader(new java.io.InputStreamReader(System.in));

            try {
                while (true) {
                    T = (Transaksi) objInput;
                    Out.writeObject(T);
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }

            T = new Transaksi(NoRekTransaksi, jenisTransaksi, tanggalTransaksi, jumlahTransaksi);
            Out.writeObject(T);
            Out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("");

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/Transaksi"+NoRekTransaksi+"Temp.dat"));
            Out = new ObjectOutputStream(new java.io.FileOutputStream("src/Transaksi"+NoRekTransaksi+".dat"));
            Object objInput = In.readObject();

            try {
                while (true) {
                    T = (Transaksi) objInput;
                    Out.writeObject(T);
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }
            Out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainATM MA = new MainATM();
        int i = 0;
        String NoRek = "";
        while (i < 3 && NoRek.equals("")) {
            NoRek = MA.loginATM();
            i++;
        }

        if (!NoRek.equals("")) {
            int Pilihan = MA.menuATM();
            while (Pilihan != 0) {
                switch (Pilihan) {
                    case 1 -> MA.cekSaldo(NoRek);
                    case 2 -> MA.setorTunai(NoRek);
                    case 3 -> MA.tarikTunai(NoRek);
                    case 4 -> MA.viewTransaksi("Transaksi"+NoRek+".dat", NoRek);
                    case 5 -> System.exit(0);
                    default -> System.out.print("\nPilihan Tidak Tersedia\n");
                }
                Pilihan = MA.menuATM();
            }
        }
    }
}
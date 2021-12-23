import java.io.*;
import java.util.Scanner;

public class NasabahMain {
    Scanner scInput = new Scanner(System.in);
    int N;

    public void menuNasabah() {
        char Quit = 'y';
        while (Quit != 'n') {
            System.out.println("\n[+]========== Menu Nasabah ==========[+]\n");
            System.out.println("1. Simpan Data");
            System.out.println("2. Lihat Nasabah");
            System.out.println("3. Tambah Nasabah");
            System.out.println("4. Ubah Data");
            System.out.println("5. Hapus Data");
            System.out.println("6. Exit");
            System.out.print("\nPilih Menu : ");

            int Pilih = scInput.nextInt();
            switch (Pilih) {
                case 1 -> {
                    saveToFile();
                }
                case 2 -> {
                    viewFile("User.dat");
                }
                case 3 -> {
                    addRecordToFile();
                }
                case 4 -> {
                    updateRecord();
                }
                case 5 -> {
                    deleteRecord();
                }
                case 6 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("\nBukan Pilihan\n");
                }
            }
            System.out.print("\nKembali ke Menu (y/n) : ");
            Quit = scInput.next().charAt(0);
        }
    }

    public void saveToFile() {
        Nasabah U = new Nasabah();
        String Nama = "", NoRekening = "", PIN = "", Alamat = "";
        double Saldo = 0;

        System.out.print("\n[+]========== Simpan Data Nasabah ==========[+]\n");
        System.out.print("\nInput Berapa Data : ");
        N = scInput.nextInt();
        System.out.println("");
        ObjectOutputStream Out = null;

        try {
            Out = new ObjectOutputStream(new java.io.FileOutputStream("src/User.dat"));
            BufferedReader brInput = new BufferedReader(new java.io.InputStreamReader(System.in));
            for (int i = 0; i < N; i++) {
                try {
                    System.out.print("Nama        : ");
                    Nama = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("No Rekening : ");
                    NoRekening = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("PIN         : ");
                    PIN = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("Alamat      : ");
                    Alamat = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("Saldo       : ");
                    Saldo = scInput.nextDouble();
                    U = new Nasabah(Nama, NoRekening, PIN, Alamat, Saldo);
                    Out.writeObject(U);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("");
            }
            Out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewFile(String fileName) {
        Nasabah U = new Nasabah();
        int TotalNasabah = 0;

//        System.out.print("\n[+]========== Data Nasabah ==========[+]\n");
        ObjectInputStream In = null;

        try {
            In = new ObjectInputStream(new java.io.FileInputStream("src/" + fileName));
            Object objInput = In.readObject();

            try {
                while (true) {
                    U = (Nasabah) objInput;
                    System.out.println("");
                    System.out.println("Nama        : " + U.getNama());
                    System.out.println("No Rekening : " + U.getNoRekening());
                    System.out.println("PIN         : " + U.getPIN());
                    System.out.println("Alamat      : " + U.getAlamat());
                    System.out.println("Saldo       : " + U.getSaldo());
                    TotalNasabah++;
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }
            System.out.println("");
            System.out.println("Nasabah : " + TotalNasabah);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRecordToFile() {
        Nasabah U = new Nasabah();
        String Nama = "", NoRekening = "", PIN = "", Alamat = "";
        double Saldo = 0;
        int TotalNasabah = 0;

        System.out.print("\n[+]========== Tambah Data Nasabah ==========[+]\n");
        System.out.print("\nTambah Berapa Data : ");
        N = scInput.nextInt();
        System.out.println("");
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
                    Out.writeObject(U);
                    TotalNasabah++;
                    objInput = In.readObject();
                }
            } catch (EOFException e) {
                System.out.print("");
            }

            for (int i = 0; i < N; i++) {
                try {
                    System.out.print("Nama        : ");
                    Nama = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("No Rekening : ");
                    NoRekening = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("PIN         : ");
                    PIN = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("Alamat      : ");
                    Alamat = brInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.print("Saldo       : ");
                    Saldo = scInput.nextDouble();
                    U = new Nasabah(Nama, NoRekening, PIN, Alamat, Saldo);
                    Out.writeObject(U);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("");
            }
            Out.close();

            try {
                In = new ObjectInputStream(new java.io.FileInputStream("src/UserTem.dat"));
                Out = new ObjectOutputStream(new java.io.FileOutputStream("src/User.dat"));
                objInput = In.readObject();

                try {
                    while (true) {
                        U = (Nasabah) objInput;
                        Out.writeObject(U);
                        TotalNasabah++;
                        objInput = In.readObject();
                    }
                } catch (EOFException e) {
                    System.out.print("");
                }
                Out.close();
//                System.out.println("Nasabah : " + TotalNasabah);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord() {
        Nasabah U = new Nasabah();
        String Nama = "", NoRekening = "", PIN = "", Alamat = "";
        double Saldo = 0;
        int TotalNasabah = 0;
        boolean found = false;

        System.out.print("\n[+]========== Update Data Nasabah ==========[+]\n");
        viewFile("User.dat");
        System.out.print("\nUpdate Data Berdasarkan No Rekening : ");
        NoRekening = scInput.next();
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
                    if (U.getNoRekening().equals(NoRekening)) {
                        System.out.print("\nNama        : ");
                        Nama = brInput.readLine();
                        System.out.print("PIN         : ");
                        PIN = brInput.readLine();
                        System.out.print("Alamat      : ");
                        Alamat = brInput.readLine();
                        System.out.print("Saldo       : ");
                        Saldo = scInput.nextDouble();
                        U = new Nasabah(Nama, NoRekening, PIN, Alamat, Saldo);
                        Out.writeObject(U);
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
                System.out.println("Data Tidak Ditemukan");
            } else {
                try {
                    In = new ObjectInputStream(new java.io.FileInputStream("src/UserTem.dat"));
                    Out = new ObjectOutputStream(new java.io.FileOutputStream("src/User.dat"));
                    objInput = In.readObject();

                    try {
                        while (true) {
                            U = (Nasabah) objInput;
                            Out.writeObject(U);
                            TotalNasabah++;
                            objInput = In.readObject();
                        }
                    } catch (EOFException e) {
                        System.out.print("");
                    }
                    Out.close();
                    System.out.print("\nData Berhasil Diupdate\n");
                    viewFile("User.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord() {
        Nasabah U = new Nasabah();
        String Nama = "", NoRekening = "", PIN = "", Alamat = "";
        double Saldo = 0;
        int TotalNasabah = 0;
        boolean found = false;

        System.out.print("\n[+]========== Hapus Data Nasabah ==========[+]\n");
        viewFile("User.dat");
        System.out.print("\nHapus Data Berdasarkan No Rekening : ");
        NoRekening = scInput.next();
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
                    if (U.getNoRekening().equals(NoRekening)) {
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
                System.out.println("Data Tidak Ditemukan");
            } else {
                try {
                    In = new ObjectInputStream(new java.io.FileInputStream("src/UserTem.dat"));
                    Out = new ObjectOutputStream(new java.io.FileOutputStream("src/User.dat"));
                    objInput = In.readObject();

                    try {
                        while (true) {
                            U = (Nasabah) objInput;
                            Out.writeObject(U);
                            TotalNasabah++;
                            objInput = In.readObject();
                        }
                    } catch (EOFException e) {
                        System.out.print("");
                    }
                    Out.close();
                    System.out.print("\nData Berhasil Dihapus\n");
                    viewFile("User.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NasabahMain NM = new NasabahMain();
        NM.menuNasabah();
    }
}
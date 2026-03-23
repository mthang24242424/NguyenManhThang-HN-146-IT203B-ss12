package Bai5;

import Bai5.entity.Patient;
import Bai5.service.PatientService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        PatientService service = new PatientService();

        while (true) {
            System.out.println("\n1. Danh sách");
            System.out.println("2. Thêm");
            System.out.println("3. Cập nhật bệnh");
            System.out.println("4. Xuất viện");
            System.out.println("5. Thoát");

            int c = Integer.parseInt(sc.nextLine());

            switch (c) {
                case 1:
                    service.showAll();
                    break;

                case 2:
                    System.out.print("Code: ");
                    String code = sc.nextLine();

                    System.out.print("Tên: ");
                    String name = sc.nextLine();

                    System.out.print("Tuổi: ");
                    int age = Integer.parseInt(sc.nextLine());

                    System.out.print("Khoa: ");
                    String dep = sc.nextLine();

                    service.add(new Patient(code, name, age, dep));
                    break;

                case 3:
                    System.out.print("ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Bệnh: ");
                    String dis = sc.nextLine();

                    service.update(id, dis);
                    break;

                case 4:
                    System.out.print("ID: ");
                    int id2 = Integer.parseInt(sc.nextLine());

                    service.discharge(id2);
                    break;

                case 5:
                    return;
            }
        }
    }
}
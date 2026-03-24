package bai6;

import bai6.service.MedicineService;
import java.util.Scanner;

public class Bai6Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MedicineService service = new MedicineService();

        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1. Update stock");
            System.out.println("2. Tìm theo giá");
            System.out.println("3. Tổng tiền đơn");
            System.out.println("4. Doanh thu ngày");
            System.out.println("5. Thoát");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    System.out.print("Số lượng thêm: ");
                    int qty = sc.nextInt();
                    service.updateStock(id, qty);
                    break;

                case 2:
                    System.out.print("Min: ");
                    double min = sc.nextDouble();
                    System.out.print("Max: ");
                    double max = sc.nextDouble();
                    service.searchByPrice(min, max);
                    break;

                case 3:
                    System.out.print("ID đơn: ");
                    int pid = sc.nextInt();
                    service.getPrescriptionTotal(pid);
                    break;

                case 4:
                    System.out.print("Ngày (yyyy-MM-dd): ");
                    String date = sc.next();
                    service.getRevenueByDate(date);
                    break;

                case 5:
                    return;
            }
        }
    }
}
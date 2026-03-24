package bai6.service;

import bai6.dao.MedicineDAO;

public class MedicineService {

    private MedicineDAO dao = new MedicineDAO();

    public void updateStock(int id, int qty) {
        dao.updateMedicineStock(id, qty);
        System.out.println("Update thành công!");
    }

    public void searchByPrice(double min, double max) {
        dao.findMedicinesByPriceRange(min, max);
    }

    public void getPrescriptionTotal(int id) {
        double total = dao.calculatePrescriptionTotal(id);
        System.out.println("Tổng tiền: " + total);
    }

    public void getRevenueByDate(String date) {
        double revenue = dao.getDailyRevenue(date);
        System.out.println("Doanh thu: " + revenue);
    }
}
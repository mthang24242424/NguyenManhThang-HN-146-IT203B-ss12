package bai6.dao;

import bai6.database.ConnectionDB;
import java.sql.*;

public class MedicineDAO {

    public void updateMedicineStock(int id, int addedQuantity) {
        String sql = "UPDATE medicines SET stock = stock + ? WHERE id = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, addedQuantity);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findMedicinesByPriceRange(double min, double max) {
        String sql = "SELECT * FROM medicines WHERE price BETWEEN ? AND ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("name") + " - " +
                                rs.getDouble("price") + " - " +
                                rs.getInt("stock")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double calculatePrescriptionTotal(int id) {
        String sql = "{CALL CalculatePrescriptionTotal(?, ?)}";
        double total = 0;

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.execute();

            total = cs.getDouble(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public double getDailyRevenue(String date) {
        String sql = "{CALL GetDailyRevenue(?, ?)}";
        double revenue = 0;

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setDate(1, Date.valueOf(date));
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.execute();

            revenue = cs.getDouble(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenue;
    }
}
package ThucHanh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/ss12";
    private static final String USER = "root";
    private static final String PASS = "123456";

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<Doctor> findDoctorBySpecialty(String specialty) {
        List<Doctor> list = new ArrayList<>();

        String sql = "SELECT * FROM Doctors WHERE specialty = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, specialty);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty"),
                        rs.getInt("experience_years"),
                        rs.getDouble("base_salary"),
                        rs.getString("password")
                );
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updatePassword(int id, String newPass) {
        String sql = "UPDATE Doctors SET password = ? WHERE doctor_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPass);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public double calculateDutyFee(int id) {
        double result = 0;

        String sql = "{CALL calculate_duty_fee(?, ?)}";

        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id); // IN
            cstmt.registerOutParameter(2, Types.DOUBLE); // OUT

            cstmt.execute();

            result = cstmt.getDouble(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

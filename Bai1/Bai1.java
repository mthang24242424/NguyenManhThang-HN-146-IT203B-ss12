package bai1;

import java.sql.*;

public class Bai1 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ss12";
        String user = "root";
        String password = "123456";

        String doctorCode = "D001";
        String doctorPass = "123";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            /*
            Phần 1:

            Đoạn code cũ dùng Statement + nối chuỗi:
            => Dữ liệu user bị chèn trực tiếp vào SQL

            Ví dụ hacker nhập:
            pass = ' OR '1'='1

            => SQL trở thành:
            SELECT * FROM Doctors
            WHERE code = 'D001' AND pass = '' OR '1'='1'

            => '1'='1' luôn đúng → bypass login


            PreparedStatement là "tấm khiên" vì:

            1. Tách SQL và dữ liệu:
               - SQL viết sẵn với dấu ?
               - Dữ liệu truyền vào bằng setXXX()

            2. Cơ chế Pre-compiled:
               - SQL được biên dịch trước (structure cố định)
               - Tham số chỉ được coi là dữ liệu, KHÔNG phải lệnh

            => Dù user nhập gì (kể cả ' OR '1'='1)
               thì nó vẫn chỉ là string bình thường

            => Không thể phá cấu trúc SQL → chống SQL Injection
            */

            /*
            Phần 2:
            */

            String sql = "SELECT * FROM Doctors WHERE doctor_code = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, doctorCode);
            pstmt.setString(2, doctorPass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công!");
                System.out.println("Tên bác sĩ: " + rs.getString("full_name"));
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
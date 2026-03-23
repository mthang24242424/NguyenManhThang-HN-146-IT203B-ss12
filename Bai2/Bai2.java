package Bai2;

import java.sql.*;

public class Bai2 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ss12";
        String user = "root";
        String password = "123456";

        double temperature = 37.5;
        int heartRate = 80;
        int patientId = 1;

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            /*
            Phần 1:

            Vấn đề khi dùng Statement + nối chuỗi:

            double temp = 37.5;

            String sql = "UPDATE Vitals SET temperature = " + temp + "...";

            => Nếu hệ điều hành dùng Locale kiểu Pháp/Việt:
               số thực sẽ hiển thị là 37,5 (dấu phẩy)

            => SQL sẽ thành:
               UPDATE Vitals SET temperature = 37,5 ...

            => SQL hiểu sai (vì phải dùng dấu chấm .)
            => Lỗi cú pháp ngay lập tức


            PreparedStatement giải quyết vì:

            1. Dữ liệu KHÔNG nối chuỗi trực tiếp vào SQL

            2. setDouble(), setInt():
               - Truyền giá trị dưới dạng kiểu dữ liệu thực
               - Driver JDBC tự convert đúng format chuẩn SQL (dấu chấm .)

            3. Không phụ thuộc Locale của máy

            => Dù máy dùng 37,5 hay 37.5
               thì khi xuống DB vẫn luôn đúng chuẩn 37.5

            => Tránh lỗi định dạng + an toàn hơn
            */

            /*
            Phần 2
            */

            String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE patient_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, temperature);
            pstmt.setInt(2, heartRate);
            pstmt.setInt(3, patientId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
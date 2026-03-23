package Bai3;

import java.sql.*;

public class Bai3 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ss12";
        String user = "root";
        String password = "123456";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            /*
            Phần 1:
            Lỗi gặp phải:
            - "The column index is out of range"
            - Hoặc không lấy được giá trị OUT

            Nguyên nhân:
            - Quên gọi registerOutParameter()
            - JDBC không biết tham số nào là OUTPUT


            Vì sao bắt buộc phải registerOutParameter()?

            - CallableStatement không tự biết tham số nào là OUT
            - Phải khai báo rõ:
                + vị trí tham số
                + kiểu dữ liệu

            => Nếu không đăng ký:
               getDouble(2) sẽ bị lỗi vì JDBC chưa chuẩn bị vùng nhớ cho OUT


            Nếu SQL dùng kiểu DECIMAL:

            Trong Java phải dùng:
                Types.DECIMAL

            Ví dụ:
                cstmt.registerOutParameter(2, Types.DECIMAL);

            => Đảm bảo mapping đúng giữa SQL và Java
            */

            /*
            Phần 2:
            */

            CallableStatement cstmt = conn.prepareCall("{call GET_SURGERY_FEE(?, ?)}");

            cstmt.setInt(1, 505);

            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            double cost = cstmt.getDouble(2);

            System.out.println("Chi phí phẫu thuật: " + cost);

            cstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
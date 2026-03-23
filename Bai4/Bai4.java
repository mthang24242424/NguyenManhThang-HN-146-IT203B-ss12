package Bai4;

import java.sql.*;
import java.util.*;

class TestResult {
    private String data;

    public TestResult(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

public class Bai4 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ss12";
        String user = "root";
        String password = "123456";

        List<TestResult> list = new ArrayList<>();
        list.add(new TestResult("A"));
        list.add(new TestResult("B"));
        list.add(new TestResult("C"));
        // giả lập nhiều data (có thể lên 1000)

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            /*
            Phần 1:

            Code cũ:

            for (...) {
                String sql = "INSERT INTO Results(data) VALUES('" + data + "')";
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }

            => Vấn đề:

            1. Mỗi vòng lặp:
               - Tạo Statement mới
               - DB phải PARSE lại SQL
               - DB phải tạo Execution Plan lại

            => Nếu 1000 bản ghi:
               - Parse 1000 lần
               - Compile 1000 lần

            => Lãng phí CPU + cực chậm


            PreparedStatement tối ưu vì:

            - SQL được biên dịch 1 lần duy nhất
            - Execution Plan được cache lại

            Trong vòng lặp:
            - chỉ thay đổi dữ liệu (parameter)
            - không cần parse lại SQL

            => Giảm tải cực mạnh cho DB
            => Tốc độ nhanh hơn rất nhiều (có thể gấp 5-10 lần)
            */

            /*
            Phần 2:
            */

            String sql = "INSERT INTO Results(data) VALUES(?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (TestResult tr : list) {
                pstmt.setString(1, tr.getData());
                pstmt.executeUpdate();
            }

            pstmt.close();
            conn.close();

            System.out.println("Insert thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
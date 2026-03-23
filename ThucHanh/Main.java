package ThucHanh;

public class Main {
    public static void main(String[] args) {
        DoctorDAO dao = new DoctorDAO();

        // Task 1
        System.out.println("=== Tìm bác sĩ Nhi khoa ===");
        dao.findDoctorBySpecialty("Nhi khoa")
                .forEach(System.out::println);

        // Task 2
        if (dao.updatePassword(1, "Rikkei2026")) {
            System.out.println("Cập nhật mật khẩu thành công!");
        }

        // Task 3
        double fee = dao.calculateDutyFee(1);
        System.out.println("Tiền trực ca: " + fee + " VNĐ");
    }
}
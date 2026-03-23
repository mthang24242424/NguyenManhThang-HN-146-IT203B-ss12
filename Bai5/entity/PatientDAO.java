package Bai5.dao;

import Bai5.entity.Patient;
import Bai5.util.DBConnection;

import java.sql.*;
import java.util.*;

public class PatientDAO {

    public List<Patient> getAll() throws Exception {
        List<Patient> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Patients";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Patient p = new Patient();
            p.setId(rs.getInt("patient_id"));
            p.setCode(rs.getString("patient_code"));
            p.setName(rs.getString("full_name"));
            p.setAge(rs.getInt("age"));
            p.setDepartment(rs.getString("department"));

            list.add(p);
        }

        return list;
    }

    public void insert(Patient p) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO Patients(patient_code, full_name, age, department) VALUES(?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getCode());
        ps.setString(2, p.getName());
        ps.setInt(3, p.getAge());
        ps.setString(4, p.getDepartment());

        ps.executeUpdate();
    }

    public boolean updateDisease(int id, String disease) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "UPDATE Patients SET disease = ? WHERE patient_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, disease);
        ps.setInt(2, id);

        return ps.executeUpdate() > 0;
    }

    public double discharge(int id) throws Exception {
        Connection conn = DBConnection.getConnection();

        CallableStatement cs = conn.prepareCall("{call CALCULATE_DISCHARGE_FEE(?, ?)}");

        cs.setInt(1, id);
        cs.registerOutParameter(2, Types.DOUBLE);

        cs.execute();

        return cs.getDouble(2);
    }
}
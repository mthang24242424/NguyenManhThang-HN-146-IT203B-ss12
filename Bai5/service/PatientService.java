package Bai5.service;

import Bai5.dao.PatientDAO;
import Bai5.entity.Patient;

import java.util.List;

public class PatientService {

    PatientDAO dao = new PatientDAO();

    public void showAll() throws Exception {
        List<Patient> list = dao.getAll();

        for (Patient p : list) {
            System.out.println(
                    p.getCode() + " | " +
                            p.getName() + " | " +
                            p.getAge() + " | " +
                            p.getDepartment()
            );
        }
    }

    public void add(Patient p) throws Exception {
        dao.insert(p);
    }

    public void update(int id, String disease) throws Exception {
        boolean ok = dao.updateDisease(id, disease);
        System.out.println(ok ? "OK" : "Không tìm thấy");
    }

    public void discharge(int id) throws Exception {
        double fee = dao.discharge(id);
        System.out.println("Viện phí: " + fee);
    }
}
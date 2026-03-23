package Bai5.entity;

public class Patient {
    private int id;
    private String code;
    private String name;
    private int age;
    private String department;
    private String disease;
    private int days;

    public Patient() {}

    public Patient(String code, String name, int age, String department) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    // getter setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }

    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }
}
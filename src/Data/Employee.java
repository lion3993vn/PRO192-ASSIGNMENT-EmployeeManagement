/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Pham Hieu
 */
public abstract class Employee {
    protected String name;
    protected String id;
    protected String gender;
    protected String birth;
    protected double basicSalary;
    protected double rateSalary;
    protected double salary;
    protected double seniority;

    public Employee(String name, String id, String gender, String birth, double basicSalary, double seniority) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.birth = birth;
        this.basicSalary = basicSalary;
        this.seniority = seniority;
        this.rateSalary = getRateSalary();
    }

    public double getRateSalary() {
        int i = 0;
        rateSalary = 1;
        if(seniority >= 5){
            i = (int)seniority / 5;
            rateSalary = rateSalary + i;
        }
        return rateSalary;
    }

    public double getSalary() {
        tinhLuong();
        return salary;
    }

    public void setSeniority(double seniority) {
            this.seniority = seniority;
    }
    public abstract void tinhLuong();
    public abstract void xuatThongTinNV();
}

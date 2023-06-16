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
public class Business extends Employee{
    double sales;
    double bonus;

    public Business(String name, String id, boolean gender, String birth, double basicSalary, double seniority) {
        super(name, id, gender, birth, basicSalary, seniority);
    }

    public Business(double sales, String name, String id, boolean gender, String birth, double basicSalary, double seniority) {
        super(name, id, gender, birth, basicSalary, seniority);
        this.sales = sales;
    }

    public double getBonus() {
        if(sales>= 5000000 && sales < 10000000){
            bonus = 5/100;
        } else if(sales>= 1000000 && sales < 20000000){
            bonus = 10/100;
        } else if(sales >= 20000000) bonus = 20/100;
        return bonus;
    }

    public double getSales(){
        return sales;
    }

    public void setSales(double sales){
            this.sales = sales;
        }
    
    @Override
    public void tinhLuong() {
            salary = basicSalary * getRateSalary() + getSales() * getBonus();
    }

    @Override
    public void xuatThongTinNV() {
        System.out.println("Tên: " + name);
        System.out.println("Mã số:" + id);
        System.out.println("Giới tính: " + gender);
        System.out.println("Ngày sinh: " + birth);
        System.out.println("Chức vụ: Nhân viên kinh doanh");
        System.out.println("Lương: "+ getSalary());
        System.out.println("Thâm niên: " + seniority);
        System.out.println("Doanh số: " + getSales());
        System.out.println("Hoa hồng: " + getBonus());
    }
}

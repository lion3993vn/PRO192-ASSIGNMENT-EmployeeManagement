package Data;

import java.text.DecimalFormat;

/**
 *
 * @author Pham Hieu
 */
public class Business extends Employee{
    double sales;
    double bonus;

    public Business(String name, String id, String gender, String birth, double basicSalary, double seniority) {
        super(name, id, gender, birth, basicSalary, seniority);
    }

    public Business(double sales, String name, String id, String gender, String birth, double basicSalary, double seniority) {
        super(name, id, gender, birth, basicSalary, seniority);
        this.sales = sales;
    }

    public double getBonus() {
        if(sales >= 5000000 && sales < 10000000){
            return 0.05;
        } else if(sales >= 10000000 && sales < 20000000){
            return 0.1;
        } else if(sales >= 20000000 && sales < 30000000){
            return 0.2;
        } else return 0;
    }
    
    public double getSales(){
        return sales;
    }

    public void setSales(double sales){
        if(sales >= 0){
            this.sales = sales;
            this.bonus = getBonus();
        }   
    }
    
    @Override
    public void tinhLuong() {
            salary = basicSalary * getRateSalary() + getSales() * getBonus();
    }

    @Override
    public void xuatThongTinNV() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        System.out.println("Tên: " + name);
        System.out.println("Mã số: " + id);
        System.out.println("Giới tính: " + gender);
        System.out.println("Ngày sinh: " + birth);
        System.out.println("Chức vụ: Nhân viên kinh doanh");
        System.out.println("Lương cơ bản: "+ decimalFormat.format(basicSalary));
        System.out.println("Lương: "+ decimalFormat.format(getSalary()));
        System.out.println("Thâm niên: " + seniority);
        System.out.println("Doanh số: " + decimalFormat.format(getSales()));
        System.out.println("Hoa hồng: " + decimalFormat.format(getBonus()* getSales()));
    }
}
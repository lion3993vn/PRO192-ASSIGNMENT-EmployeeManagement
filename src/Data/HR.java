/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Pham Hieu
 */
public class HR {

    private static List<Employee> ep = new ArrayList();
    private static DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    public HR() {
    }

    public static void main(String[] args) {
        byte choose, choose2;

        Scanner sc = new Scanner(System.in);
        do {
            menu();
            choose = sc.nextByte();
            switch (choose) {
                case 1:
                    nhapDS();
                    choose2 = sc.nextByte();
                    switch (choose2) {
                        case 1:
                            inputBussiness();
                            break;
                        case 2:
                            inputAdministrator();
                            break;
                    }
                    break;
                case 2:
                    for (Employee x : ep) {
                        System.out.println("========EMPLOYEE========");
                        x.xuatThongTinNV();
                    }
                    break;
                case 3:
                    printEmployeeType();
                    break;
                case 4:
                    calAvgSalary();
                    break;
            }
        } while (true);
    }

    public static void menu() {
        System.out.println("=============");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Danh sách nhân viên");
        System.out.println("3. Danh sách nhân viên theo loại");
        System.out.println("4. Lương trung bình toàn công ty");
        System.out.println("5. Lương cao nhất");
        System.out.println("6. Lương thấp nhất");
        System.out.println("7. Tìm kiếm nhân viên");
        System.out.println("8. Sắp xếp nhân viên");
        System.out.println("9. Xóa nhân viên theo mã");
        System.out.println("10. Cập nhật nhân viên theo mã");
        System.out.println("11. Exit chương trình");
        System.out.println("=============");
        System.out.print("Choose your option: ");
    }

    public static void nhapDS() {
        System.out.println("=====ADD=====");
        System.out.println("1. Nhân viên kinh doanh");
        System.out.println("2. Nhân viên hành chính");
        System.out.println("=============");
        System.out.print("Choose your option: ");
    }

    public static void inputBussiness() {
        String name, ID, birth, gender;
        double basicSalary, seniority, sales;
        int i;
        Scanner sc = new Scanner(System.in);
        name = Validation.getNoneBlankString("Tên: ", "Lỗi tên");
        do {
            System.out.print("ID: ");
            ID = sc.nextLine().toUpperCase();
            if (Validation.isID(ID)) {
                for (i = 0; i < ep.size(); i++) {
                    if (ep.get(i).id.equals(ID)) {
                        System.err.println("ID đã bị trùng");
                        break;
                    }
                }
                if(i == ep.size()) break;
            } else {
                System.err.println("ID không đúng định dạng");
            }
        } while (true);
        gender = Validation.getGender("Giới tính (true: nam | false: nữ): ", "Vui lòng nhập true/false");
        birth = Validation.getBirthDate("Ngày sinh (dd/mm/yyyy): ", "Vui lòng nhập đúng định dạng");
        basicSalary = Validation.isNumber("Lương cơ bản: ", "Lương phải là số và lớn hơn 0");
        seniority = Validation.isNumber("Thâm niên: ", "Thâm niên phải là số và lớn hơn 0");
        sales = Validation.isNumber("Doanh số: ", "Doanh số phải là số và lớn hơn 0");
        ep.add(new Business(sales, name, ID, gender, birth, basicSalary, seniority));
    }

    public static void inputAdministrator() {
        String name, ID, birth, gender;
        double basicSalary, seniority, allowance;
        int i;
        Scanner sc = new Scanner(System.in);
        name = Validation.getNoneBlankString("Tên: ", "Lỗi tên");
        do {
            System.out.print("ID: ");
            ID = sc.nextLine().toUpperCase();
            if (Validation.isID(ID)) {
                for (i = 0; i < ep.size(); i++) {
                    if (ep.get(i).id.equals(ID)) {
                        System.err.println("ID đã bị trùng");
                        break;
                    }
                }
                if(i == ep.size()) break;
            } else {
                System.err.println("ID không đúng định dạng");
            }
        } while (true);
        gender = Validation.getGender("Giới tính (true: nam | false: nữ): ", "Vui lòng nhập true/false");
        birth = Validation.getBirthDate("Ngày sinh (dd/mm/yyyy): ", "Vui lòng nhập đúng định dạng");
        basicSalary = Validation.isNumber("Lương cơ bản: ", "Lương phải là số và lớn hơn 0");
        seniority = Validation.isNumber("Thâm niên: ", "Thâm niên phải là số và lớn hơn 0");
        allowance = Validation.isNumber("Phụ cấp: ", "Phụ cấp phải là số và lớn hơn 0");
        ep.add(new Administrator(allowance, name, ID, gender, birth, basicSalary, seniority));
    }

    public static void printEmployeeType() {
        Scanner sc = new Scanner(System.in);
        String type;
        do{
        System.out.print("Chọn loại nhân viên (Business | Administrator): ");
        type = sc.nextLine().toLowerCase();
        if(!type.equals("business") || !type.equals("administrator")) System.err.println("Vui lòng nhập Business hoặc Administrator");
        } while(!type.equals("business") && !type.equals("administrator"));
        for (Employee x : ep) {
            if (type.equals("business") && (x instanceof Business)) {
                System.out.println("======BUSINESS======");
                x.xuatThongTinNV();
            } else if (type.equals("administrator") && (x instanceof Administrator)) {
                System.out.println("======ADMINISTRATOR======");
                x.xuatThongTinNV();
            }
        }
    }
    public static void calAvgSalary(){
        double sum = 0;
        if(ep.size() == 0){
            System.err.println("Không có nhân viên nào để tính lương");
//        } else if(ep.size() == 1){
//            System.out.println("Lương trung bình toàn công ty: " + (ep.get(0).salary));
        } else {
            for (Employee x : ep) {
                sum += x.getSalary();
            }
            System.out.println("Lương trung bình toàn công ty: " + decimalFormat.format(sum / ep.size()));
        }
    }
    public static void fiMostSalary(){
        
    }
}

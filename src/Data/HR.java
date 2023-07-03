/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        int choose, choose2, choose3;
        String id;
        Scanner sc = new Scanner(System.in);
        do {
            menu();
            choose = Validation.getChoose();
            switch (choose) {
                case 1:
                    do {
                        nhapDS();
                        choose2 = Validation.getChoose();
                        switch (choose2) {
                            case 1:
                                inputBussiness();
                                break;
                            case 2:
                                inputAdministrator();
                                break;
                            default:
                                break;
                        }
                    } while (choose2 < 2);
                    break;
                case 2:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    xuatToanBoDS();
                    break;
                case 3:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    String loaiNV;
                    do {
                        System.out.print("Vui lòng nhập loại (business | administrator): ");
                        loaiNV = sc.nextLine().toLowerCase();
                        if (loaiNV.equals("business") || loaiNV.equals("administrator")) {
                            break;
                        } else {
                            System.err.println("Vui lòng nhập lại");
                        }
                    } while (true);
                    xuatNVTheoLoai(loaiNV);
                    break;
                case 4:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    tinhLuongTB();
                    break;
                case 5:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    timLuongCaoNhat();
                    break;
                case 6:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    timLuongThapNhat();
                    break;
                case 7:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    do {
                        searchSelect();
                        choose3 = Validation.getChoose();
                        switch (choose3) {
                            case 1:
                                searchEpByName();
                                break;
                            case 2:
                                searchEpByID();
                                break;
                            default:
                                break;
                        }
                    } while (choose3 < 3);
                case 8:
                    sapXepNV();
                    break;
                case 9:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    do {
                        System.out.print("Nhập mã để xóa: ");
                        id = sc.nextLine().toLowerCase();
                        if (searchID(id) == null) {
                            System.err.println("ID không đúng hoặc chưa có");
                        } else {
                            break;
                        }
                    } while (true);
                    xoaNVTheoMa(id);
                    break;
                case 10:
                    if(ep.size() == 0){
                        System.err.println("Danh sách nhân viên rỗng");
                        break;
                    }
                    do {
                        System.out.print("Nhập mã để cập nhật: ");
                        id = sc.nextLine().toUpperCase();
                        if (searchID(id) == null) {
                            System.err.println("ID không đúng hoặc chưa có");
                        } else {
                            break;
                        }
                    } while (true);
                    capNhatNVTheoMa(id);
                    break;
                default:
                    break;
            }
        } while (choose < 11);
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
        System.out.println("11. Thoát chương trình");
        System.out.println("=============");
        System.out.print("Lựa chọn của bạn: ");
    }

    public static void nhapDS() {
        System.out.println("=====ADD/UPDATE=====");
        System.out.println("1. Nhân viên kinh doanh");
        System.out.println("2. Nhân viên hành chính");
        System.out.println("3. Thoát chương trình");
        System.out.println("=============");
        System.out.print("Lựa chọn của bạn: ");
    }

    public static void searchSelect() {
        System.out.println("=====SEARCH=====");
        System.out.println("1. Tìm kiếm bằng tên");
        System.out.println("2. Tìm kiếm bằng id");
        System.out.println("3. Thoát chương trình");
        System.out.println("=============");
        System.out.print("Lựa chọn của bạn: ");
    }

    public static void inputBussiness() {
        String name, ID, birth, gender;
        Scanner sc = new Scanner(System.in);
        double basicSalary, seniority, sales;
        name = Validation.getNoneBlankString("Tên: ", "Lỗi tên");
        do {
            System.out.print("ID (HRxxx): ");
            ID = sc.nextLine().toUpperCase();
            if (Validation.isID(ID) && (searchID(ID) == null)) {
                break;
            } else if (!Validation.isID(ID)) {
                System.err.println("ID không đúng định dạng");
            } else {
                System.err.println("ID đã bị trùng");
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
        Scanner sc = new Scanner(System.in);
        String name, ID, birth, gender;
        double basicSalary, seniority, allowance;
        name = Validation.getNoneBlankString("Tên: ", "Lỗi tên");
        do {
            System.out.print("ID (HRxxx): ");
            ID = sc.nextLine().toUpperCase();
            if (Validation.isID(ID) && (searchID(ID) == null)) {
                break;
            } else if (!Validation.isID(ID)) {
                System.err.println("ID không đúng định dạng");
            } else {
                System.err.println("ID đã bị trùng");
            }
        } while (true);
        gender = Validation.getGender("Giới tính (true: nam | false: nữ): ", "Vui lòng nhập true/false");
        birth = Validation.getBirthDate("Ngày sinh (dd/mm/yyyy): ", "Vui lòng nhập đúng định dạng");
        basicSalary = Validation.isNumber("Lương cơ bản: ", "Lương phải là số và lớn hơn 0");
        seniority = Validation.isNumber("Thâm niên: ", "Thâm niên phải là số và lớn hơn 0");
        allowance = Validation.isNumber("Phụ cấp: ", "Phụ cấp phải là số và lớn hơn 0");
        ep.add(new Administrator(allowance, name, ID, gender, birth, basicSalary, seniority));
    }

    public static void xuatToanBoDS() {
        if (ep.isEmpty()) {
            System.err.println("Không có nhân viên nào để hiển thị");
        } else {
            for (Employee x : ep) {
                System.out.println("========EMPLOYEE========");
                x.xuatThongTinNV();
            }
        }
    }

    public static void xuatNVTheoLoai(String type) {
        if (ep.size() == 0) {
            System.out.println("Không có nhân viên nào để hiển thị");
            return;
        }
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

    public static void tinhLuongTB() {
        double sum = 0;
        if (ep.size() == 0) {
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

    public static void timLuongCaoNhat() {
        double max = ep.get(0).getSalary();
        for (Employee x : ep) {
            if (x.getSalary() > max) {
                max = x.getSalary();
            }
        }
        System.out.println("Lương cao nhất công ty: " + decimalFormat.format(max));
        System.out.println("Danh sách nhân viên có lương cao nhất: ");
        for (Employee x : ep) {
            if (x.getSalary() == max) {
                System.out.println("======MOST SALARY======");
                x.xuatThongTinNV();
            }
        }
    }

    public static void timLuongThapNhat() {
        double min = ep.get(0).getSalary();
        for (Employee x : ep) {
            if (x.getSalary() < min) {
                min = x.getSalary();
            }
        }
        System.out.println("Lương thấp nhất công ty: " + decimalFormat.format(min));
        System.out.println("Danh sách nhân viên có lương thấp nhất: ");
        for (Employee x : ep) {
            if (x.getSalary() == min) {
                System.out.println("======LOWEST SALARY======");
                x.xuatThongTinNV();
            }
        }
    }

    public static Employee searchID(String id) {
        for (Employee x : ep) {
            if (x.id.equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    public static void searchEpByID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id để tìm: ");
        String id = sc.nextLine();
        if (searchID(id) == null) {
            System.err.println("Không có nhân viên nào với id " + id);
        } else {
            System.out.println("Đã tìm thấy nhân viên với id " + id);
            System.out.println("======INFO " + id + "======");
            searchID(id).xuatThongTinNV();
        }
    }

    public static void searchEpByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên để tìm: ");
        String name = sc.nextLine();
        boolean found = false;
        for (Employee x : ep) {
            if (x.name.equalsIgnoreCase(name)) {
                found = true;
                System.out.println("======INFO " + name + "======");
                x.xuatThongTinNV();
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy người nào có tên " + name);
        }
    }

    public static void sapXepNV() {
        Collections.sort(ep, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.name.equals(o2.name)) {
                    return (int) (o2.getSalary() - o1.getSalary());
                }
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println("Danh sách nhân viên sau khi sắp xếp");
        xuatToanBoDS();
    }

    public static void xoaNVTheoMa(String id) {
        boolean remove = false;
        for (Employee x : ep) {
            if (x.id.equalsIgnoreCase(id)) {
                ep.remove(x);
                System.out.println("Đã xóa nhân viên có ID " + id);
                remove = true;
                break;
            }
        }
        if (!remove) {
            System.err.println("Không có nhân viên nào có id " + id + " để xóa");
        }
    }

    public static void capNhatNVTheoMa(String id) {
        Scanner sc = new Scanner(System.in);
        String name, birth, gender;
        double basicSalary, seniority, allowance, sales;
        byte choose;
        if (searchID(id) == null) {
            System.err.println("ID không đúng hoặc không có");
            return;
        }
        for (int i = 0; i < ep.size(); i++) {
            if (ep.get(i).id.equals(id)) {
                nhapDS();
                choose = sc.nextByte();
                name = Validation.getNoneBlankString("Tên: ", "Lỗi tên");
                gender = Validation.getGender("Giới tính (true: nam | false: nữ): ", "Vui lòng nhập true/false");
                birth = Validation.getBirthDate("Ngày sinh (dd/mm/yyyy): ", "Vui lòng nhập đúng định dạng");
                basicSalary = Validation.isNumber("Lương cơ bản: ", "Lương phải là số và lớn hơn 0");
                seniority = Validation.isNumber("Thâm niên: ", "Thâm niên phải là số và lớn hơn 0");
                switch (choose) {
                    case 1:
                        sales = Validation.isNumber("Doanh số: ", "Doanh số phải là số và lớn hơn 0");
                        ep.set(i, new Business(sales, name, id, gender, birth, basicSalary, seniority));
                        break;
                    case 2:
                        allowance = Validation.isNumber("Phụ cấp: ", "Phụ cấp phải là số và lớn hơn 0");
                        ep.set(i, new Administrator(allowance, name, id, gender, birth, basicSalary, seniority));
                        break;
                }
                break;
            }
        }
        System.out.println("Thông tin đã được cập nhật");
    }
}

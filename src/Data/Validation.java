/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Pham Hieu
 */
public class Validation {
    public static boolean isID(String ID){
        String regex = "^HR\\d{5}$";
        if(ID.matches(regex)){
            return true;
        } else return false;
    }
    public static String getNoneBlankString(String inMsg, String errMsg) {
        String s = null;
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(inMsg);
                s = sc.nextLine().trim();
                if (s.isEmpty() || s.equals(" ") || s.length() > 50) {
                    throw new Exception();
                }
                cont = false;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        } while (cont == true);
        return s;
    }
    public static boolean checkDate(String birthDate) {
//        Scanner sc = new Scanner(System.in);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //System.out.println("Input your birthday: ");
            Date input = df.parse(birthDate);
            //System.out.println("Output:" + df.format(input));
            return true;
        } catch(Exception e){
            
        }
        return false;
    }
    
    public static String getBirthDate(String input, String error) {
        boolean check = true;
        String date;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(input);
            date = sc.nextLine();
            check = checkDate(date);
            if (!check) {
                System.err.println("Không đúng định dạng ngày dd/MM/yyyy");
            }else {
                String tmp[] = date.split("/");
                int year = Integer.parseInt(tmp[2]);
                if(2023 - year < 18){
                    System.err.println("Chưa đủ 18!");
                    check = false;
                }
            }
        } while (check == false);
        return date;
    }
    public static double isNumber(String input, String error){
        Scanner sc = new Scanner(System.in);
        double num;
        do{
            try{
                System.out.print(input);
                num = Double.parseDouble(sc.nextLine());
                if(num > 0){
                    break;
                } else throw new Exception();
            }catch(Exception e){
                System.err.println(error);
            }
        }while(true);
        return num;
    }
    
    public static String getGender(String input, String error){
        Scanner sc = new Scanner(System.in);
        String gender;
        do{
            System.out.print(input);
            gender = sc.nextLine().toLowerCase();
            if(gender.equals("true")){
                gender = "nam";
                break;
            }else if(gender.equals("false")){
                gender = "nữ";
                break;
            } else System.err.println(error);
        }while(true);
        return gender;
    }
}

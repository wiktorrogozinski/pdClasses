package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Menager menager = Menager.generateMenager();
        System.out.println(menager.toString());
    }
}
class Employee {
    String name;
    String surname;


    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }

    @Override
    public String toString() {
        return "Pracownik: " + name +" "+ surname + " zarobił w tym miesiącu "+ calculateSalary();

    }

    public float calculateSalary() {
        return 2500;
    }
    public static Employee generateOne (){
        return new Employee(Data.getName(),Data.getSurnames());

    }

    public String getDivison() {
        return this.getClass().getSimpleName();
    }
}

class Serviceman extends Employee {
    int hoursDone;
    int hourlyWage;

    public Serviceman(String name, String surname, int hoursDone, int hourlyWage) {
        super(name, surname);
        this.hoursDone = hoursDone;
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ ": "+ name +" "+ surname+ " zarobił w tym miesiącu "+ calculateSalary();
    }

    @Override
    public float calculateSalary() {
        return hoursDone*hourlyWage;
    }

    public static Serviceman generateOne (){
        return new Serviceman(Data.getName(),Data.getSurnames(),Data.getHoursDone(),Data.getHourlyWage());

    }

    public String getDivison() {
        return this.getClass().getSimpleName();
    }


}

class Dealer extends Employee {
    public int salesDone;
    float commision;

    public Dealer(String name, String surname, int salesDone, float commision) {
        super(name, surname);
        this.salesDone = salesDone;
        this.commision = commision;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +": "+ name +" "+ surname + " zarobił w tym miesiącu " + calculateSalary() + "zł.";

    }

    @Override
    public float calculateSalary() {
        return salesDone*commision;
    }

    public static Dealer generateOne (){
        return new Dealer(Data.getName(),Data.getSurnames(),Data.getSalesDone(),Data.getCommision());

    }

    public String getDivison() {
        return this.getClass().getSimpleName();
    }
}


class Data {
    static String [] name = {"Maciek", "Daniel", "Wiktor", "Ryszard", "Jarosław","Marek"};
    static String [] surnames = {"Kowalski", "Pieńkos", "Bąk", "Gawroński", "Dębicki","Okuniewski"};

    public static String getName() {
        int randomName = (int)(Math.random()*5);
        return name[randomName];
    }

    public static String getSurnames() {
        int randomSurname = (int)(Math.random()*5);
        return surnames[randomSurname];
    }

    public static int getSalesDone (){
        return (int)(Math.random()*200+20);
    }

    public static int getCommision (){
        return (int)(Math.random()*100);
    }

    public static int getHourlyWage () {
        return (int)(Math.random()*25+17);
    }

    public static int getHoursDone (){
        return (int)(Math.random()*200+100);
    }

    public static int getSalary (){
        return (int)(Math.random()*2300+1800);
    }

    public static int getDealersCommision (){
        return (int)Math.random()*150+50;
    }

    public static int getServiceManCommision () {
        return (int)(Math.random()*150+50);
    }
}

class Menager extends Employee{
    float dealerCommision;
    float servicemenCommision;
    Employee []staff;

    public Menager(String name, String surname, float dealerCommision, float servicemenCommision) {
        super(name, surname);
        this.dealerCommision = dealerCommision;
        this.servicemenCommision = servicemenCommision;
        this.staff = generateStaff();
    }

    public static Menager generateMenager () {
        return  new Menager(Data.getName(),Data.getSurnames(),Data.getDealersCommision(),Data.getServiceManCommision());

    }



    public Employee[] generateStaff () {
        Employee[] pracownicy = new Employee[10];

        for (int i = 0; i < pracownicy.length; ++i) {
            int warunek = (int) (Math.random() * 2);
            switch (warunek) {
                case 0: {
                    pracownicy[i] = Dealer.generateOne();
                    break;
                }
                case 1: {
                    pracownicy[i] = Serviceman.generateOne();
                    break;
                }

            }
        }

        return pracownicy;

    }

    @Override
    public float calculateSalary() {
        int hoursDone = 0;
        int salesDone = 0;

        for (int i = 0;i < staff.length; ++i) {
            if ((staff[i].getDivison()).equals("Dealer")) {
                salesDone += ((Dealer)staff[i]).salesDone;
            }
            else if ((staff[i].getDivison()).equals("Serviceman")) {
                hoursDone += ((Serviceman)staff[i]).hoursDone;
            }

            else
                continue;
        }

        return salesDone*servicemenCommision + hoursDone*servicemenCommision;

    }

    public String printStaff () {
        String worker = "";
        for (int i = 0; i < staff.length; ++i) {
            if(staff[i] != null ) {
                worker+=(staff[i].toString()+"\n");
            }
        }
        return worker;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +": "+ name +" "+ surname + " zarobił w tym miesiącu " + calculateSalary() + "zł. \n" + printStaff();

    }

    public String getDivison() {
        return this.getClass().getSimpleName();
    }
}


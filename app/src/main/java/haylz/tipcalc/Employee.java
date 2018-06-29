package haylz.tipcalc;

public class Employee {

    int lNum;
    String name;
    String number;
    double hours;
    double tips;

    public Employee(String name, String number, int lNum, double hours, double tips) {
        this.name = name;
        this.number = number;
        this.lNum = lNum;
        this.hours = hours;
        this.tips = tips;
    }

}
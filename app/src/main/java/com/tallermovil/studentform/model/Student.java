package com.tallermovil.studentform.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String num;
    private String age;
    private String career;

    public Student(String name, String num, String age, String career) {
        this.name = name;
        this.num = num;
        this.age = age;
        this.career = career;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}

package com.example.myapplication.src.Remark;

public class CommonRemarkFactoryManager {
    private static CommonRemarkFactory commonRemarkFactory;


    public static synchronized CommonRemarkFactory getInstance() {
        if (commonRemarkFactory == null) {
            return new CommonRemarkFactory();
        }
        return commonRemarkFactory;
    }
}

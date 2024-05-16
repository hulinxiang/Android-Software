package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 */
public class CommonRemarkFactoryManager {
    private static CommonRemarkFactory commonRemarkFactory;

    public static synchronized CommonRemarkFactory getInstance() {
        if (commonRemarkFactory == null) {
            return new CommonRemarkFactory();
        }
        return commonRemarkFactory;
    }
}
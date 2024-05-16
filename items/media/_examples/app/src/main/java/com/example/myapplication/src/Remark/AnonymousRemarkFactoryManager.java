package com.example.myapplication.src.Remark;

/**
 * Author: Linxiang Hu
 */
public class AnonymousRemarkFactoryManager {
    private static AnonymousRemarkFactory anonymousRemarkFactory;

    public static synchronized AnonymousRemarkFactory getInstance(){
        if (anonymousRemarkFactory==null){
            return new AnonymousRemarkFactory();
        }
        return anonymousRemarkFactory;
    }
}
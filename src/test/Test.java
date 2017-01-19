package test;

import system.PropertiesSwitch;

/**
 * Created by ilya on 16/09/2016.
 */
public class Test {

    public static void main(String args[]){
        PropertiesSwitch switcher=new PropertiesSwitch();

        System.out.println(switcher.getValue("./config/application.properties","bar"));
        switcher.switchProperties("./config/application.properties","bar","foo");


    }
}

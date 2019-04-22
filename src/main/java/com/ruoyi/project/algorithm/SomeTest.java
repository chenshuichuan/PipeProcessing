package com.ruoyi.project.algorithm;


import java.util.ArrayList;
import java.util.List;

public class SomeTest {
    public static void main(String[] args) {
        SomeTest someTest = new SomeTest();
        List<Integer> integerList = new ArrayList<>();
        List<Integer> integerList1 =integerList;

        integerList.add(10);
        integerList.add(2);
        Integer integer = integerList.get(1);
        System.out.println("integer:"+integer);
        System.out.print("list:");
        someTest.printList(integerList);
        System.out.print("list1:");
        someTest.printList(integerList1);
        integerList.set(1,5);
        System.out.println("integer:"+integer);

        System.out.print("list:");
        someTest.printList(integerList);
        System.out.print("list1:");
        someTest.printList(integerList1);

        Integer integer1 = integerList.get(1);
        Integer integer2 = integerList.get(0);
        integerList.set(0,integer1);
        integerList.set(1,integer2);

        for(Integer integer3: integerList){
            System.out.println(integer3);
            if(integer3.equals(integer1)){
                System.out.println("integer3.equals(integer1)");
            }
            if(integer3.equals(integer2)){
                System.out.println("integer3.equals(integer2)");
            }
        }
        System.out.println("integer:"+integer);

//        integerList = new ArrayList<>();
//        integerList.add(1);
//
//        System.out.print("list:");
//        someTest.printList(integerList);
//        System.out.print("list1:");
//        someTest.printList(integerList1);
    }

    private void printList(List<Integer> integerList){
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer integer:integerList){
            stringBuilder.append(integer);
            stringBuilder.append(" ");

        }
        System.out.println(stringBuilder.toString());
    }

}
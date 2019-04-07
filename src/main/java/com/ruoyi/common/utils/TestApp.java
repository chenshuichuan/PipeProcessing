package com.ruoyi.common.utils;

import com.ruoyi.project.system.scanner.domain.Scanner;
import io.swagger.models.auth.In;

import java.util.ArrayList;

/**
 *@ClassName: TestApp
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/4/5 20:09
 **/
public class TestApp {

    public static  void  main(){
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        //T
        String input = scanner.nextLine();
        int numberOfArray = Integer.parseInt(input);
        while(numberOfArray>0 && numberOfArray<10){
            numberOfArray--;

            input = scanner.nextLine();
            int N = Integer.parseInt(input);
            input = scanner.nextLine();
            String[] numberArray = input.split(" ");

            if(N<=0 || N!=numberArray.length){
                System.out.println("this input error!");
                continue;
            }
            ArrayList<Integer> array = new ArrayList<>();
            for (int i =0; i<N; i++){
                array.add(Integer.parseInt(numberArray[i]));
            }
            build(N, array);
        }

    }

    public static void build(int n, ArrayList< Integer>array){
        if(null == array || n != array.size()){
            System.out.println("error!");
            return;
        }
        System.out.println("n = "+n+","+"array=["+array);

        //卡住的块
        int i = 0;
        int j = 0;
        while (i<array.size()){
            //日期增加
            if(j<array.size())j++;

            //对ij之间的块进行处理
            int k = i;
            while(k<=j){
                //if(array.get(k))
            }
        }
    }
}

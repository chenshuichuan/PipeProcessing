package com.ruoyi.project.algorithm.dynamic;

import com.ruoyi.project.algorithm.genetic.GaInfo;
import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;

import java.util.List;

/**
 *@ClassName: DynamicProgramming
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/4/21 9:46
 **/
public class DynamicProForGa {

    /**
     * 待套料原料
     * **/
    private TaoliaoOrigin taoliaoOrigin;
    /**
     * 待下料管件
     * */
    private List<GaInfo> taoliaoGaList;

    /**
     * 填表法，参照https://mp.weixin.qq.com/s/rvdK0am5duiK1jrBnGt1iw
     * Solution1.java 的demo实现
     * */
    private Integer[][] results = null;

    public int init(TaoliaoOrigin taoliaoOrigin, List<GaInfo> taoliaoResultList) {
        if(null==taoliaoOrigin || null==taoliaoResultList
                ||taoliaoOrigin.getOriginLength()<1 || taoliaoResultList.size()<1){
            return 0;
        }
        this.taoliaoOrigin = taoliaoOrigin;
        this.taoliaoGaList = taoliaoResultList;
        results = new Integer[taoliaoResultList.size()+1][taoliaoOrigin.getOriginLength()+1];
        // 初始化
        for (int m = 0; m <= taoliaoResultList.size(); m++){
            results[m][0] = 0;
        }
        for (int m = 0; m <= taoliaoOrigin.getOriginLength(); m++){
            results[0][m] = 0;
        }

        fillResults();
        backtrackingResults();
        return 1;
    }
    /**
     * 填表法获取最优数值
     * **/
    private int fillResults(){
        // 开始填表
        for (int m = 1; m <= taoliaoGaList.size(); m++){
            for (int n = 1; n <= taoliaoOrigin.getOriginLength(); n++){
                int ws = taoliaoGaList.get(m-1).getCutLength();
                if (n < ws){
                    results[m][n] = results[m-1][n];
                } else {
                    if (results[m-1][n] > results[m-1][n-ws] + ws){
                        results[m][n] = results[m-1][n];
                    } else {
                        results[m][n] = results[m-1][n-ws] + ws;
                    }
                }
            }
        }
        return results[taoliaoGaList.size()][taoliaoOrigin.getOriginLength()];
    }

    /**
     * 回溯results得到最终的分配结果
     * **/
    private int backtrackingResults(){
        int pipeNumber=0;
        int totalUseLength = 0;
        for (int i = taoliaoGaList.size(); i > 0 ;){
            for (int j = taoliaoOrigin.getOriginLength(); j >0 ; ){

                //注意填表是留了一行一列为0，不用的的，取物品的时候还是要从0 开始取
                //第i-1个（从0开始）物品没有选中
                if(results[i-1][j].equals(results[i][j])){
                    i--;
                }
                else{
                    //第i-1个（从0开始）物品选中
                    int ws = taoliaoGaList.get(i-1).getCutLength();
                    pipeNumber++;
                    totalUseLength += ws;

                    taoliaoGaList.get(i-1).setTaoliaoOrigin(taoliaoOrigin.getId(),
                            taoliaoOrigin.getOriginIndex(),taoliaoOrigin.getOriginLength());
                    i--;
                    j=j-ws;
                }
                if(i<=0){
                    break;
                }
            }
        }

        taoliaoOrigin.setPipeNumber(pipeNumber);
        taoliaoOrigin.setRemainLength(taoliaoOrigin.getOriginLength()-totalUseLength);
        return 1;
    }


    public TaoliaoOrigin getTaoliaoOrigin() {
        return taoliaoOrigin;
    }

    public void setTaoliaoOrigin(TaoliaoOrigin taoliaoOrigin) {
        this.taoliaoOrigin = taoliaoOrigin;
    }

    public List<GaInfo> getTaoliaoGaList() {
        return taoliaoGaList;
    }

    public void setTaoliaoGaList(List<GaInfo> taoliaoGaList) {
        this.taoliaoGaList = taoliaoGaList;
    }

    public Integer[][] getResults() {
        return results;
    }

    public void setResults(Integer[][] results) {
        this.results = results;
    }

}

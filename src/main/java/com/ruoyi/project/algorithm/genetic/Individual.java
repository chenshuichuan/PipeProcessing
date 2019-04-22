package com.ruoyi.project.algorithm.genetic;

import com.ruoyi.project.algorithm.dynamic.DynamicProForGa;
import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;

import java.util.ArrayList;
import java.util.List;

public class Individual {

    /***
     *桶，染色体
     */
    private List<GaInfo>[] resultRoom;
    /**
     * 结果
     */
    private List<TaoliaoResult> taoliaoResultList;
    private List<GaInfo> gaInfoList;
    /**
     * 剩下未套料的管子
     * **/
    private List<GaInfo> remainGaInfo ;
    /**
     *原料
     */
    private List<TaoliaoOrigin> taoliaoOriginList;

    private double fitness = -1;

    public Individual(List<TaoliaoOrigin> taoliaoOrigins1, List<TaoliaoResult> taoliaoResultList1) {

        //深度复制
        this.taoliaoOriginList = new ArrayList<>(taoliaoOrigins1);
        this.taoliaoResultList = new ArrayList<>(taoliaoResultList1);
        gaInfoList = new ArrayList<>();
        for(int j = 0; j<taoliaoResultList.size(); j++){
            gaInfoList.add(new GaInfo(taoliaoResultList.get(j)));
        }

        //this.chromosome = new TaoliaoOrigin[taoliaoOriginList.size()];

        this.resultRoom = new List[taoliaoOriginList.size()];
        List<GaInfo> gaInfoCopy = gaInfoList;
        remainGaInfo =null;
        for(int i = 0; i<taoliaoOriginList.size(); i++){
            //this.chromosome[i]= taoliaoOriginList.get(i);
            //对每根原料管进行初始化、初步分配
//            DynamicProForGa dynamicProForGa = new DynamicProForGa();
//            dynamicProForGa.init(taoliaoOriginList.get(i),gaInfoCopy);
            randomInit(taoliaoOriginList.get(i), gaInfoCopy);
            remainGaInfo = gaInfoCopy;
            // 对套料完成的进行保存和剔除
            gaInfoCopy = new ArrayList<>();
            List<GaInfo> gaInfosOk = new ArrayList<>();
            for (GaInfo gaInfo: remainGaInfo){

                //该gaInfo被分配到该原料进行套料
                if(gaInfo.getOriginId()!=0){
                    gaInfosOk.add(gaInfo);
                }
                else{
                    gaInfoCopy.add(gaInfo);
                }
            }

            //第i间房分配好了
            resultRoom[i] = gaInfosOk;
        }
        //剩下的是未能分配的
        remainGaInfo = gaInfoCopy;
    }

    /**
     * 随机的初始化个体
     * 随机取x根未下料管子,加入该原料套料
     * @param gaInfoList 传入的都是未下料的管子！
     * @param taoliaoOrigin 待下料原料
     * ***/
    private void randomInit(TaoliaoOrigin taoliaoOrigin, List<GaInfo> gaInfoList){
        int loopNumber = (int)(gaInfoList.size()*Math.random());
        int pipeNumber = 0;
        while (loopNumber>0){
            int index = (int)(gaInfoList.size()*Math.random());
            GaInfo gaInfo = gaInfoList.get(index);
            if(gaInfo.getOriginId()!=0){
                if(taoliaoOrigin.getRemainLength()>= gaInfo.getCutLength()){
                    gaInfo.setTaoliaoOrigin(taoliaoOrigin.getId(),taoliaoOrigin.getOriginIndex(),taoliaoOrigin.getOriginLength());
                    taoliaoOrigin.setRemainLength(taoliaoOrigin.getRemainLength()-gaInfo.getCutLength());
                    pipeNumber++;
                }
                loopNumber--;
            }
        }
        taoliaoOrigin.setPipeNumber(pipeNumber);
    }

    /**
     * Calculate individual's fitness value
     * @return fitness
     */
    public double calcFitness() {
        // Calculate fitness
        int unCutLength = getRemainGaInfoLength();
        int useRemainLength = getOriginUseRemainLength();
        double fitness = 100.0 / (double) (unCutLength+useRemainLength + 1);

        this.setFitness(fitness);
        return fitness;
    }

    public int getRemainGaInfoLength(){
        int unCutLength = 0;
        for(GaInfo gaInfo : remainGaInfo){
            if(gaInfo.getOriginId()!=0){
                System.out.println("remainGaInfo has error data:"+gaInfo.toString());
            }
            unCutLength+=gaInfo.getCutLength();
        }
        return  unCutLength;
    }
    /**
     * 计算原料中已经被使用但是又有剩余的管材
     * 不计算未使用的原料
     * **/
    public int getOriginUseRemainLength(){
        int useRemainLength = 0;
        for(TaoliaoOrigin taoliaoOrigin : taoliaoOriginList){
            if(taoliaoOrigin.getPipeNumber()!=0){
                useRemainLength+= taoliaoOrigin.getRemainLength();
            }
        }
        return  useRemainLength;
    }
    /**
     * Gets individual's chromosome length
     *
     * @return The individual's chromosome length
     */
    public int getChromosomeLength() {
        return this.resultRoom.length;
    }

    /**
     * Set gene at offset
     *
     * @param gaInfoList 该基因包含的管件
     * @param offset index
     */
    public void setGene(int offset, List<GaInfo> gaInfoList) {
        this.resultRoom[offset] = gaInfoList;
    }

    /**
     * Get gene at offset
     *
     * @param offset index
     * @return gene 该基因包含的管件
     */
    public  List<GaInfo> getGene(int offset) {
        return this.resultRoom[offset];
    }


    public List<GaInfo>[] getResultRoom() {
        return resultRoom;
    }

    public void setResultRoom(List<GaInfo>[] resultRoom) {
        this.resultRoom = resultRoom;
    }

    public List<TaoliaoResult> getTaoliaoResultList() {
        return taoliaoResultList;
    }

    public void setTaoliaoResultList(List<TaoliaoResult> taoliaoResultList) {
        this.taoliaoResultList = taoliaoResultList;
    }

    public List<GaInfo> getGaInfoList() {
        return gaInfoList;
    }

    public void setGaInfoList(List<GaInfo> gaInfoList) {
        this.gaInfoList = gaInfoList;
    }

    public List<GaInfo> getRemainGaInfo() {
        return remainGaInfo;
    }

    public void setRemainGaInfo(List<GaInfo> remainGaInfo) {
        this.remainGaInfo = remainGaInfo;
    }

    public List<TaoliaoOrigin> getTaoliaoOriginList() {
        return taoliaoOriginList;
    }

    public void setTaoliaoOriginList(List<TaoliaoOrigin> taoliaoOriginList) {
        this.taoliaoOriginList = taoliaoOriginList;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * 将index段的resultRoom中的List<GaInfo> 替换成 传入的List<GaInfo>
     * 并且要符合原来的数据，唯一性
     * */
    public  void replaceGen(int index, List<GaInfo> others){
        List<GaInfo> oldList = resultRoom[index];
        //先替换出所有
        for (GaInfo newGaInfo: others){

        }
        for (GaInfo oldGaInfo: oldList){

        }
    }
}

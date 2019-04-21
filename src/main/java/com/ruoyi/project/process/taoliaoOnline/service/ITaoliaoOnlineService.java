package com.ruoyi.project.process.taoliaoOnline.service;

import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliaoOnline.domain.OriginInfo;

import java.util.List;

/**
 * 在线套料 服务层
 * 
 * @author ricardo
 * @date 2019-04-19
 */
public interface ITaoliaoOnlineService 
{

    int generateTaoliao(ArrangeTable arrangeTable);

    /**
     * 根据某个要套料的管材Taoliao去套料
     * **/
    public int dynamicProgramming(Taoliao taoliao);
    /**
     * 输入管材原料
     * @param taoliao 对应的套料taoliao次
     * @param originInfos 用户输入的管材原料数据信息
     * @param algorithm 所用套料算法
     * **/
    public int inputOrigin(Taoliao taoliao, List<OriginInfo> originInfos, String algorithm);
    /**
     * 删除TaoliaoOrigin信息
     * @param taoliao 对应的套料taoliao次
     * @param algorithm 套料算法
     * **/
    public int deleteOrigin(Taoliao taoliao, String algorithm);
}

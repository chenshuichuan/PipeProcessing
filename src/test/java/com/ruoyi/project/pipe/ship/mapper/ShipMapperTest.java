package com.ruoyi.project.pipe.ship.mapper;

import com.ruoyi.project.pipe.ship.domain.ShipSimple;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/31
 * Time: 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipMapperTest {
    @Autowired
    private ShipMapper shipMapper;
    @Test
    public void selectShipSimpleList() {
        List<ShipSimple> shipSimpleList = shipMapper.selectShipSimpleList(null);
        Assert.assertThat(shipSimpleList,notNullValue());
        Assert.assertThat(shipSimpleList.size(),greaterThan(0));
    }
}
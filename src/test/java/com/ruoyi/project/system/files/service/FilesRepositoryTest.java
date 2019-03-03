package com.ruoyi.project.system.files.service;

import com.ruoyi.project.system.files.domain.Files;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/2
 * Time: 23:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilesRepositoryTest {

    @Autowired
    private FilesRepository filesRepository;

    @Test
    public void findBySuffix() {
        List<Files> filesList = filesRepository.findBySuffix("xls");
        Assert.assertThat(filesList,notNullValue());
        Assert.assertThat(filesList.size(),greaterThan(0));
        System.out.println("find size = "+filesList.size());
    }
}
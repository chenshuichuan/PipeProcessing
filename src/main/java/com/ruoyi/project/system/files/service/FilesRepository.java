package com.ruoyi.project.system.files.service;


import com.ruoyi.project.system.files.domain.Files;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by: ricardo
 * Description: 文件类的repository类
 * Date: 2018/8/22
 */
public interface FilesRepository extends JpaRepository<Files,Integer> {

    //根据文件后缀查询文件
    List<Files> findBySuffix(String suffix);
}

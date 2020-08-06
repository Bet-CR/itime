package com.pointflow.itime.service.impl;

import com.pointflow.itime.domain.Pictures;
import com.pointflow.itime.mapper.PicturesMapper;
import com.pointflow.itime.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 19:33
 **/
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PicturesMapper picturesMapper;

    @Override
    public int addPicture(Pictures picture) {
        return picturesMapper.addPicture(picture);
    }

    @Override
    public String[] getUris(Pictures picture) {
        return picturesMapper.getUris(picture);
    }
}

package com.pointflow.itime.service;

import com.pointflow.itime.domain.Pictures;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 19:29
 **/
public interface PictureService {

    int addPicture(Pictures picture);
    String[] getUris(Pictures picture);
}

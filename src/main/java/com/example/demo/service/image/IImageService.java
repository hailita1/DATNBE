package com.example.demo.service.image;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.service.IGeneralService;

public interface IImageService extends IGeneralService<Image> {
    Iterable<Image> findAllByHouseImage(House house);
}

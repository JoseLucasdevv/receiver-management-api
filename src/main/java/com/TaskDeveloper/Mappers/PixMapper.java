package com.TaskDeveloper.Mappers;

import com.TaskDeveloper.Dtos.PixDTO;
import com.TaskDeveloper.Entity.Pix;

public class PixMapper {

    public PixDTO entityToDTO(Pix pix){
        PixDTO pixdto = new PixDTO(pix.getKey_type(),pix.getKey_pix(),pix.getPixId());
        return pixdto;
    }

    public Pix dtoToEntity(PixDTO pixdto){
        Pix pix = new Pix();
        pix.setKey_pix(pixdto.key_pix());
        pix.setKey_type(pixdto.key_type());
        return pix;
    }
}

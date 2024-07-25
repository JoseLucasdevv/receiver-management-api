package com.TaskDeveloper.Mappers;
import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiverMapper {

    public ReceiverDTO entityToDTO(Receiver receiver){
        return new ReceiverDTO(receiver.getReceiverId(),receiver.getName(),receiver.getCpf(),receiver.getEmail(),receiver.getPix());
    }

    public Receiver dtoToEntity(ReceiverDTO receiverdto){
        Receiver receiver = new Receiver();
        receiver.setCpf(receiverdto.cpf());
        receiver.setName(receiverdto.name());
        receiver.setEmail(receiverdto.email());
        receiver.setPix(receiverdto.pix());
        return receiver;
    }
    public List<Receiver> listDtoToEntity(List<ReceiverDTO> receiverdto) {
        List<Receiver> receivers = new ArrayList<>();

        return receiverdto.stream().map(dto -> {
            Receiver receiver = new Receiver();
            receiver.setCpf(dto.cpf());
            receiver.setName(dto.name());
            receiver.setEmail(dto.email());
            receiver.setPix(dto.pix());
            return receiver;
        }).collect(Collectors.toList());



    }


}

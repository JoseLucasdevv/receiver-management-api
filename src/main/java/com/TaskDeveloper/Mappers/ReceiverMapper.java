package com.TaskDeveloper.Mappers;
import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.TypesStatus.TypeStatus;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiverMapper {

    public ReceiverDTO entityToDTO(Receiver receiver){
        return new ReceiverDTO(receiver.getReceiverId(),receiver.getName(),receiver.getCpfOrCnpj(),receiver.getEmail(),receiver.getPix(),receiver.getStatus());
    }

    public Receiver dtoToEntity(ReceiverDTO receiverdto){
        Receiver receiver = new Receiver();
        receiver.setCpfOrCnpj(receiverdto.cpfOrCnpj());
        receiver.setName(receiverdto.name());
        receiver.setEmail(receiverdto.email());
        receiver.setPix(receiverdto.pix());
        if(receiverdto.status() == TypeStatus.VALIDATED){
            receiver.setStatus(receiverdto.status());
        }

        return receiver;
    }
    public List<Receiver> listDtoToEntity(List<ReceiverDTO> receiverdto) {

        return receiverdto.stream().map(dto -> {
            Receiver receiver = new Receiver();
            receiver.setCpfOrCnpj(dto.cpfOrCnpj());
            receiver.setName(dto.name());
            receiver.setEmail(dto.email());
            receiver.setPix(dto.pix());
            if(dto.status() == TypeStatus.VALIDATED){
                receiver.setStatus(dto.status());
            }
            return receiver;
        }).collect(Collectors.toList());

    }


}

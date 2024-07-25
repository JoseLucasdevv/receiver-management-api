package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.Mappers.ReceiverMapper;
import com.TaskDeveloper.Repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateReceiverUseCase {

    @Autowired
    ReceiverRepository _receiverRepository;

    public String updateReceiver(long id , ReceiverDTO receiverdto){

       this._receiverRepository.findById(id).ifPresentOrElse((user)->{

           Receiver receiver = new ReceiverMapper().dtoToEntity(receiverdto);

           user.setPix(receiver.getPix());
           user.setCpf(receiver.getCpf());
           user.setName(receiver.getName());
           user.setEmail(receiver.getEmail());

           this._receiverRepository.save(user);

            }, ()->{
                throw new RuntimeException("cannot be find");
            });

        return "Success to update user";

    }

}

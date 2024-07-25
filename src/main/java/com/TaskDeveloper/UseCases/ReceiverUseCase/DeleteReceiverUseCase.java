package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.Mappers.ReceiverMapper;
import com.TaskDeveloper.Repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeleteReceiverUseCase {
@Autowired
    ReceiverRepository _receiverRepository;

    public String deleteReceiver(long id) {
        this._receiverRepository.findById(id).ifPresentOrElse((user)->{
            this._receiverRepository.delete(user);
        }, ()->{
            throw new RuntimeException("cannot be find");
        });

        return "Success to Delete user";

        }

    public String deleteReceivers(List<ReceiverDTO> receiverDto) {

        List<Long> listId;

        listId = receiverDto.stream().map(ReceiverDTO::receiver_id).collect(Collectors.toList());


      this._receiverRepository.deleteAllById(listId);

        return "Success to Delete users";

    }



}


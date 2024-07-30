package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.Repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetReceiverUseCase {

    @Autowired
    private ReceiverRepository _receiverRepository;

    public GetReceiverUseCase(ReceiverRepository receiverRepository) {
        this._receiverRepository = receiverRepository;
    }


    public List<Receiver> resourceAllReceiver(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,10);

        Page<Receiver> listPageable =
                this._receiverRepository.findAll(page);

        List<Receiver> receivers = listPageable.stream().collect(Collectors.toList());
        return receivers;
    }
}

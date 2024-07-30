package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.Repository.ReceiverRepository;
import com.TaskDeveloper.TypesStatus.TypeStatus;
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


    public List<Receiver> getAllReceiver(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,10);

        Page<Receiver> listPageable =
                this._receiverRepository.findAll(page);

        List<Receiver> receivers = listPageable.stream().collect(Collectors.toList());
        return receivers;

    }
    public List<Receiver> getAllReceiverByName(String name,int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,10);

        Page<Receiver> listPageable =
                this._receiverRepository.findAllByName(name,page);

        List<Receiver> receivers = listPageable.stream().collect(Collectors.toList());
        return receivers;

    }
    public List<Receiver> getAllReceiverByStatus( int pageNumber,TypeStatus status) {
        Pageable page = PageRequest.of(pageNumber,10);

        Page<Receiver> listPageable =
                this._receiverRepository.findAllByStatus(status,page);

        List<Receiver> receivers = listPageable.stream().collect(Collectors.toList());
        return receivers;

    }


}

package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.PixDTO;
import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.Repository.ReceiverRepository;

import com.TaskDeveloper.TypesPix.TypesPix;
import com.TaskDeveloper.TypesPix.Validation.ValidationKeyTypePix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.TaskDeveloper.Mappers.ReceiverMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CreateReceiverUseCase extends RuntimeException {
    @Autowired
    private ReceiverRepository _receiverRepository;
    @Autowired
    private ValidationKeyTypePix _validationTypePix;

    public String createReceiverList(List<ReceiverDTO> receiverDto) {
            this._validationTypePix.handlereceiverDTOList(receiverDto);

            this._receiverRepository.saveAll(new ReceiverMapper().listDtoToEntity(receiverDto));
            return "save";
        }


    public String createReceiver(ReceiverDTO receiverDto) {

    this._validationTypePix.handlereceiverDTO(receiverDto);

    this._receiverRepository.save(new ReceiverMapper().dtoToEntity(receiverDto));

        return "save";
    }


}
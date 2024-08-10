package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Repository.ReceiverRepository;
import com.TaskDeveloper.TypesPix.Validation.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TaskDeveloper.Mappers.ReceiverMapper;
import java.util.List;



@Service
public class CreateReceiverUseCase extends RuntimeException {
    @Autowired
    private ReceiverRepository _receiverRepository;
    @Autowired
    private Validations _validations;

    public String createReceiverList(List<ReceiverDTO> receiverDto) {

            this._validations.ValidateCpfOrCnpjList(receiverDto);
            this._validations.handlereceiverDTOList(receiverDto);

            this._receiverRepository.saveAll(new ReceiverMapper().listDtoToEntity(receiverDto));
            return "save";
        }


    public String createReceiver(ReceiverDTO receiverDto) {
        this._validations.ValidateCpfOrCnpj(receiverDto);
    this._validations.handlereceiverDTO(receiverDto);

    this._receiverRepository.save(new ReceiverMapper().dtoToEntity(receiverDto));

        return "save";
    }


}
package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Pix;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.Mappers.ReceiverMapper;
import com.TaskDeveloper.Repository.ReceiverRepository;
import com.TaskDeveloper.TypesPix.Validation.Validations;
import com.TaskDeveloper.TypesStatus.TypeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TaskDeveloper.exceptions.Exception;

import java.util.ArrayList;
import java.util.List;


@Service
public class UpdateReceiverUseCase {

    @Autowired
    ReceiverRepository _receiverRepository;
    @Autowired
    Validations _validations;

    public String updateReceiver(long id , ReceiverDTO receiverdto){
        Receiver receiver = new ReceiverMapper().dtoToEntity(receiverdto);
       this._receiverRepository.findById(id).ifPresentOrElse((user)->{

           if(user.getStatus() != receiver.getStatus()){
               throw new Exception("Cannot Change Status");
           }

           if(user.getStatus() == TypeStatus.VALIDATED){
               this.checkPix(user.getPix(),receiver.getPix());

                if(!user.getCpfOrCnpj().equals(receiver.getCpfOrCnpj())){
                    throw new Exception("Cannot change cpfOrCnpj");
                }
               if(!user.getName().equals(receiver.getName())){
                   throw new Exception("Cannot change Name");
               }

                user.setEmail(receiver.getEmail());
           }

           if(user.getStatus() == TypeStatus.SKETCH){
            this._validations.handlereceiverDTO(receiverdto);

               user.setPix(receiver.getPix());
               user.setCpfOrCnpj(receiver.getCpfOrCnpj());
               user.setName(receiver.getName());
               user.setEmail(receiver.getEmail());

           }


           this._receiverRepository.save(user);

            }, ()->{
                throw new RuntimeException("cannot be find");
            });

        return "Success to update user";

    }
    private void checkPix(List<Pix> bdPix, List<Pix> inputPix){
    List<String> bdKeys = new ArrayList<>();
    List<String> inputKeys = new ArrayList<>();


        bdPix.forEach(pix -> {
            bdKeys.add(pix.getKey_type().name());
            bdKeys.add(pix.getKey_pix());
        });
        inputPix.forEach(pix -> {
            inputKeys.add(pix.getKey_type().name());
            inputKeys.add(pix.getKey_pix());

        });

        if(!bdKeys.get(0).equals(inputKeys.get(0))){
            throw new Exception("Cannot change Pix key");
        }
        if(!bdKeys.get(1).equals(inputKeys.get(1))){
            throw new Exception("cannot change Pix value");
        }


    }

}

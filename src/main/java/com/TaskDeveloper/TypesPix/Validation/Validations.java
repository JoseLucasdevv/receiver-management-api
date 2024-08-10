package com.TaskDeveloper.TypesPix.Validation;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.TypesPix.TypesPix;

import org.springframework.stereotype.Component;
import com.TaskDeveloper.exceptions.Exception;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class Validations {

    private String cpfPattern = "^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}$";
    private String cnpjPattern = "^[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}-?[0-9]{2}$";
    private String randomKeyPattern = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    private String phonePattern = "^((?:\\+?55)?)([1-9][0-9])(9[0-9]{8})$";
    private String emailPattern = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";

    private Set<String> processDuplicateKey = new HashSet<>();

    public void handlereceiverDTO(ReceiverDTO receiverdto){
        try{
        receiverdto.pix().forEach(receiverPix -> {
                this.checkForDuplicateKey(receiverPix.getKey_pix());
                this.validationKey(receiverPix.getKey_type(), receiverPix.getKey_pix());
        });
        }
        finally {
        this.resetProcessedKeys();
        }
    }
    public void handlereceiverDTOList(List<ReceiverDTO> receiverdto){
        try{
        receiverdto.forEach(receivers->{
            receivers.pix().forEach(receiverPix ->{
                this.checkForDuplicateKey(receiverPix.getKey_pix());
                this.validationKey(receiverPix.getKey_type(), receiverPix.getKey_pix());
            });
        });
        }finally {
            this.resetProcessedKeys();
        }
    }

    private void checkForDuplicateKey(String keyValue) {
        if (processDuplicateKey.contains(keyValue)) {
            throw new Exception("Duplicate Key: " + keyValue);
        } else {
            processDuplicateKey.add(keyValue);
        }
    }

    private void resetProcessedKeys() {
        processDuplicateKey.clear();
    }

    private void validationKey(TypesPix keyType, String keyValue)  {

    switch(keyType){
        case CPF:
            Boolean matcherCPF = matcherPattern(this.cpfPattern, keyValue);
            this.checkMatcher(matcherCPF,keyType);
            return;

        case CNPJ:
            Boolean matcherCNPJ = matcherPattern(this.cnpjPattern, keyValue);
            this.checkMatcher(matcherCNPJ,keyType);
            return;

        case RANDOM_KEY:
            Boolean matcherRandomKey = matcherPattern(this.randomKeyPattern, keyValue);
            this.checkMatcher(matcherRandomKey,keyType);
            return;

        case PHONE:

            Boolean matcherPhone = matcherPattern(this.phonePattern, keyValue);
            this.checkMatcher(matcherPhone,keyType);
            return;

        case EMAIL:

            Boolean matcherEmail = matcherPattern(this.emailPattern, keyValue);
            this.checkMatcher(matcherEmail,keyType);
            return;

        default:
            throw new Exception("Unknown KeyType: " + keyType);
        }
    }

    private boolean matcherPattern(String patternKey, String keyValue){
        Pattern pattern = Pattern.compile(patternKey);
        Matcher matcher = pattern.matcher(keyValue);
        return matcher.matches();
    }

    public void ValidateCpfOrCnpj(ReceiverDTO receiver){
    Boolean cpfValidate =  this.matcherPattern(this.cpfPattern,receiver.cpfOrCnpj());
    Boolean cnpjValidate = this.matcherPattern(this.cnpjPattern,receiver.cpfOrCnpj());

    if(cpfValidate || cnpjValidate){
        System.out.println("ok");
    }else{
        throw new Exception("Cpf or CNPJ failed");
    }

    }

    public void ValidateCpfOrCnpjList(List<ReceiverDTO> receiverDto){
        receiverDto.forEach(r ->{
            Boolean cpfValidate =  this.matcherPattern(this.cpfPattern,r.cpfOrCnpj());
            Boolean cnpjValidate = this.matcherPattern(this.cnpjPattern,r.cpfOrCnpj());

            if(cpfValidate || cnpjValidate){
                System.out.println("ok");
            }else{
                throw new Exception("Cpf or CNPJ failed");
            }

        });

    }

    private void checkMatcher(Boolean matcher, TypesPix type){
        if(!matcher){
            throw new Exception("Invalid KeyValue: " + type);
        }
    };

}

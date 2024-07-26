package com.TaskDeveloper.TypesPix.Validation;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.TypesPix.TypesPix;
import org.springframework.stereotype.Component;
import com.TaskDeveloper.exceptions.Exception;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



@Component
public class ValidationKeyTypePix {


    public void handlereceiverDTO(ReceiverDTO receiverdto){

        receiverdto.pix().forEach(receiverPix -> {

                this.validationKey(receiverPix.getKey_type(), receiverPix.getKey_pix());

        });

    }
    public void handlereceiverDTOList(List<ReceiverDTO> receiverdto){

        receiverdto.forEach(receivers->{
            receivers.pix().forEach(receiverPix ->{
                this.validationKey(receiverPix.getKey_type(), receiverPix.getKey_pix());
            });
        });

    }

    private Boolean validationKey(TypesPix keyType,String keyValue)  {

    switch(keyType){
        case CPF:
            Boolean matcherCPF = matcherPattern("^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}$", keyValue);
            System.out.println(matcherCPF);

            this.checkMatcher(matcherCPF);

            return matcherCPF;

        case CNPJ:
            Boolean matcherCNPJ = matcherPattern("^[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}-?[0-9]{2}$", keyValue);
            System.out.println(matcherCNPJ);
            return matcherCNPJ;

        case RANDOM_KEY:
            Boolean matcherRandomKey = matcherPattern("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", keyValue);
            System.out.println(matcherRandomKey);
            return matcherRandomKey;

        case PHONE:
            Boolean matcherPhone = matcherPattern("^((?:\\+?55)?)([1-9][0-9])(9[0-9]{8})$", keyValue);
            System.out.println(matcherPhone);
            return matcherPhone;

        case EMAIL:
            Boolean matcherEmail = matcherPattern("^[a-z0-9+_.-]+@[a-z0-9.-]+$", keyValue);
            System.out.println(matcherEmail);
            return matcherEmail;

        default:
            throw new Exception("Unknown KeyType: " + keyType);

        }
    }

    private boolean matcherPattern(String patternKey, String keyValue){
        Pattern pattern = Pattern.compile(patternKey);
        Matcher matcher = pattern.matcher(keyValue);
        return matcher.matches();
    }

    private Boolean checkMatcher(Boolean matcherCPF){

        if(!matcherCPF){
            throw new Exception("Invalid KeyType");
        }
        return true;
    };

}

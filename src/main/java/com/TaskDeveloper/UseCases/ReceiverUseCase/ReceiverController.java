package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.TypesStatus.TypeStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Receiver")
public class ReceiverController {
    @Autowired
    CreateReceiverUseCase _createReceiverUseCase;
    @Autowired
    GetReceiverUseCase _getReceiverUseCase;
    @Autowired
    UpdateReceiverUseCase _updateReceiverUseCase;
    @Autowired
    DeleteReceiverUseCase _deleteReceiverUseCase;
    @PostMapping("list")
    public ResponseEntity<String> createReceiverList(@Valid @RequestBody List<ReceiverDTO> receiverdto){
        try{
            return new ResponseEntity<>(_createReceiverUseCase.createReceiverList(receiverdto),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping()
    public ResponseEntity<String> createReceiver(@Valid @RequestBody ReceiverDTO receiverdto){
        try{
        return new ResponseEntity<>(_createReceiverUseCase.createReceiver(receiverdto),HttpStatus.CREATED);
        }catch(Exception e ){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("{pageNumber}")
    @ResponseBody
    public ResponseEntity<List<Receiver>> getAllReceivers(@PathVariable int pageNumber){

     return new ResponseEntity<>(this._getReceiverUseCase.getAllReceiver(pageNumber), HttpStatus.OK);
    }
    @GetMapping("name/{name}/{pageNumber}")
    @ResponseBody
    public ResponseEntity<List<Receiver>> getAllReceiversByName(@PathVariable String name,@PathVariable int pageNumber){

        return new ResponseEntity<>(this._getReceiverUseCase.getAllReceiverByName(name,pageNumber), HttpStatus.OK);
    }

    @GetMapping("status/{status}/{pageNumber}")
    @ResponseBody
    public ResponseEntity<List<Receiver>> getAllReceiversByStatus(@PathVariable int pageNumber,@PathVariable TypeStatus status){

        return new ResponseEntity<>(this._getReceiverUseCase.getAllReceiverByStatus(pageNumber,status), HttpStatus.OK);
    }


    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<String> updateReceiver(@PathVariable long id, @Valid @RequestBody ReceiverDTO receiverdto){
        return new ResponseEntity<>(this._updateReceiverUseCase.updateReceiver(id,receiverdto),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<String> deleteReceiver(@PathVariable long id){
        return new ResponseEntity<>(this._deleteReceiverUseCase.deleteReceiver(id),HttpStatus.OK);
    }


    @DeleteMapping("list")
    @ResponseBody
    public ResponseEntity<String> deleteReceivers(@RequestBody List<Long> id){
        return new ResponseEntity<>(this._deleteReceiverUseCase.deleteReceivers(id),HttpStatus.OK);
    }

}
    
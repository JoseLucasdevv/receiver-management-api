package com.TaskDeveloper.UseCases.ReceiverUseCase;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Receiver;
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
     return new ResponseEntity<>(_createReceiverUseCase.createReceiverList(receiverdto),HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<String> createReceiver(@Valid @RequestBody ReceiverDTO receiverdto){
        return new ResponseEntity<>(_createReceiverUseCase.createReceiver(receiverdto),HttpStatus.CREATED);
    }
    @GetMapping("{pageNumber}")
    @ResponseBody
    public ResponseEntity<List<Receiver>> getAllResources(@PathVariable int pageNumber){
     return new ResponseEntity<>(this._getReceiverUseCase.resourceAllReceiver(pageNumber), HttpStatus.OK);
    }
    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<String> updateReceiver(@PathVariable long id, @Valid @RequestBody ReceiverDTO receiverdto){
        return new ResponseEntity<>(this._updateReceiverUseCase.updateReceiver(id,receiverdto),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<String> updateReceiver(@PathVariable long id){
        return new ResponseEntity<>(this._deleteReceiverUseCase.deleteReceiver(id),HttpStatus.OK);
    }


}
    
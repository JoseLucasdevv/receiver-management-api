package com.TaskDeveloper.Dtos;
import com.TaskDeveloper.Entity.Pix;

import java.util.List;

public record ReceiverDTO(Long receiver_id, String name, String cpf, String email, List<Pix> pix) { }


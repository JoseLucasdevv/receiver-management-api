package com.TaskDeveloper.Dtos;
import com.TaskDeveloper.Entity.Pix;
import com.TaskDeveloper.TypesStatus.TypeStatus;

import java.util.List;

public record ReceiverDTO(Long receiver_id, String name, String cpfOrCnpj, String email, List<Pix> pix, TypeStatus status) { }


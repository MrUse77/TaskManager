package com.siemens.task_manager.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Usuarios")
public class usuarios {

    @Id
    private Long id;
    private String name;
    private String email;
    public usuarios(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    };
}

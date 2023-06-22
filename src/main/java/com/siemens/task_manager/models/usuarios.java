package com.siemens.task_manager.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Usuarios")
public class usuarios {
    @Id
    private String id;
    @Indexed(unique = true)
    private String numInt;
    private String alias;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    @CreatedDate
    private LocalDateTime createdDate;
    public usuarios(String email,String password){
        super();
        this.email = email;
        this.password = password;
    }
        public usuarios(String numInt,String alias,String name, String email, String password){
        super();
        this.numInt = numInt;
        this.alias = alias;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

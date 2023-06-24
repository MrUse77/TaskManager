package com.siemens.task_manager.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Usuarios")
public class usuarios implements UserDetails{
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
    private Boolean locked = false;
    private Boolean enabled = false;
    
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

    public usuarios(usuarios usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Collections.emptyList();
    }



    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
    return getEnabled() != null ? getEnabled() : false;
    }
 



        
}

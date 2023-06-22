/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.security;

import lombok.Data;

/**
 *
 * @author agusd
 */
@Data
public class AuthCredentials {
    private String email;
    private String password;
}
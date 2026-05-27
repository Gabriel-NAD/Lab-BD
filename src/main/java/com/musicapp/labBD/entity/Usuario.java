 package com.musicapp.labBD.entity;

 import jakarta.persistence.*;

 @Entity
 @Table(name = "usuario")
 public class Usuario {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false, unique = true)
     private String username;

     @Column(nullable = false, unique = true)
     private String email;

     public Usuario() {
     }

     public Usuario(String email, String username, Long id) {
         this.email = email;
         this.username = username;
         this.id = id;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }
 }
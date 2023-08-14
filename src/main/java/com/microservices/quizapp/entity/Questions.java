package com.microservices.quizapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    @Column(name = "questiontitle",nullable = false)
    private String questionTitle;

   
    @Column(name = "option1",nullable = false)
    private String option1;

 
    @Column(name = "option2",nullable = false)
    private String option2;

    @Column(name = "option3",nullable = false)
    private String option3;

    
    @Column(name = "option4", nullable = false)
    private String option4;
    
     
    @Column(name = "rightanswer",nullable = false)
    private String rightAnswer;

    @Column(name = "difficultylevel")
    private String difficultyLevel;
 
    //@Column(name = "category", columnDefinition = "varchar(255) default 'Basics'")
    @Column(name = "category")
    private String category;


    
    public Questions() {
        
        this.difficultyLevel = "Easy";
        this.category = "Basics";
    }
    
    
}


package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "QuestionCatagory",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "questionDesc"
            })
    })
public class QuestionCatagory {

}

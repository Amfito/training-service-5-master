package com.accenture.ems.emstraining.business.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainingDetails")
public class TrainingDetailsDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "training_id")
    private TrainingDAO training;

    @Column(name = "employee_id")
    private Long employee;

    @Column(name = "mentor_relation_id")
    private Long employeeRelation;

    @Column(name = "final_presentation_date")
    private String finalPresentationDate;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    public TrainingDetailsDAO(Long trainingDetails ){

    }
}

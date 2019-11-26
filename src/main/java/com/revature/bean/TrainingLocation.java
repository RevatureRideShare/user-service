package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "training_location")
public class TrainingLocation {

  @Id
  @SequenceGenerator(name = "TL_SEQ", sequenceName = "training_location_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TL_SEQ")
  @Column(name = "training_location_id")
  int trainingLocationID;

  @Column(name = "training_location_name")
  String trainingLocationName;

}

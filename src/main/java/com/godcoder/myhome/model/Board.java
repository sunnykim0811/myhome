package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa 설정을 위해서 이 클래스가 데이터 베이스 연동을 위한 모델 클래스라는 걸 알려줌
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

}

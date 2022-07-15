package com.godcoder.myhome.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity //jpa 설정을 위해서 이 클래스가 데이터 베이스 연동을 위한 모델 클래스라는 걸 알려줌
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "글자 수는 2자 이상, 30자 이하여야 합니다.")
    private String title;
    private String content;

}

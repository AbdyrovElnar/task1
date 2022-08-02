package com.example.task1.enity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    private String inn;

    private String telNumber;
}

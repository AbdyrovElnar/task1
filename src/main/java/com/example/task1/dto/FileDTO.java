package com.example.task1.dto;

import com.example.task1.enity.FileInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Long id;
    private String name;
    private String department;
    private String inn;
    private String telNumber;

    public static FileDTO from(FileInfo file) {
        return builder()
                .id(file.getId())
                .name(file.getName())
                .department(file.getDepartment())
                .inn(file.getInn())
                .telNumber(file.getTelNumber())
                .build();
    }

}

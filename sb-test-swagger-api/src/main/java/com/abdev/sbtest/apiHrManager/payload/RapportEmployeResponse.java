package com.abdev.sbtest.apiHrManager.payload;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RapportEmployeResponse {
    @CsvBindByPosition(position = 0)
    private Long    id;
    @CsvBindByPosition(position = 1)
    private String  nameProject;
    @CsvBindByPosition(position = 2)
    private String timeAdd;
    @CsvBindByPosition(position = 3)
    private String    timeNow;
}

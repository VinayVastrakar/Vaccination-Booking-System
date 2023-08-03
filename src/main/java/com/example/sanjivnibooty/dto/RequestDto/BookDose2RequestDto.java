package com.example.sanjivnibooty.dto.RequestDto;

import com.example.sanjivnibooty.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PACKAGE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDose2RequestDto {

    int personId ;
    DoseType doseType;
}

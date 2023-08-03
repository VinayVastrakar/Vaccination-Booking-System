package com.example.sanjivnibooty.dto.ResponseDto;

import com.example.sanjivnibooty.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CenterRersonseDto {

    String centerName;
    CenterType centerType;
    String address;
}

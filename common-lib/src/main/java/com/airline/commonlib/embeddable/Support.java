package com.airline.commonlib.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Support {
    private String email;
    private String phone;
    private String hours;
}

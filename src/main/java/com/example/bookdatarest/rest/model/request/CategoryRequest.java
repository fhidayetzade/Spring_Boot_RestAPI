package com.example.bookdatarest.rest.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {

    String name;
    String description;
}

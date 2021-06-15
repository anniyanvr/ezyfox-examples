package com.tvd12.ezydata.example.jpa.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryData {
    private final long id;
    private final String name;
}

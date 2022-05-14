package com.Project_2_Location_Search_API.entities;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MapRequest {
    private String center;
    private String marker1;
    private String marker2;
    private String path;
}

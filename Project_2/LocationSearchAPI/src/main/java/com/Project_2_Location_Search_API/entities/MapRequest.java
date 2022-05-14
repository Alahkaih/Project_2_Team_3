package com.Project_2_Location_Search_API.entities;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MapRequest {

    // String center, String zoom, String size, String marker1, String marker2, String path
    private String center;
    private String marker1;
    private String marker2;
    private String path;
}

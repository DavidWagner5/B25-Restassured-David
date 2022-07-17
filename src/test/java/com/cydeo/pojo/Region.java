package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Data

public class Region {

    @JsonProperty("region_id")  //use json propery in case in the Json file, there is a space.
    private int regionId;
    private String region_name;
    private List<Link> links;



}

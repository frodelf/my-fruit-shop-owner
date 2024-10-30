package test.myfruitshopowner.dto;

import lombok.Data;

@Data
public class FilterDto {
    int page;
    int pageSize;
    String query;
}
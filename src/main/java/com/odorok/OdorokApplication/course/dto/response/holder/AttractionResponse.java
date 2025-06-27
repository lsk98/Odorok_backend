package com.odorok.OdorokApplication.course.dto.response.holder;

import com.odorok.OdorokApplication.course.dto.response.item.AttractionSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionResponse {
    private Integer contentTypeId;
    private List<AttractionSummary> items;
}

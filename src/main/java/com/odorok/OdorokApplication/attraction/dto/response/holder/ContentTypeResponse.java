package com.odorok.OdorokApplication.attraction.dto.response.holder;

import com.odorok.OdorokApplication.attraction.dto.response.item.ContentTypeSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentTypeResponse {
    private List<ContentTypeSummary> items;
}

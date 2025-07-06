package com.odorok.OdorokApplication.region.dto.response.holder;

import com.odorok.OdorokApplication.region.dto.response.item.SidoSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SidoResponse {
    private List<SidoSummary> items;
}

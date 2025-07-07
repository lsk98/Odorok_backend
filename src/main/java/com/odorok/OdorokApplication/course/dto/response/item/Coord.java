package com.odorok.OdorokApplication.course.dto.response.item;

import com.odorok.OdorokApplication.course.repository.PathCoordRepository;
import com.odorok.OdorokApplication.infrastructures.domain.PathCoord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coord {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Integer ordering;

    public Coord(PathCoord coord) {
        this.id = coord.getId();
        this.latitude = coord.getLatitude();
        this.longitude = coord.getLongitude();
        this.ordering = coord.getOrdering();
    }
}

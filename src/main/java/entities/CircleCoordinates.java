package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "circle_coordinates")

public class CircleCoordinates {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "center_x")
    private double centerX;

    @Column(name = "center_y")
    private double centerY;

    @Column(name = "point_x")
    private double pointX;

    @Column(name = "point_y")
    private double pointY;
}

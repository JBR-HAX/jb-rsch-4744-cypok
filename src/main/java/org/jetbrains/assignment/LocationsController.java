package org.jetbrains.assignment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationsController {

    @PostMapping("/locations")
    public List<Point> locations(@RequestBody List<Move> moves) {
        List<Point> locations = new ArrayList<>();
        Point point = new Point(0, 0);
        locations.add(new Point(point)); // add the initial point

        for (Move move : moves) {
            switch (move.getDirection().toUpperCase()) {
                case "NORTH" -> point.setY(point.getY() + move.getSteps());
                case "SOUTH" -> point.setY(point.getY() - move.getSteps());
                case "EAST" -> point.setX(point.getX() + move.getSteps());
                case "WEST" -> point.setX(point.getX() - move.getSteps());
            }
            locations.add(new Point(point)); // add the updated point
        }
        return locations;
    }
}
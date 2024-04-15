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

    @PostMapping("/moves")
    public List<Move> getMoves(@RequestBody List<Point> points) {
        List<Move> moves = new ArrayList<>();
        Point currentPoint = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            Point nextPoint = points.get(i);
            moves.addAll(getMovesToNextPoint(currentPoint, nextPoint));
            currentPoint = nextPoint;
        }

        return moves;
    }

    private List<Move> getMovesToNextPoint(Point current, Point next) {
        List<Move> moves = new ArrayList<>();
        int xDiff = next.getX() - current.getX();
        int yDiff = next.getY() - current.getY();

        if (xDiff > 0) {
            moves.add(new Move("EAST", xDiff));
        } else if (xDiff < 0) {
            moves.add(new Move("WEST", -xDiff));
        }

        if (yDiff > 0) {
            moves.add(new Move("NORTH", yDiff));
        } else if (yDiff < 0) {
            moves.add(new Move("SOUTH", -yDiff));
        }

        return moves;
    }
}
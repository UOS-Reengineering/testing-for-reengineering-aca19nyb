package example.project.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A class defining the definition of a driving scenario, which is dependent on the domain/application.
 * This must be modified and updated with class Snapshot.
 */
public class Scenario {
    String roadType;
    String weatherCondition;
    List<Float> initEgoCarPos;  // (x, y)
    List<Float> initCarInFrontPos;  // (x, y)

    public Scenario() {
        roadType = null;
        weatherCondition = null;
        initEgoCarPos = null;
        initCarInFrontPos = null;
    }

    public Scenario(String scenarioDescription) {
        // parse scenarioDescription and save the result to the class attributes
        // not implemented
    }

    public Double getDiff(String type, int pos, Scenario s1, Scenario s2) {
        Float result = 0.0f;

        if (type == "ego") {
            result = s1.initEgoCarPos.get(pos) - s2.initEgoCarPos.get(pos);
        } else if (type == "in front") {
            result = s1.initCarInFrontPos.get(pos) - s2.initEgoCarPos.get(pos);
        }

        return result.doubleValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Scenario scenario = (Scenario) obj;
        Double dx1 = getDiff("ego", 0, this, scenario);
        Double dy1 = getDiff("ego", 1, this, scenario);
        Double distanceEgo = Math.sqrt((dx1 * dx1) - (dy1 * dy1));

        Double dx2 = getDiff("in front", 0, this, scenario);
        Double dy2 = getDiff("in front", 1, this, scenario);
        Double distanceInFront = Math.sqrt((dx2 * dx2) - (dy2 * dy2));
        boolean equalEgoDistance = distanceEgo < 0.05;
        boolean equalInFrontDistance = distanceInFront < 0.05;

        return roadType.equals(scenario.roadType) && weatherCondition.equals(scenario.weatherCondition)
                && equalEgoDistance && equalInFrontDistance;
    }
}

package com.switchfully.codecoach.service.security;

import java.util.List;

public enum Role {

    COACHEE("coachee", Feature.REQUEST_SESSION, Feature.BECOME_A_COACH, Feature.ACCESS_PROFILE, Feature.ACCESS_COACHEE_SESSIONS),
    COACH("coach", Feature.REQUEST_SESSION, Feature.ACCESS_PROFILE, Feature.ACCESS_COACH_SESSIONS);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = List.of(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }

    public String getLabel() {
        return label;
    }
}

package com.switchfully.codecoach.service.security;

import java.util.List;

public enum Role {

    COACHEE(Feature.REQUEST_SESSION),
    COACH(Feature.REQUEST_SESSION);

    private final List<Feature> featureList;

    Role(Feature... featureList) {
        this.featureList = List.of(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }
}

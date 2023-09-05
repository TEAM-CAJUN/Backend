package com.cajun.mds.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

public class RegionKey implements Serializable {

    private Integer regionCode;
    private Integer dongCode;
}

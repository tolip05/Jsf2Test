package com.test.models;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class Municipality extends ModelWithId {
    private String municipalityName;

    public Municipality() {
        super(NOT_DEFINED_ID);
    }

    public Municipality(Long id) {
        super(id);
    }

    @PojoCreator
    public Municipality(@PojoProperty("municipality_id") Long id,
                        @PojoProperty("municipality_name") String municipalityName) {
        super(id);
        this.municipalityName = municipalityName;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }
}

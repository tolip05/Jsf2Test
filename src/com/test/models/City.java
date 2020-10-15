package com.test.models;
import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;
public class City extends ModelWithId{
    private String cityName;
    private int kindCity;
    private Municipality municipality;
    
    public City() {
        super(NOT_DEFINED_ID);
    }

    public City(Long id) {
        super(id);
    }

    @PojoCreator
    public City(@PojoProperty("city_id") Long id,
                @PojoProperty("city_name") String cityName,
                @PojoProperty("kind_city") int kindCity,
                @PojoProperty("municipality_id") Municipality municipality) {
        super(id);
        this.cityName = cityName;
        this.kindCity = kindCity;
        this.municipality = municipality;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getKindCity() {
        return kindCity;
    }

    public void setKindCity(int kindCity) {
        this.kindCity = kindCity;
    }
    
    public Municipality getMunicipality() {
		return municipality;
	}
    public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}
}

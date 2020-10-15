package com.test.models;
import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;
public class CityClass extends ModelWithId{
	
	private String cityName;
    private int kindCity;
    private long municipality;
    
    public CityClass() {
    	super(NOT_DEFINED_ID);
    }
    @PojoCreator
    public CityClass(@PojoProperty("city_id") Long id,
                @PojoProperty("city_name") String cityName,
                @PojoProperty("kind_city") int kindCity,
                @PojoProperty("municipality_id") long municipality) {
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

	public long getMunicipality() {
		return municipality;
	}

	public void setMunicipality(long municipality) {
		this.municipality = municipality;
	}

}

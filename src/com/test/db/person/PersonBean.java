package com.test.db.person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.address.AddressBean;
import com.test.db.address.builder.AddressSelectByAnyCriteriasBuilder;
import com.test.db.address.builder.AddressSelectByCriteriaBuilder;
import com.test.db.city.CitiesBean3;
import com.test.db.pack.MidlleBean;
import com.test.db.pack.PackageBean;
import com.test.db.municipality.builder.MunicipalitySelectAllBuilder;
import com.test.db.municipality.builder.MunicipalitySelectByIdBuilder;
import com.test.db.municipality.builder.MunicipalitySelectByMultipleIdsBuilder;
import com.test.db.person.builder.PersonSelectByCriteriaAnyCityBuilder;
import com.test.db.person.builder.PersonSelectByCriteriaBuilder;
import com.test.helpers.AddressHelper;
import com.test.helpers.CityHelper;
import com.test.helpers.PackageHelper;
import com.test.helpers.PersonHelper;
import com.test.models.Address;
import com.test.models.City;
import com.test.models.Municipality;
import com.test.models.Person;

@ManagedBean(name = "pBean")
@SessionScoped
public class PersonBean {
	@ManagedProperty(value = "#{cBean}")
	private CitiesBean3 cityBean;
	@ManagedProperty(value="#{mBean}")
	private MidlleBean midBean;

	private Address address;
	private Person editObject;
	private List<Person> persons;
	private List<Address> addresses;
	private List<City> cities;
	private List<Municipality> municipalities;
	private Set<String> streets;
	private List<Integer> streetsNumbers;
	private List<Long> addressesIds;
	private Long municipalityId;
	private final static int PAGE_SIZE = 20;
	private int currentPage;
	private enum ACTION {
		ADD, EDIT, EDITPAC ,CHANGEPAC
	}

	private ACTION currentAction;
	private Long addressId;
	private String firstName;
	private String lastName;
	private String uid;
	private String gender;
	private String cityName;
	private Long cityId;
	private String street;
	private Integer numberOfStreet;
	private String municipalityName;
	private Integer city;
	

	public PersonBean() throws PojoCreationException, EmptyResultSetException, SQLException {
		this.cities = new ArrayList<>();
		this.addresses = new ArrayList<>();
		this.municipalities = new ArrayList<>();
		goTo(1);
	}
	
	private int getRowBegin() {
		return (currentPage - 1) * PAGE_SIZE + 1;
	}

	private int getRowEnd() {
		return getRowBegin() + PAGE_SIZE - 1;
	}
	
	
	private void goTo(int page) {
		currentPage = page;
		loadPageData();
	}
	
	private void loadPageData() {
		try {
			
			if((cityName == null || cityName.isEmpty()) &&
					this.numberOfStreet == null && 
					(this.street == null || this.street.isEmpty())) {
				persons = PojoHelper.fillListOfPojos(Person.class, PersonSelectByCriteriaBuilder.create()
						.setParam1_FirstName(firstName != null && !firstName.isEmpty() ? firstName.toUpperCase() : null)
						.setParam2_LastName(lastName != null && !lastName.isEmpty() ? lastName.toUpperCase() : null)
						.setParam3_Uid(uid != null && !uid.isEmpty() ? uid.toUpperCase() : null)
						.setParam4_AddressId(addressId != null ? addressId : null)
						.setParam5_Gender(gender != null && !gender.isEmpty() ? gender.toUpperCase() : null)
						.setParam6_RowBegin(getRowBegin())
						.setParam7_RowEnd(getRowEnd())
						.build()
						.selectListObjects());
			}else {
				persons = PojoHelper.fillListOfPojos(Person.class, PersonSelectByCriteriaAnyCityBuilder.create()
						.setParam1_FirstName(firstName != null &&  !firstName.isEmpty() ? firstName.toUpperCase() : null)
						.setParam2_LastName(lastName != null && !lastName.isEmpty() ? lastName.toUpperCase() : null)
						.setParam3_Uid(uid != null && !uid.isEmpty() ? uid.toUpperCase() : null)
						.setParam4_AddressId(this.addressesIds)
						.setParam5_Gender(gender != null && !gender.isEmpty() ? gender.toUpperCase() : null)
						.setParam6_RowBegin(getRowBegin())
						.setParam7_RowEnd(getRowEnd())
						.build()
						.selectListObjects());
			}
		} catch (PojoCreationException  | SQLException e) {
			e.printStackTrace();
		}catch(EmptyResultSetException e) {
	     	persons = new ArrayList<>();
		}
		try {
			fillCollections();
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void fillCollections() throws PojoCreationException, EmptyResultSetException, SQLException {
		List<Long>addressIds = new ArrayList<>();
		List<Long>citiesIds = new ArrayList<>();
		List<Long>municipalityIds = new ArrayList<>();
        
		addressIds = persons.stream()
				.map(Person::getAddress)
				.map(Address::getId)
				.collect(Collectors.toList());
		addresses = AddressHelper.selectAddressByIds(addressIds);
		
		citiesIds = addresses.stream()
				.map(Address::getCity)
				.map(City::getId)
				.collect(Collectors.toList());
		
		cities = CityHelper.selectCityByMultiplyId(citiesIds);
		
		municipalityIds = cities.stream()
				.map(City::getMunicipality)
				.map(Municipality::getId)
				.collect(Collectors.toList());
		
		municipalities = PojoHelper.fillListOfPojos(Municipality.class, MunicipalitySelectByMultipleIdsBuilder.create()
				.setParam1_MunicipalityIds(municipalityIds).build().selectListObjects());
		
		fillObjects();
	}
	
	private void fillObjects() {
		
		Map<Long, Municipality> municipalityById = municipalities.stream()
				.collect(Collectors.toMap(Municipality::getId, x -> x));
		cities.forEach(city -> city.setMunicipality(municipalityById.get(city.getMunicipality().getId())));
		Map<Long,City> cityMap = cities.stream().collect(Collectors.toMap(City::getId, city -> city));
		addresses.forEach(address -> address.setCity(cityMap.get(address.getCity().getId())));
		Map<Long,Address> addressMap = addresses.stream().collect(Collectors.toMap(Address::getId, address->address));
		persons.forEach(person -> person.setAddress(addressMap.get(person.getAddress().getId())));
	}
	public void nextPage() {
		goTo(currentPage + 1);
	}

	public void prevPage() {
		goTo(currentPage - 1);
	}

	public void firstPage() {
		goTo(1);
	}

	public void lastPage() {
		goTo(persons != null && !persons.isEmpty()
				? (persons.get(0).getRowsCount() / PAGE_SIZE) + (persons.get(0).getRowsCount() % PAGE_SIZE > 0 ? 1 : 0)
				: 1);
	}
	
	public void addPerson() {
		municipalityId = null;
		cityName = null;
		cityId = null;
		street = null;
		numberOfStreet = null;
		streets = new HashSet<>();
		streetsNumbers = new ArrayList<>();
		editObject = new Person();
		Municipality municipality = new Municipality();
		City city= new City();
		Address address = new Address();
		city.setMunicipality(municipality);
		address.setCity(city);
		editObject.setAddress(address);
		currentAction = ACTION.ADD;
	}
	
	
	public void save() throws SQLException, PojoCreationException, EmptyResultSetException, IOException  {
		
		switch (currentAction) {
		case ADD:
			addressId = addresses.stream().filter(a-> a.getNumberOfStreet() == numberOfStreet).map(Address::getId)
			.findFirst().orElse(null);
			editObject.getAddress().setId(addressId);
			PersonHelper.insertPerson(editObject);
			
			break;

		case EDIT:
			addressId = addresses.stream().filter(a-> a.getNumberOfStreet() == numberOfStreet).map(Address::getId)
			.findFirst().orElse(null);
			editObject.getAddress().setId(addressId);
			PersonHelper.update(editObject);
			break;
		case EDITPAC:
			addressId = addresses.stream().filter(a-> a.getNumberOfStreet() == numberOfStreet).map(Address::getId)
			.findFirst().orElse(null);
			Long addresIdForSend = addressId;
			midBean.startPckBean(addresIdForSend);
			clearFilters();
			break;
		case CHANGEPAC:
			Long addresId = null;
			try {
				addressId = addresses.stream()
						.filter(s -> s.getStreet().equals(street))
						.filter(a-> a.getNumberOfStreet() == numberOfStreet).map(Address::getId)
						.findFirst().orElse(null);
			}catch(NullPointerException n) {
				addresId = null;
			}
			midBean.setIdSender(addressId);
			midBean.editPackageBean();
			clearFilters();
			break;
		}
		if(!currentAction.equals(ACTION.EDITPAC) || !currentAction.equals(ACTION.CHANGEPAC)) {
			addressId = null;
			goTo(1);
		}
		
	}
	
	
	public void changePackage () throws SQLException, PojoCreationException, EmptyResultSetException, IOException {
		currentAction = ACTION.CHANGEPAC;
		save();
	}
	
    public void start() throws SQLException, PojoCreationException, EmptyResultSetException, IOException {
    	currentAction = ACTION.EDITPAC;
    	save();
	}
    
	public boolean isNextPageDisabled() {
	  return persons != null && !persons.isEmpty() ? persons.get(0).getRowsCount() <= currentPage * PAGE_SIZE : true;
	}
	
	public void edit(Person person) {
		editObject = person;
		currentAction = ACTION.EDIT;
	}
	
	public void selectAll() {
		clearFilters();
		goTo(1);
	}
	
	public void searchPerson() throws PojoCreationException, EmptyResultSetException, SQLException {
		if(null == this.cityName || this.cityName.isEmpty()) {
			this.addresses = PojoHelper.fillListOfPojos(Address.class,
					AddressSelectByCriteriaBuilder.create().setParam1_City_id(null)
							.setParam2_Street(street != null && !street.isEmpty() ? street.toUpperCase() : null)
							.setParam3_Number_of_street(numberOfStreet != null ? numberOfStreet : null)
							.setParam4_RowBegin(getRowBegin()).setParam5_RowEnd(getRowEnd()).build()
							.selectListObjects());
		}else {
			this.addresses = PojoHelper.fillListOfPojos(Address.class,
					AddressSelectByAnyCriteriasBuilder.create()
					.setParam1_CitiesIds(searchCityIds(this.cityName))
					.setParam2_Street(street != null && !street.isEmpty() ? street.toUpperCase() : null)
					.setParam3_Number_of_street(numberOfStreet != null ? numberOfStreet : null)
					.setParam4_RowBegin(getRowBegin()).setParam5_RowEnd(getRowEnd()).build()
					.selectListObjects());
		}
	    addressesIds = this.addresses.stream().map(Address::getId).collect(Collectors.toList());
		goTo(1);
	}
	
	private List<Integer> searchCityIds(String cityName) throws PojoCreationException, EmptyResultSetException, SQLException{
		List<Integer> citiesIds = new ArrayList();
		cities = CityHelper.selectCityObjectByCityName(cityName);
		citiesIds = cities.stream().map(City::getId).map(a -> a.intValue())
				.collect(Collectors.toList());
		return citiesIds;
	}
	
	
	public void clearFilters() {
		addressId = null;
		firstName = null;
		lastName = null;
	    uid = null;
		gender = null;
		cityId = null;
		street = null;
		numberOfStreet = null;
		municipalityName = null;
		city = null;
		cityName = null;
	}
	
	
	public void onMunicipalityChange() throws PojoCreationException, SQLException{
		this.cityId = null;
		this.street = null;
		this.numberOfStreet = null;
		 if(municipalityId !=null && !municipalityId.equals("")) {
			 cities = CityHelper.selectCitiesByMunicipalityId(municipalityId);
		 }else {
			 cities = new ArrayList<>();
		 }
	}
	
	public void onCityChange() throws PojoCreationException, EmptyResultSetException, SQLException {
		this.street = null;
		this.numberOfStreet = null;
		if(cityId != null) {
			try {
				addresses = AddressHelper.selectAddressByCityId(cityId);
			}catch(EmptyResultSetException e) {
				addresses = new ArrayList<>();
			}
		    streets = addresses.stream().map(a -> a.getStreet()).collect(Collectors.toSet());
		    if(streets == null || streets.isEmpty()) {
		    	streets = new HashSet<>();
		    	streets.add("Empty");
		    }
		}else {
			streets = new HashSet<>();
	    	streets.add("Empty");
		}
	}
	
	public void onStreetChange() {
		this.numberOfStreet = null;
		if(street !=null && !street.equals("")) {
			List<Address>filterAddresses = new ArrayList<>();
			filterAddresses = addresses.stream().filter(a -> a.getStreet().equals(street))
					.collect(Collectors.toList());
			streetsNumbers = filterAddresses.stream().map(Address::getNumberOfStreet).collect(Collectors.toList());
			Collections.sort(streetsNumbers,(a1,a2) -> a1.compareTo(a2));
		}else {
			streetsNumbers.add(1);
		}
		
	}
	public void clearCollection() {
		cities = new ArrayList<>();
		streetsNumbers = new ArrayList<>();
	}
	
	public Set<String> getStreets() {
		return streets;
	}

	public void setStreets(Set<String> streets) {
		this.streets = streets;
	}
	
	public Integer getNumberOfStreet() {
		return numberOfStreet;
	}

	public void setNumberOfStreet(Integer numberOfStreet) {
		this.numberOfStreet = numberOfStreet;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	public List<Integer> getStreetsNumbers() {
		return streetsNumbers;
	}

	public void setStreetsNumbers(List<Integer> streetsNumbers) {
		this.streetsNumbers = streetsNumbers;
	}
	
	public Long getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public CitiesBean3 getCityBean() {
		return cityBean;
	}

	public void setCityBean(CitiesBean3 cityBean) {
		this.cityBean = cityBean;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	
	public Person getEditObject() {
		return editObject;
	}


	public void setEditObject(Person editObject) {
		this.editObject = editObject;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void getAddressById(long id) {
		
	}
	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	public List<City> getCities() {
		return cities;
	}


	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	

	public String getMunicipalityName() {
		return municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}
	
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}
	
	 public List<Long> getAddressesIds() {
			return addressesIds;
		}

		public void setAddressesIds(List<Long> addressesIds) {
			this.addressesIds = addressesIds;
		}
		
		public MidlleBean getMidBean() {
			return midBean;
		}

		public void setMidBean(MidlleBean midBean) {
			this.midBean = midBean;
		}

		
}

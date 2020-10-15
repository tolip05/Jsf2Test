package com.test.db.pack;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.address.builder.AddressSelectByAnyCriteriasBuilder;
import com.test.db.address.builder.AddressSelectByCityIdAndStreetBuilder;
import com.test.db.address.builder.AddressSelectByCriteriaBuilder;
import com.test.db.address.builder.AddressSelectByIdBuilder;
import com.test.db.city.builder.CitySelectByNameBuilder;
import com.test.db.municipality.builder.MunicipalitySelectByMultipleIdsBuilder;
import com.test.db.pack.builder.PackageInsertIntoDbBuilder;
import com.test.db.pack.builder.PackageSelectByCriteriaBuilder;
import com.test.db.pack.builder.PackageSelectBySearchCriteriaBuilder;
import com.test.db.pack.builder.PackageUpdateBuilder;
import com.test.db.pack.builder.PackageUpdateWithoutDateBuilder;
import com.test.db.panda.builder.PandaUserSelectByEmeilBuilder;
import com.test.db.panda.builder.PandaUserSelectByIdsBuilder;
import com.test.db.panda.builder.PandaUserSelectByNameBuilder;
import com.test.db.person.PersonBean;
import com.test.db.register.builder.UserSelectByEmeilBuilder;
import com.test.helpers.AddressHelper;
import com.test.helpers.CityHelper;
import com.test.helpers.PackageHelper;
import com.test.models.Address;
import com.test.models.City;
import com.test.models.Municipality;
import com.test.models.Person;
import com.test.models.UserPanda;
import com.test.models.Package;

@ManagedBean(name = "pacBean")
@SessionScoped
public class PackageBean {

	private Package editObject;
	private String userEmail;
	private Long idSenderFromMidlleBean;
	private List<Package> packages;
	private List<UserPanda> users;
	private List<Address> addresses;
	private List<Municipality> municipalities;
	private List<City> cities;
	private List<Long> addressIds;
	private Long userId;
	private final static int PAGE_SIZE = 20;
	private final static String USER_PANDA = "User";
	private final static Long SET_USER_ID_WHEN_IS_NULL = Long.MAX_VALUE - 1;
	private int currentPage;
	private String sender;
	private String cityName;
	private String streetName;
	private Integer streetNumber;

	public PackageBean() {
		editObject = new Package();
		this.packages = new ArrayList<>();
		this.users = new ArrayList<>();
		this.addresses = new ArrayList<>();
		this.municipalities = new ArrayList<>();
		this.cities = new ArrayList<>();
		this.addressIds = new ArrayList<>();
	}

	public void edit(Package object) {
		this.editObject = object;
	}
	
	private List<Integer> searchCityIds(String cityName)
			throws PojoCreationException, EmptyResultSetException, SQLException {
		List<Integer> citiesIds = new ArrayList();
		try {
			cities = CityHelper.selectCityObjectByCityName(cityName);
		}catch(EmptyResultSetException e) {
			cities = new ArrayList<>();
		}
		
		citiesIds = cities.stream().map(City::getId).map(a -> a.intValue()).collect(Collectors.toList());
		return citiesIds;
	}

	public void searchPackage() throws PojoCreationException, EmptyResultSetException, SQLException {
		this.currentPage = 1;
		if (null == this.cityName || this.cityName.isEmpty()) {
		try {
			this.addresses = PojoHelper.fillListOfPojos(Address.class, AddressSelectByCriteriaBuilder.create()
					.setParam1_City_id(null)
					.setParam2_Street(streetName != null && !streetName.isEmpty() ? streetName.toUpperCase() : null)
					.setParam3_Number_of_street(streetNumber != null ? streetNumber : null)
					.setParam4_RowBegin(getRowBegin())
					.setParam5_RowEnd(getRowEnd())
					.build()
					.selectListObjects());
		}catch(EmptyResultSetException e) {
			this.addresses =  new ArrayList<>();
		}	
		} else {
			try {
				this.addresses = PojoHelper.fillListOfPojos(Address.class, AddressSelectByAnyCriteriasBuilder.create()
						.setParam1_CitiesIds(searchCityIds(this.cityName))
						.setParam2_Street(streetName != null && !streetName.isEmpty() ? streetName.toUpperCase() : null)
						.setParam3_Number_of_street(streetNumber != null ? streetNumber : null)
						.setParam4_RowBegin(getRowBegin()).setParam5_RowEnd(getRowEnd()).build().selectListObjects());
			}catch(EmptyResultSetException e) {
				this.addresses =  new ArrayList<>();
			}	
			
		}
		addressIds = this.addresses.stream().map(Address::getId).collect(Collectors.toList());
		goTo(1);
	}

	public void loadPageData() {
		if ((cityName == null || cityName.isEmpty()) && this.streetNumber == null
				&& (this.streetName == null || this.streetName.isEmpty())) {
			try {
				this.packages = PojoHelper.fillListOfPojos(Package.class,
						PackageSelectByCriteriaBuilder.create()
								.setParam1_Id(editObject.getId() != null ? editObject.getId() : null)
								.setParam2_Title(editObject.getTitle() != null && !editObject.getTitle().isEmpty()
										? editObject.getTitle().toUpperCase()
										: null)
								.setParam3_Description(null)
								.setParam4_Weight(editObject.getWeight() != null ? editObject.getWeight() : null)
								.setParam5_ShippingAddress(editObject.getShippingAddress() != null
										? editObject.getShippingAddress().getId()
										: null)
								.setParam6_Status(editObject.getStatus() != null && !editObject.getStatus().isEmpty()
										? editObject.getStatus().toUpperCase()
										: null)
								.setParam7_Recipient(
										editObject.getRecipient() != null && !editObject.getRecipient().isEmpty()
												? editObject.getRecipient().toUpperCase()
												: null)
								.setParam8_EstimatedDeliveryTime(editObject.getEstimatedDeliveryTime() != null
										? editObject.getEstimatedDeliveryTime()
										: null)
								.setParam9_UserPanda(
										editObject.getUserPanda() != null ? editObject.getUserPanda().getId() : null)
								.setParam10_RowBegin(getRowBegin())
								.setParam11_RowEnd(getRowEnd()).build()
								.selectListObjects());
			} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
				this.packages = new ArrayList<>();
			}
			fillListUserData();
		} else {
			try {
				this.packages = PojoHelper.fillListOfPojos(Package.class, PackageSelectBySearchCriteriaBuilder.create()
						.setParam1_Id(editObject.getId() != null ? editObject.getId() : null)
						.setParam2_Title(editObject.getTitle() != null && !editObject.getTitle().isEmpty()
								? editObject.getTitle().toUpperCase()
								: null)
						.setParam3_Description(null)
						.setParam4_Weight(editObject.getWeight() != null ? editObject.getWeight() : null)
						.setParam5_ShippingAddress(addressIds)
						.setParam6_Status(editObject.getStatus() != null && !editObject.getStatus().isEmpty()
								? editObject.getStatus().toUpperCase()
								: null)
						.setParam7_Recipient(editObject.getRecipient() != null && !editObject.getRecipient().isEmpty()
								? editObject.getRecipient().toUpperCase()
								: null)
						.setParam8_EstimatedDeliveryTime(
								editObject.getEstimatedDeliveryTime() != null ? editObject.getEstimatedDeliveryTime()
										: null)
						.setParam9_UserPanda(
								editObject.getUserPanda() != null ? editObject.getUserPanda().getId() : null)
						.setParam10_RowBegin(getRowBegin())
						.setParam11_RowEnd(getRowEnd()).build().selectListObjects());
			} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
				this.packages = new ArrayList<>();
			}
			fillListUserData();
		}
         
	}

	private void fillListUserData() {
		List<Long> usersIds = new ArrayList<>();
		List<Long> addressIdsies = new ArrayList<>();;
		List<Long> municipalityIds = new ArrayList<>();
		List<Long> citiesIds = new ArrayList<>();

		usersIds = this.packages.stream().map(Package::getUserPanda).map(UserPanda::getId).collect(Collectors.toList());

		addressIdsies = this.packages.stream().map(Package::getShippingAddress).map(Address::getId)
				.collect(Collectors.toList());
		try {
			this.users = PojoHelper.fillListOfPojos(UserPanda.class,
					PandaUserSelectByIdsBuilder.create().setParam1_Ids(usersIds).build().selectListObjects());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			this.users = new ArrayList<>();
		}
		Map<Long, UserPanda> userMap = this.users.stream().collect(Collectors.toMap(UserPanda::getId, u -> u));
		this.packages.forEach(p -> p.setUserPanda(userMap.get(p.getUserPanda().getId())));
		try {
			this.addresses = AddressHelper.selectAddressByIds(addressIdsies);
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			this.addresses = new ArrayList<>();
		}
		citiesIds = this.addresses.stream().map(Address::getCity).map(City::getId).collect(Collectors.toList());
		try {
			this.cities = CityHelper.selectCityByMultiplyId(citiesIds);
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			this.cities = new ArrayList<>();
		}
		municipalityIds = this.cities.stream().map(City::getMunicipality).map(Municipality::getId)
				.collect(Collectors.toList());
		try {
			this.municipalities = PojoHelper.fillListOfPojos(Municipality.class, MunicipalitySelectByMultipleIdsBuilder
					.create().setParam1_MunicipalityIds(municipalityIds).build().selectListObjects());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			this.municipalities = new ArrayList<>();
		}
		Map<Long, Municipality> municipalityMap = this.municipalities.stream()
				.collect(Collectors.toMap(Municipality::getId, m -> m));
		this.cities.forEach(c -> c.setMunicipality(municipalityMap.get(c.getMunicipality().getId())));

		Map<Long, City> cityMap = this.cities.stream().collect(Collectors.toMap(City::getId, c -> c));
		this.addresses.forEach(a -> a.setCity(cityMap.get(a.getCity().getId())));
		Map<Long, Address> addressMap = this.addresses.stream().collect(Collectors.toMap(Address::getId, a -> a));
		this.packages.forEach(p -> p.setShippingAddress(addressMap.get(p.getShippingAddress().getId())));

		try {
			redirectIntoAllReceipts();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	}

	private void redirectIntoAllReceipts() throws IOException {
		clearObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/jsfVersion2/all-receipts.jsf");
	}

	public void save(Long addressId) throws PojoCreationException, EmptyResultSetException, SQLException, IOException {

		UserPanda userPanda = PojoHelper.fillPojo(UserPanda.class,
				PandaUserSelectByEmeilBuilder.create().setParam1_Email(userEmail).build().selectOneObject());

		PackageInsertIntoDbBuilder.create().setParam1_Description(editObject.getDescription())
				.setParam2_Weight(editObject.getWeight())
				.setParam3_EstimatedDate(editObject.getEstimatedDeliveryTime())
				.setParam4_Recipient(editObject.getRecipient())
				.setParam5_Status(editObject.getStatus())
				.setParam6_UserId(userPanda.getId())
				.setParam7_AddressId(addressId)
				.setParam8_Title(editObject.getTitle()).build().execute();

		clearObject();
		goTo(1);

	}
	
	public void changePackObject() throws SQLException {
		Long idAddress = this.idSenderFromMidlleBean;
		Long id = editObject.getShippingAddress().getId();
		if(idAddress != null && idAddress != 0) {
			id = idAddress;
		}
		
		PackageUpdateBuilder.create()
		.setParam1_Description(editObject.getDescription())
		.setParam2_Weight(editObject.getWeight())
		.setParam3_Estimated_delivery_time(editObject.getEstimatedDeliveryTime())
		.setParam4_Status(editObject.getStatus())
		.setParam5_Shipping_address(id)
		.setParam6_Title(editObject.getTitle())
		.setParam7_Recipient(editObject.getRecipient())
		.setParam8_Package_id(editObject.getId())
		.build().execute();
		this.idSenderFromMidlleBean = null;
		
		clearObject();
		goTo(1);
	}

	public void sellectAllPackage() {
		clearObject();
		goTo(1);
	}
	
	public void clearObject() {
		this.userEmail = null;
		this.editObject = new Package();
		this.cityName = null;
		this.streetName = null;
		this.streetNumber = null;
		this.sender = null;
	}

	public int getRowBegin() {
		return (currentPage - 1) * PAGE_SIZE + 1;
	}

	public int getRowEnd() {
		return getRowBegin() + PAGE_SIZE - 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void goTo(int page) {
		currentPage = page;
		
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		if (httpSession.getAttribute("role").equals(USER_PANDA)) {
			String email = (String) httpSession.getAttribute("email");
			UserPanda user = null;
			try {
				user = PojoHelper.fillPojo(UserPanda.class,
						PandaUserSelectByEmeilBuilder.create().setParam1_Email(email).build().selectOneObject());
			} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
				user = null;
			}
			editObject.setUserPanda(new UserPanda());
			editObject.getUserPanda().setId(user.getId());
		}
		if(this.sender != null && !this.sender.isEmpty()) {
			UserPanda user = null;
			try {
				user = PojoHelper.fillPojo(UserPanda.class, PandaUserSelectByNameBuilder.create()
						.setParam1_Name(this.sender).build().selectOneObject());
			}catch (PojoCreationException | EmptyResultSetException | SQLException e) {
				user = null;
			}
			if(user != null) {
				editObject.setUserPanda(new UserPanda());
				editObject.getUserPanda().setId(user.getId());
			}else {
				editObject.setUserPanda(new UserPanda());
				editObject.getUserPanda().setId(SET_USER_ID_WHEN_IS_NULL);
			}
			
		}
		loadPageData();
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
		goTo(packages != null && !packages.isEmpty() ? (packages.get(0).getRowsCount() / PAGE_SIZE)
				+ (packages.get(0).getRowsCount() % PAGE_SIZE > 0 ? 1 : 0) : 1);
	}

	public boolean isNextPageDisabled() {
		return packages != null && !packages.isEmpty() ? packages.get(0).getRowsCount() < currentPage * PAGE_SIZE
				: true;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Package getEditObject() {
		return editObject;
	}

	public void setEditObject(Package editObject) {
		this.editObject = editObject;
	}

	

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<UserPanda> getUsers() {
		return users;
	}

	public void setUsers(List<UserPanda> users) {
		this.users = users;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Long getIdSenderFromMidlleBean() {
		return idSenderFromMidlleBean;
	}

	public void setIdSenderFromMidlleBean(Long idSenderFromMidlleBean) {
		this.idSenderFromMidlleBean = idSenderFromMidlleBean;
	}
}

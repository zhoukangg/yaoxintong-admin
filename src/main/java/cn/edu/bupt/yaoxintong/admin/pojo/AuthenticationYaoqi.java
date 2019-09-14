package cn.edu.bupt.yaoxintong.admin.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authentication_yaoqi")
public class AuthenticationYaoqi {

    @Id
    private Integer id;

    private String businessId;

//    private String companyName;

    private String contact;

    private String address;

    private String phone;

    private String businessLicense;

    private String hygieneLicense;

    private String foodCirculationLicense;

    private String status;
    private String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getWebsite_status() {
        return website_status;
    }

    public void setWebsite_status(Integer website_status) {
        this.website_status = website_status;
    }

    private Integer website_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName == null ? null : companyName.trim();
//    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getHygieneLicense() {
        return hygieneLicense;
    }

    public void setHygieneLicense(String hygieneLicense) {
        this.hygieneLicense = hygieneLicense == null ? null : hygieneLicense.trim();
    }

    public String getFoodCirculationLicense() {
        return foodCirculationLicense;
    }

    public void setFoodCirculationLicense(String foodCirculationLicense) {
        this.foodCirculationLicense = foodCirculationLicense == null ? null : foodCirculationLicense.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "AuthenticationYaoqi{" +
                "id=" + id +
                ", businessId='" + businessId + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", businessLicense='" + businessLicense + '\'' +
                ", hygieneLicense='" + hygieneLicense + '\'' +
                ", foodCirculationLicense='" + foodCirculationLicense + '\'' +
                ", status='" + status + '\'' +
                ", website='" + website + '\'' +
                ", website_status=" + website_status +
                '}';
    }
}

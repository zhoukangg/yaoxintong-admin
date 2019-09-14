package cn.edu.bupt.yaoxintong.admin.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authentication_dianshang")
public class AuthenticationDianshang {
    @Id
    private Integer id;

    private String businessId;

    private String corporateName;

    private String contact;

    private String address;

    private String phone;

    private String icpFilingInformation;

    private String idtsqc;

    private String dmqmsc;

    private String status;

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

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName == null ? null : corporateName.trim();
    }

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

    public String getIcpFilingInformation() {
        return icpFilingInformation;
    }

    public void setIcpFilingInformation(String icpFilingInformation) {
        this.icpFilingInformation = icpFilingInformation == null ? null : icpFilingInformation.trim();
    }

    public String getIdtsqc() {
        return idtsqc;
    }

    public void setIdtsqc(String idtsqc) {
        this.idtsqc = idtsqc == null ? null : idtsqc.trim();
    }

    public String getDmqmsc() {
        return dmqmsc;
    }

    public void setDmqmsc(String dmqmsc) {
        this.dmqmsc = dmqmsc == null ? null : dmqmsc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "AuthenticationDianshang{" +
                "id=" + id +
                ", businessId='" + businessId + '\'' +
                ", corporateName='" + corporateName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", icpFilingInformation='" + icpFilingInformation + '\'' +
                ", idtsqc='" + idtsqc + '\'' +
                ", dmqmsc='" + dmqmsc + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
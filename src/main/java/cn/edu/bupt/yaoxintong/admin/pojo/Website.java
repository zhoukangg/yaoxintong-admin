package cn.edu.bupt.yaoxintong.admin.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authentication_dianshang")
public class Website {
    @Id
    private String id;
    private String corporateName;
    private String website;
    private Integer website_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorporate_name() {
        return corporateName;
    }

    public void setCorporate_name(String corporate_name) {
        this.corporateName = corporate_name;
    }

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

    @Override
    public String toString() {
        return "Website{" +
                "id='" + id + '\'' +
                ", corporate_name='" + corporateName + '\'' +
                ", website='" + website + '\'' +
                ", website_status=" + website_status +
                '}';
    }
}

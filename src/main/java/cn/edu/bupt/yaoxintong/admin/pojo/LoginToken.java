package cn.edu.bupt.yaoxintong.admin.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity 
@Table(name = "login_token")
public class LoginToken {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_token.id
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
	@Id
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_token.token
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    private String token;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_token.create_time
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_token.status
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_token.userId
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    private String userid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_token.id
     *
     * @return the value of login_token.id
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_token.id
     *
     * @param id the value for login_token.id
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_token.token
     *
     * @return the value of login_token.token
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_token.token
     *
     * @param token the value for login_token.token
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_token.create_time
     *
     * @return the value of login_token.create_time
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_token.create_time
     *
     * @param createTime the value for login_token.create_time
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_token.status
     *
     * @return the value of login_token.status
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_token.status
     *
     * @param status the value for login_token.status
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_token.userId
     *
     * @return the value of login_token.userId
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_token.userId
     *
     * @param userid the value for login_token.userId
     *
     * @mbggenerated Sun Mar 18 22:43:31 CST 2018
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}
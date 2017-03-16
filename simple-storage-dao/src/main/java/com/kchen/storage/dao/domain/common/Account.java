package com.kchen.storage.dao.domain.common;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table account
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Account {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   登录名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Database Column Remarks:
     *   昵称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.nick_name
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * Database Column Remarks:
     *   用户类型：0-普通用户， 1- 管理员
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.role
     *
     * @mbg.generated
     */
    private String role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.enabled
     *
     * @mbg.generated
     */
    private Integer enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbg.generated
     */
    public Account(Long id, String userName, String password, String nickName, String role, Integer enabled, String phone, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.role = role;
        this.enabled = enabled;
        this.phone = phone;
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbg.generated
     */
    public Account() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.id
     *
     * @return the value of account.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.id
     *
     * @param id the value for account.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.user_name
     *
     * @return the value of account.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.user_name
     *
     * @param userName the value for account.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.password
     *
     * @return the value of account.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.password
     *
     * @param password the value for account.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.nick_name
     *
     * @return the value of account.nick_name
     *
     * @mbg.generated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.nick_name
     *
     * @param nickName the value for account.nick_name
     *
     * @mbg.generated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.role
     *
     * @return the value of account.role
     *
     * @mbg.generated
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.role
     *
     * @param role the value for account.role
     *
     * @mbg.generated
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.enabled
     *
     * @return the value of account.enabled
     *
     * @mbg.generated
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.enabled
     *
     * @param enabled the value for account.enabled
     *
     * @mbg.generated
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.phone
     *
     * @return the value of account.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.phone
     *
     * @param phone the value for account.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.email
     *
     * @return the value of account.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.email
     *
     * @param email the value for account.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
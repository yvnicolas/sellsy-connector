/**
 * 
 */
package com.sellsy.objectentities;

/**
 * @author Yves Nicolas
 *
 */
public class SellsyPeople extends SellsyObject {

    
    private String forename;
    private String name;
    private String email;
    private String tel;
    private String mobile;
    private String position;
    private String ownerfullname;
    /**
     * 
     */
    public SellsyPeople() {
       
    }
    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getOwnerfullname() {
        return ownerfullname;
    }
    public void setOwnerfullname(String ownerfullname) {
        this.ownerfullname = ownerfullname;
    }
    @Override
    public String toString() {
        return "SellsyPeople [forename=" + forename + ", name=" + name + ", email=" + email + ", tel=" + tel
                + ", mobile=" + mobile + ", position=" + position + ", ownerfullname=" + ownerfullname + ", Id="
                + getId() + ", OwnerId=" + getOwnerid() + "]";
    }
    
    
    
}

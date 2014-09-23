package org.aaron.cobar.bean;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-09-13 12:38 PM
 */
public class TbUser {
    Long userId;/*用户id*/
    String nickName;/*用户昵称*/
    Date created;/*创建时间*/
    Date modified;/*修改时间*/

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}

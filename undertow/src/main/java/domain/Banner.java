package domain;

import java.util.Date;

/**
 * banner封装
 *
 * @author youmoo
 * @since 2014-09-17 10:57 AM
 */
public class Banner {
    String desc;//描述
    String link;//a 链接
    String img;//img url
    Date start;//开始日期
    Date end;//结绳日期
    Integer prioirty;//优先级
    String isValid;//是否有效

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getPrioirty() {
        return prioirty;
    }

    public void setPrioirty(Integer prioirty) {
        this.prioirty = prioirty;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}

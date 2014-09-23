package dao.impl;

import dao.BannerDao;
import dao.Dao;
import domain.Banner;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * @author youmoo
 * @since 2014-09-17 11:02 AM
 */
public class BannerDaoImpl extends Dao implements BannerDao {

    public static final String insert = "insert into banner (`desc`,link,img,`start`,`end`,priority,is_valid) values (?,?,?,?,?,?,?)";
    public static final String get = "select `desc`,link,img,`start`,`end`,priority,is_valid from banner where `start`>= ? and is_valid='Y' limit 1";

    @Override
    public void insert(Banner banner) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConn();
            statement = connection.prepareStatement(insert);
            int counter = 1;
            statement.setObject(counter++, banner.getDesc());
            statement.setObject(counter++, banner.getLink());
            statement.setObject(counter++, banner.getImg());
            statement.setObject(counter++, banner.getStart());
            statement.setObject(counter++, banner.getEnd());
            statement.setObject(counter++, banner.getPrioirty());
            statement.setObject(counter++, banner.getIsValid());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException("fail to insert a new banner", e);
        } finally {
            close(connection, statement, null);
        }

    }

    @Override
    public Banner get(Date date) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConn();
            statement = connection.prepareStatement(get);
            int counter = 1;
            statement.setObject(counter++, date);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Banner banner = new Banner();
                banner.setDesc(resultSet.getString("desc"));
                banner.setLink(resultSet.getString("link"));
                banner.setImg(resultSet.getString("img"));
                banner.setStart(resultSet.getDate("start"));
                banner.setEnd(resultSet.getDate("end"));
                banner.setPrioirty(resultSet.getInt("priority"));
                banner.setIsValid(resultSet.getString("is_valid"));
                return banner;
            }
        } catch (Exception e) {
            throw new DaoException("fail to insert a new banner", e);
        } finally {
            close(connection, statement, null);
        }
        return null;
    }
}

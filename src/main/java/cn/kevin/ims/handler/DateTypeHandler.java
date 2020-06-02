package cn.kevin.ims.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * @Author: Kevin
 * @Date: 2020/6/2 4:28 下午
 */
public class DateTypeHandler implements TypeHandler<Date> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(i, date);
    }

    @Override
    public Date getResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getDate(s);
    }

    @Override
    public Date getResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i);
    }

    @Override
    public Date getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i);
    }
}

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
 * The type Date type handler.
 * DateTypeHandler
 * 时间处理器
 * @Author: Kevin
 * @Date: 2020 /6/2 4:28 下午
 */
public class DateTypeHandler implements TypeHandler<Date> {
    /**
     * The Sdf.
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Sets parameter.
     *
     * @param preparedStatement the prepared statement
     * @param i                 the
     * @param date              the date
     * @param jdbcType          the jdbc type
     * @throws SQLException the sql exception
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(i, date);
    }

    /**
     * @throws SQLException the sql exception
     * @method: get result.
     * @param: resultSet the result set
     * @param: s the s
     * @return: result
     */
    @Override
    public Date getResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getDate(s);
    }

    /**
     * @throws SQLException the sql exception
     * @method: get result.
     * @param: resultSet the result set
     * @param: i the
     * @return: result
     */
    @Override
    public Date getResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i);
    }

    /**
     * @throws SQLException the sql exception
     * @method: get result.
     * @param: callableStatement the callable statement
     * @param: i the
     * @return: result
     */
    @Override
    public Date getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i);
    }
}

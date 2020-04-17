package com.github.jnoee.xo.ienum.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.jnoee.xo.ienum.IEnum;
import com.github.jnoee.xo.ienum.IEnumManager;

/**
 * 枚举转换器。
 *
 * @param <E> 枚举类型
 */
public class IEnumTypeHandler<E extends IEnum> extends BaseTypeHandler<E> {
  private final Class<E> type;

  public IEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("枚举类型不能为空。");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getValue());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return IEnumManager.getIEnumByValue(type, rs.getString(columnName));
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return IEnumManager.getIEnumByValue(type, rs.getString(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return IEnumManager.getIEnumByValue(type, cs.getString(columnIndex));
  }
}

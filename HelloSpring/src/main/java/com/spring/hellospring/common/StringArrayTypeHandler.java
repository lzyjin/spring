package com.spring.hellospring.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

// mybatis에서 사용할 타입객체로 등록 
// TypeHandler 인터페이스를 구현하면 된다 
public class StringArrayTypeHandler implements TypeHandler<String[]> {
	
	
	// setParameter() 메소드는 
	// 위치홀더에 들어갈 값을 세팅하는 것
	// pstmt.setString(1, "값");  

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		
		if(parameter != null) {
			ps.setString(i, String.join(",", parameter)); // join과 반대는 split 
		} else {
			ps.setString(i, "");
		}
	}

	
	
	// getResult() 메소드는 
	// DB에서 조회한 데이터를 vo에 저장할 때 
	// rs.getString("컬럼명"); 또는 rs.getString(컬럼인덱스번호);
	
	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		
		return rs.getString(columnName).split(",");
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return rs.getString(columnIndex).split(",");
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		
		return cs.getString(columnIndex).split(",");
	}



}

package com.testweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.testweb.util.JdbcUtil;

public class UserDAO {
	
	private DataSource ds;//server.xml에 설정한 값 얻어오기
	
	//멤버변수
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	//1.싱글톤 - 중요(static)
	private static UserDAO instance = new UserDAO();
	
	//2.기본생성자 생성할 수 없도록 막기
	private UserDAO() {
		
		//생성자 생성시마다 드라이버 로드
		try {
			InitialContext ctx = new InitialContext();//초기설정정보
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("드라이버 호출 에러 발생");
			e.printStackTrace();
		}
	}
	
	
	//3.외부에서 객체 생성을 요구할 때 getter메서드를 통해 1번 객체를 반환
	public static UserDAO getInstance() {
		return instance;
	}
	
	//회원가입 메서드
	public int join(UserVO vo) {
		
		//1.가입sql 생성
		String sql = "INSERT INTO members(id, password, name, phone1, phone2, phone3, email, eaddr, addr_basic, addr_detail) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int result = 0;//성공여부 반환
		
		try {
			//2.연결 생성
			conn = ds.getConnection();
			//?를 채워주는 pstmt
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getEaddr());
			pstmt.setString(9, vo.getAddr_basic());
			pstmt.setString(10, vo.getAddr_detail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("join()메서드 에러 발생");
			e.printStackTrace();
			
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}

	//중복검사 메서드
	public int checkID(String id) {
		
		//1.sql작성
		String sql = "SELECT * FROM members WHERE id = ?";
		
		int result = 0;//결과값 반환
		
		try {
			//2.연결생성
			conn = ds.getConnection();
			//3.pstmt생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//중복 o
				result = 1;
			} else {//중복 x
				result = 0;
			}
			
		} catch (Exception e) {
			System.out.println("checkID()에서 에러 발생");
			e.printStackTrace();
		} finally {
			//4.닫아주기
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
	}

	//로그인 처리 메서드
	public UserVO login(String id, String password) {
		
		//1.로그인 sql
		String sql = "SELECT * FROM members WHERE id = ? AND password = ?";
		//반환할 vo객체
		UserVO vo = new UserVO();
		
		try {
			//2.연결생성
			conn = ds.getConnection();
			//pstmt
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {//회원 존재
				vo.setId(id);
				vo.setPassword(password);
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setEaddr(rs.getString("eaddr"));
				vo.setAddr_basic(rs.getString("addr_basic"));
				vo.setAddr_detail(rs.getString("addr_detail"));
				//>뽑아서 담아주기
			} else {//없다면
				vo = null;
			}
			
		} catch (Exception e) {
			System.out.println("login()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}

	//회원 삭제처리 메서드
	public void delete(String id, String password) {
		
		//삭제 sql
		String sql = "DELETE FROM members WHERE id = ? AND password = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("delete()메서드 에러 발생");
			e.printStackTrace();
			
		}
		
	}

	//회원정보 수정 메서드
	public void update(UserVO vo) {
		//회원정보 수정 쿼리
		String sql = "UPDATE members"
					+ " SET password = ?,"
					+ " name = ?,"
					+ " phone1 = ?,"
					+ " phone2 = ?,"
					+ " phone3 = ?,"
					+ " email = ?,"
					+ " eaddr = ?,"
					+ " addr_basic = ?,"
					+ " addr_detail = ?"
					+ " WHERE id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone1());
			pstmt.setString(4, vo.getPhone2());
			pstmt.setString(5, vo.getPhone3());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getEaddr());
			pstmt.setString(8, vo.getAddr_basic());
			pstmt.setString(9, vo.getAddr_detail());
			pstmt.setString(10, vo.getId());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("update()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}


	
	
	
	
	
}















































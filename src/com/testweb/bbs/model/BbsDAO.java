package com.testweb.bbs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.testweb.util.JdbcUtil;

public class BbsDAO {
	private DataSource ds;// server.xml에 설정한 값 얻어오기

	// 멤버변수
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//1.싱글톤 - 중요한건 뭐다? private static
	private static BbsDAO instance = new BbsDAO();
	
	//2.외부에서 사용하게 생성자getter하나 생성
	public static BbsDAO getInstance() {
		return instance;
	}
	
	//3. 기본생성자 막기
	private BbsDAO() {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
			
			
		} catch (Exception e) {
			System.out.println("드라이버 호출 에러 발생");
			e.printStackTrace();
		}
	}
	
	//게시글 등록 처리
	
	
	
	//게시글 가져오기
	/*
	 * public ArrayList<BbsVO> getList(){
	 * 
	 * ArrayList<BbsVO> list = new ArrayList<>();
	 * 
	 * //최신글 = 가장 마지막 입력됨 > bno거꾸로 String sql =
	 * "SELECT * FROM bbs ORDER BY bno DESC";
	 * 
	 * try { conn = ds.getConnection(); pstmt = conn.prepareStatement(sql); rs =
	 * pstmt.executeQuery();
	 * 
	 * //존재하는 글 전부 가져오기 - while while(rs.next()) { int bno = rs.getInt("bno");
	 * String writer = rs.getString("writer"); String title = rs.getString("title");
	 * String content = rs.getString("content"); Timestamp regdate =
	 * rs.getTimestamp("regdate");
	 * 
	 * //모든 값을 꺼내와 vo에 넣고 BbsVO vo = new BbsVO(bno, writer, title, content,
	 * regdate); //list에 추가 list.add(vo); } } catch (Exception e) {
	 * System.out.println("getList()메서드 에러 발생"); e.printStackTrace(); } finally {
	 * JdbcUtil.close(conn, pstmt, rs); }
	 * 
	 * return list; }
	 */
	//페이징 처리, 게시글
	public ArrayList<BbsVO> getList(int pageNum, int amount){
			
		ArrayList<BbsVO> list = new ArrayList<>();
		
		//sql 생성
		String sql = "SELECT * \r\n" + 
				"FROM(\r\n" + 
				"    SELECT ROWNUM rn,\r\n" + 
				"           bno,\r\n" + 
				"           writer,\r\n" + 
				"           title,\r\n" + 
				"           content,\r\n" + 
				"           regdate\r\n" + 
				"    FROM (\r\n" + 
				"    SELECT * FROM bbs\r\n" + 
				"    ORDER BY bno DESC)\r\n" + 
				"    )\r\n" + 
				"WHERE RN > ? AND RN <= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum - 1)*amount);
			pstmt.setInt(2, pageNum*amount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				BbsVO vo = new BbsVO(bno, writer, title, content, regdate);
				list.add(vo);
			}	
		} catch (Exception e) {
			System.out.println("getList()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return list;	
	}
	
	
	
	//글 상세보기(글번호)
	public BbsVO content(int bno) {
		
		//sql:글번호 -> 찾기
		String sql = "SELECT * FROM bbs WHERE bno = ?";
		BbsVO vo = new BbsVO();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//bno번째 글이 존재함
				vo.setBno(bno);
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}
		} catch (Exception e) {
			System.out.println("content()메서드 에러 발생");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return vo;
	}
	
	//게시글 수정 처리 메서드
	public void update(int bno, String title, String content) {
		
		//알맞는 sql
		String sql = "UPDATE bbs SET title = ?, content = ? WHERE bno = ?";
		
		try {
			//연결
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bno);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("update()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	//메인화면 최신 공지글 10개 출력
	public ArrayList<BbsVO> mainList() {
		ArrayList<BbsVO> list = new ArrayList<>();
		
		String sql = "SELECT * \r\n" + 
				"FROM(\r\n" + 
				"    SELECT ROWNUM r, \r\n" + 
				"           bno,\r\n" + 
				"           writer,\r\n" + 
				"           title,\r\n" + 
				"           content,\r\n" + 
				"           regdate\r\n" + 
				"    FROM (SELECT *\r\n" + 
				"          FROM bbs\r\n" + 
				"          ORDER BY bno DESC)\r\n" + 
				"    ) \r\n" + 
				"WHERE r > 0 AND r <= 10";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = Integer.parseInt(rs.getString("bno")); 
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				
				BbsVO vo = new BbsVO(bno, writer, title, content, regdate);
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("mainList()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
	}

	
	//게시글 작성 메서드
	public void regist(String writer, String title, String content) {
		
		//sql
		String sql = "INSERT INTO bbs(bno, writer, title, content)"
				+ "	VALUES(bbs_seq.nextval, ?, ?, ?)";
		
		try {
			//연결생성
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("regist()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	
	}

	public ArrayList<BbsVO> mypage(String id) {
		ArrayList<BbsVO> list = new ArrayList<>();
		
		String sql = "SELECT * " + 
					"FROM bbs b\r\n" + 
					"LEFT OUTER JOIN members m\r\n" + 
					"ON b.writer = m.id\r\n" + 
					"where b.writer = ?\r\n" + 
					"ORDER BY b.bno DESC";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = Integer.parseInt(rs.getString("bno")); 
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				
				BbsVO vo = new BbsVO(bno, writer, title, content, regdate);
				list.add(vo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	
	//전체 게시글 수
	public int getTotal() {
		
		int total = 0;
		
		//전체 게시글 조회 sql
		String sql = "SELECT count(*) as total FROM bbs";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}
			
		} catch (Exception e) {
			System.out.println("getTotal()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		return total;
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
















































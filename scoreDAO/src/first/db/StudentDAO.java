package first.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDAO {
	private static final int SCORE_DESC = 1;
	private static final int SCORE_ASC = 2;
	private static final int NO_ASC = 3;

	public static StudentVO search(int n, String sNum) throws Exception{
		Connection con = DBUtil.getConnection();
		StudentVO s = null;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		try {
			if(sNum == null) {
				query = "select membertbl.*, s.db, s.c, s.java, s.total, s.avg, s.grade, pr.proName from membertbl join scoretbl as s on membertbl.no = s.no join professor as pr on pr.no = membertbl.no where membertbl.no = ?;";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, n);
			}else {
				query = "select membertbl.*, s.db, s.c, s.java, s.total, s.avg, s.grade, pr.proName from membertbl join scoretbl as s on membertbl.no = s.no join professor as pr on pr.no = membertbl.no where membertbl.studentNum = ?;";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, sNum);
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String studentNum = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String address = rs.getString(7);
				int db = rs.getInt(8);
				int c = rs.getInt(9);
				int java = rs.getInt(10);
				int total = rs.getInt(11);
				double avg = rs.getDouble(12);
				String grade = rs.getString(13);
				String proName = rs.getString(14);
				
				s = new StudentVO(no, studentNum, name, age, phone, email,
						address, db, c, java, total, avg, grade, proName);
			}
			
			if(s == null) {
				System.out.println("검색결과 없음");
			}
			
		}catch (Exception e) {
			System.out.println("검색실패");
		}
		return s;
	}

	public static void delete(int n) throws Exception {
		Connection con = DBUtil.getConnection();
		
		PreparedStatement pstmt = null;
		
		String query = "delete from membertbl where no = ?;";
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, n);
			
			pstmt.executeUpdate();
			
			System.out.println("삭제성공");
			
		}catch (Exception e) {
			System.out.println("삭제실패");
		}
		
	}

	public static void input(StudentVO s) throws Exception {
		Connection con = DBUtil.getConnection();
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		
		String member_Query = "insert into membertbl values(null, ?, ?, ?, ?, ?, ?);";
		String scoreinfo_Query = "insert into scoretbl (no, studentNum, name) select no, studentNum, name from membertbl where studentNum = ?;";
		String proScore_Query = "call pro_calculator(?, ?, ?, ?);";
		String ssor_Query = "insert into professor (no, name) select no, name from membertbl where studentNum = ?;";
		String ssor_Query2 = "update professor set proName = ? where no = ?";
		String ssor_Query3 = "update professor as pr, profession as pn set pr.subName = pn.subName where pr.proName = pn.proName;";
		String getNo = "select no from membertbl where studentNum = ?;";
		try {
			pstmt1 = con.prepareStatement(member_Query);
			
			pstmt1.setString(1, s.getStudentNum());
			pstmt1.setString(2, s.getName());
			pstmt1.setInt(3, s.getAge());
			pstmt1.setString(4, s.getPhone());
			pstmt1.setString(5, s.getEmail());
			pstmt1.setString(6, s.getAddress());
			
			pstmt1.executeUpdate();
			
			pstmt8 = con.prepareStatement(getNo);
			pstmt8.setString(1, s.getStudentNum());
			ResultSet rs = pstmt8.executeQuery();
			
			while(rs.next()) {
				s.setNo(rs.getInt(1));
			}
			pstmt2 = con.prepareStatement(scoreinfo_Query);
			pstmt4 = con.prepareStatement(proScore_Query);
			pstmt5 = con.prepareStatement(ssor_Query);
			pstmt6 = con.prepareStatement(ssor_Query2);
			pstmt7 = con.prepareStatement(ssor_Query3);
			
			pstmt1.setString(1, s.getStudentNum());
			pstmt1.setString(2, s.getName());
			pstmt1.setInt(3, s.getAge());
			pstmt1.setString(4, s.getPhone());
			pstmt1.setString(5, s.getEmail());
			pstmt1.setString(6, s.getAddress());
			
			pstmt2.setString(1, s.getStudentNum());
			
			pstmt4.setInt(1, s.getNo());
			pstmt4.setInt(2, s.getDb());
			pstmt4.setInt(3, s.getC());
			pstmt4.setInt(4, s.getJava());
			
			pstmt5.setString(1, s.getStudentNum());
			
			pstmt6.setString(1, s.getProName());
			pstmt6.setInt(2, s.getNo());
			
			
			pstmt2.executeUpdate();
			pstmt4.executeUpdate();
			pstmt5.executeUpdate();
			pstmt6.executeUpdate();
			pstmt7.executeUpdate();
			
			System.out.println("삽입성공");
		}catch (Exception e) {
			System.out.println("삽입실패");
		}
		
	}

	public static ArrayList<StudentVO> print(int select) throws Exception {
		Connection con = DBUtil.getConnection();
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		PreparedStatement pstmt = null;
		
		String query = "select membertbl.*, s.db, s.c, s.java, s.total, s.avg, s.grade, pr.proName from membertbl join scoretbl as s on membertbl.no = s.no join professor as pr on pr.no = membertbl.no";
		
		switch(select) {
		case SCORE_DESC:
			query += " ORDER BY avg DESC;";
			break;
		case SCORE_ASC:
			query += " ORDER BY avg ASC;";
			break;
		case NO_ASC:
			query += " ORDER BY no ASC;";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String studentNum = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String address = rs.getString(7);
				int db = rs.getInt(8);
				int c = rs.getInt(9);
				int java = rs.getInt(10);
				int total = rs.getInt(11);
				double avg = rs.getDouble(12);
				String grade = rs.getString(13);
				String proName = rs.getString(14);
				
				list.add(new StudentVO(no, studentNum, name, age, phone, email,
						address, db, c, java, total, avg, grade, proName));
			}
			
		}catch(Exception e) {
			System.out.println("출력실패");
		}
		
		
		
		return list;
	}
}

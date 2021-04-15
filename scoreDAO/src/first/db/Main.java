package first.db;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static final Scanner sc = new Scanner(System.in);
	private static final int PRINT = 1;
	private static final int INPUT = 2;
	private static final int DELETE = 3;
	private static final int SEARCH = 4;
	private static final int EXIT = 5;
	
	public static void main(String[] args) throws Exception {
		
		boolean flag = false;
		int num = 0;
		while(!flag) {
			System.out.println("1.출력, 2.입력, 3.삭제, 4.검색, 5.종료");
			try {
				num = sc.nextInt();
			}catch (InputMismatchException i) {
				System.out.println("숫자만 입력하세요");
				continue;
			}
			
			switch(num) {
			
			case PRINT:
				printStudentInformation();
				break;
			case INPUT:
				inputStudentInformation();
				break;
			case DELETE:
				deleteStudentInformation();
				break;
			case SEARCH:
				searchStudentInformation();
				break;
			case EXIT:
				flag = true;
				break;
			default:
				System.out.println("1~5 입력요망");
				break;
			}
		}//end of while
		
		System.out.println("프로그램 종료.");

	}
	private static void searchStudentInformation() throws Exception {
		try {
			System.out.print("1.no검색, 2.학번검색\n선택: ");
			int choice = sc.nextInt();
			int n = 0;
			String sNum = null;
			if(choice == 1) {
				System.out.print("no입력: ");
				n = sc.nextInt();
				StudentVO studentVO= StudentDAO.search(n, sNum);
				System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(studentVO);
			}else if(choice == 2) {
				System.out.print("학번입력: ");
				sNum = sc.next();
				StudentVO studentVO= StudentDAO.search(n, sNum);
				System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(studentVO);
			}else {
				System.out.println("(1또는2번 선택)");
			}
			
		}catch (InputMismatchException i) {
			System.out.println("숫자만 입력하세요");
			sc.next();
		}
		
		
		
		
		
	}
	private static void deleteStudentInformation() throws Exception {
		try {
			System.out.print("삭제할 no 입력: ");
			
			int n = sc.nextInt();
			StudentVO studentVO = StudentDAO.search(n, null);
			
			if(studentVO != null) {
				System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(studentVO);
				System.out.println("삭제 하시겠습니까?(Y/N)");
				
				String choice = sc.next();
				if(choice.toUpperCase().equals("Y")) {
					StudentDAO.delete(n);
				}else if(choice.toUpperCase().equals("N")) {
					System.out.println("삭제를 취소합니다.");
				}else {
					System.out.println("Y 또는 N을 입력해주세요");
				}
			}
			
			
		}catch (InputMismatchException i) {
			System.out.println("숫자만 입력하세요");
			sc.next();
		}
		System.out.println();
		
	}
	private static void inputStudentInformation() throws Exception {
		int db, c, java, age = 0;
		
		String studentNum = null;
		while(true) {
			System.out.print("학번: ");
			studentNum = sc.next();
			if(studentNum.length() == 8 && studentNum.matches("^[0-9]*$")) {
				break;
			}else {
				System.out.println("학번 8자리를 입력해주세요");
			}
		}
		
		System.out.print("이름: ");
		String name = sc.next();
		
		while(true) {
			
			try {
				System.out.print("나이: ");
				age = sc.nextInt();
				break;
			}catch (InputMismatchException i) {
				System.out.println("숫자만 입력하세요");
				sc.next();
				System.out.println();
			}
			
		}
		
		System.out.print("핸드폰(-제외): ");
		String phone = sc.next();
		
		System.out.print("이메일: ");
		String email = sc.next();
		
		System.out.print("주소: ");
		String address = sc.next();
		
		score:while(true){
			
			try {
				System.out.print("db점수: ");
				db = sc.nextInt();
				
				if(db < 0 || db > 100) {
					System.out.println("점수를 잘못 입력하셨습니다.");
					continue score;
				}
				break;
				
			}catch (InputMismatchException i) {
				System.out.println("숫자만 입력하세요");
				sc.next();
				
			}
		}
		
		score1:while(true) {
				
			try {
				System.out.print("c점수: ");
				c = sc.nextInt();
				
				if(c < 0 || c > 100) {
					System.out.println("점수를 잘못 입력하셨습니다.");
					continue score1;
				}
				break;
				
			}catch (InputMismatchException i) {
				System.out.println("숫자만 입력하세요");
				sc.next();
				
			}
		}
		score2:while(true) {
			try {	
				System.out.print("java점수: ");
				java = sc.nextInt();
				if(java < 0 || java > 100) {
					System.out.println("점수를 잘못 입력하셨습니다.");
					continue score2;
				}
				
				break;
			}catch (InputMismatchException i) {
				System.out.println("숫자만 입력하세요");
				sc.next();
			
			}
		}
			
		
		System.out.print("담당교수: ");
		String proName = sc.next();
		
		StudentDAO.input(new StudentVO(0, studentNum, name, age, phone, email
				, address, db, c, java, 0,0.0,null, proName));
		System.out.println();
		
	}
	private static void printStudentInformation() throws Exception {
		int select = 0;
		while(true) {
			System.out.println();
			System.out.println("1.내림차순 2.오름차순 3.번호순");
			try {
				select = sc.nextInt();
				if(select < 1 || select > 3) {
					System.out.println("(1~3)번을 입력해주세요.");
				}else {
					break;
				}
				
			}catch (InputMismatchException i) {
				System.out.println("숫자만 입력하세요.");
				sc.next();
			}
		}
		
		
		
		ArrayList<StudentVO> list = StudentDAO.print(select);
		System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		for(StudentVO s : list) {
			System.out.println(s);
		}
		System.out.println();
		
	}

}

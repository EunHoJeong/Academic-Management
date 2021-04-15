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
			System.out.println("1.���, 2.�Է�, 3.����, 4.�˻�, 5.����");
			try {
				num = sc.nextInt();
			}catch (InputMismatchException i) {
				System.out.println("���ڸ� �Է��ϼ���");
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
				System.out.println("1~5 �Է¿��");
				break;
			}
		}//end of while
		
		System.out.println("���α׷� ����.");

	}
	private static void searchStudentInformation() throws Exception {
		try {
			System.out.print("1.no�˻�, 2.�й��˻�\n����: ");
			int choice = sc.nextInt();
			int n = 0;
			String sNum = null;
			if(choice == 1) {
				System.out.print("no�Է�: ");
				n = sc.nextInt();
				StudentVO studentVO= StudentDAO.search(n, sNum);
				System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(studentVO);
			}else if(choice == 2) {
				System.out.print("�й��Է�: ");
				sNum = sc.next();
				StudentVO studentVO= StudentDAO.search(n, sNum);
				System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(studentVO);
			}else {
				System.out.println("(1�Ǵ�2�� ����)");
			}
			
		}catch (InputMismatchException i) {
			System.out.println("���ڸ� �Է��ϼ���");
			sc.next();
		}
		
		
		
		
		
	}
	private static void deleteStudentInformation() throws Exception {
		try {
			System.out.print("������ no �Է�: ");
			
			int n = sc.nextInt();
			StudentVO studentVO = StudentDAO.search(n, null);
			
			if(studentVO != null) {
				System.out.println("no\tstduntNum\tname\tage\tphone\t\temail\t\taddress\tdb\tc\tjava\ttotal\tavg\tgrade\tproName");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(studentVO);
				System.out.println("���� �Ͻðڽ��ϱ�?(Y/N)");
				
				String choice = sc.next();
				if(choice.toUpperCase().equals("Y")) {
					StudentDAO.delete(n);
				}else if(choice.toUpperCase().equals("N")) {
					System.out.println("������ ����մϴ�.");
				}else {
					System.out.println("Y �Ǵ� N�� �Է����ּ���");
				}
			}
			
			
		}catch (InputMismatchException i) {
			System.out.println("���ڸ� �Է��ϼ���");
			sc.next();
		}
		System.out.println();
		
	}
	private static void inputStudentInformation() throws Exception {
		int db, c, java, age = 0;
		
		String studentNum = null;
		while(true) {
			System.out.print("�й�: ");
			studentNum = sc.next();
			if(studentNum.length() == 8 && studentNum.matches("^[0-9]*$")) {
				break;
			}else {
				System.out.println("�й� 8�ڸ��� �Է����ּ���");
			}
		}
		
		System.out.print("�̸�: ");
		String name = sc.next();
		
		while(true) {
			
			try {
				System.out.print("����: ");
				age = sc.nextInt();
				break;
			}catch (InputMismatchException i) {
				System.out.println("���ڸ� �Է��ϼ���");
				sc.next();
				System.out.println();
			}
			
		}
		
		System.out.print("�ڵ���(-����): ");
		String phone = sc.next();
		
		System.out.print("�̸���: ");
		String email = sc.next();
		
		System.out.print("�ּ�: ");
		String address = sc.next();
		
		score:while(true){
			
			try {
				System.out.print("db����: ");
				db = sc.nextInt();
				
				if(db < 0 || db > 100) {
					System.out.println("������ �߸� �Է��ϼ̽��ϴ�.");
					continue score;
				}
				break;
				
			}catch (InputMismatchException i) {
				System.out.println("���ڸ� �Է��ϼ���");
				sc.next();
				
			}
		}
		
		score1:while(true) {
				
			try {
				System.out.print("c����: ");
				c = sc.nextInt();
				
				if(c < 0 || c > 100) {
					System.out.println("������ �߸� �Է��ϼ̽��ϴ�.");
					continue score1;
				}
				break;
				
			}catch (InputMismatchException i) {
				System.out.println("���ڸ� �Է��ϼ���");
				sc.next();
				
			}
		}
		score2:while(true) {
			try {	
				System.out.print("java����: ");
				java = sc.nextInt();
				if(java < 0 || java > 100) {
					System.out.println("������ �߸� �Է��ϼ̽��ϴ�.");
					continue score2;
				}
				
				break;
			}catch (InputMismatchException i) {
				System.out.println("���ڸ� �Է��ϼ���");
				sc.next();
			
			}
		}
			
		
		System.out.print("��米��: ");
		String proName = sc.next();
		
		StudentDAO.input(new StudentVO(0, studentNum, name, age, phone, email
				, address, db, c, java, 0,0.0,null, proName));
		System.out.println();
		
	}
	private static void printStudentInformation() throws Exception {
		int select = 0;
		while(true) {
			System.out.println();
			System.out.println("1.�������� 2.�������� 3.��ȣ��");
			try {
				select = sc.nextInt();
				if(select < 1 || select > 3) {
					System.out.println("(1~3)���� �Է����ּ���.");
				}else {
					break;
				}
				
			}catch (InputMismatchException i) {
				System.out.println("���ڸ� �Է��ϼ���.");
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

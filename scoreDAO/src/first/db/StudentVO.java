package first.db;

public class StudentVO {
	private int no;
	private String studentNum;
	private String name;
	private int age;
	private String phone;
	private String email;
	private String address;
	private int db;
	private int c;
	private int java;
	private int total;
	private double avg;
	private String grade;
	private String proName;
	
	
	public StudentVO() {
		this(0, null, null, 0, null, null, null, 0, 0, 0, 0, 0.0, null, null);
	}


	public StudentVO(int no, String studentNum, String name, int age, String phone, String email, String address,
			int db, int c, int java, int total, double avg, String grade, String proName) {
		super();
		this.no = no;
		this.studentNum = studentNum;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.db = db;
		this.c = c;
		this.java = java;
		this.total = total;
		this.avg = avg;
		this.grade = grade;
		this.proName = proName;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getDb() {
		return db;
	}


	public void setDb(int db) {
		this.db = db;
	}


	public int getC() {
		return c;
	}


	public void setC(int c) {
		this.c = c;
	}


	public int getJava() {
		return java;
	}


	public void setJava(int java) {
		this.java = java;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public double getAvg() {
		return avg;
	}


	public void setAvg(double avg) {
		this.avg = avg;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	@Override
	public String toString() {
		return no + "\t" + studentNum + "\t" + name + "\t" + age + "\t"
				+ phone + "\t" + email + "\t" + address + "\t" + db + "\t" + c + "\t" + java
				+ "\t" + total + "\t" + avg + "\t" + grade + "\t" + proName;
	}

	
	
	


	
}

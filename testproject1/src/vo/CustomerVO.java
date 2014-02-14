package vo;

public class CustomerVO {

	private String id;
	private String password;
	private String name;
	private String birth;
	private String zipCode;
	private String job;
	private String address1;
	private String address2;
	private String major;
	private String homePhone;
	private String cellPhone;
	private boolean isChrist;
	private String photoPath;
	private String prayTitle;
	private int deptCode;
	private String deptName;
	private int callingCode;
	private String callingName;
	private int gradeCode;
	private String gradeName;
	
	public CustomerVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerVO(String id, String password, String name, String birth,
			String zipCode, String job, String address1, String address2,
			String major, String homePhone, String cellPhone, boolean isChrist,
			String photoPath, String prayTitle, int deptCode, String deptName,
			int callingCode, String callingName, int gradeCode, String gradeName) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.zipCode = zipCode;
		this.job = job;
		this.address1 = address1;
		this.address2 = address2;
		this.major = major;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.isChrist = isChrist;
		this.photoPath = photoPath;
		this.prayTitle = prayTitle;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.callingCode = callingCode;
		this.callingName = callingName;
		this.gradeCode = gradeCode;
		this.gradeName = gradeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public boolean isChrist() {
		return isChrist;
	}

	public void setChrist(boolean isChrist) {
		this.isChrist = isChrist;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPrayTitle() {
		return prayTitle;
	}

	public void setPrayTitle(String prayTitle) {
		this.prayTitle = prayTitle;
	}

	public int getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(int callingCode) {
		this.callingCode = callingCode;
	}

	public String getCallingName() {
		return callingName;
	}

	public void setCallingName(String callingName) {
		this.callingName = callingName;
	}

	public int getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	
}

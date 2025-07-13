package models.response;

public class CreateUserResponse {

	private String name;
	private String job;
	private String id;
	private String createdAt;
	
	public CreateUserResponse() {
	}
	
	public CreateUserResponse(String name, String job, String id, String createdAt) {
		this.name = name;
		this.job = job;
		this.id = id;
		this.createdAt = createdAt;
	}
	
	public String getName() {return name;}
	public String getJob() {return job;}
	public String getId() {return id;}
	public String getCreatedAt() {return createdAt;}
	
	public void setName(String name) {this.name = name;}
	public void setJob(String job) {this.job = job;}
	public void setId(String id) {this.id = id;}
	public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}
}

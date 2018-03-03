package cglib;

public class ClientBean {
	private String name = null;

	public ClientBean() {

	}

	public ClientBean(String name) {
		this.name = name;
	}

	public void addClient() {
		System.out.println("this is addClient() method!");
	}

	public void deleteClient() {
		System.out.println("this is deleteClient() method!");
	}

	public void getClient() {
		System.out.println("this is getClient() method!");
	}

	public void updateClient() {
		System.out.println("this is updateClient() method!");
	}

	public String getClientName() {
		return name;
	}

	public void setClientName(String name) {
		this.name = name;
	}

}

package IOCAOP.aop;

public class UserBeanImpl implements UserBean{

	private String user = null; 
	public UserBeanImpl(){     
	}   
	public UserBeanImpl(String user){ 
		this.user = user; 
	}   
	public String getUserName(){ 
		return user; 
	}   

	public void getUser() {
		System.out.println("this is getUser() method!"); 
	}

	public void addUser() {
		 System.out.println("this is addUser() method!"); 
	}

	public void updateUser() {
		System.out.println("this is updateUser() method!"); 
	}

	public void deleteUser() {
		System.out.println("this is deleteUser() method!"); 
	}

}

package main.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LoggedUserService {
	
	private int currUser;

	public int getCurrUser() {
		return currUser;
	}

	public void setCurrUser(int currUser) {
		this.currUser = currUser;
	}

}

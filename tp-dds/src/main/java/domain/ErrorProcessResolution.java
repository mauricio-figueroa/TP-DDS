package domain;

import users.Admin;

public interface ErrorProcessResolution {



	public void errorResolution(String result,Admin admin, Runnable process);
}

package domain;

import java.util.concurrent.Callable;

import users.Admin;

public interface ErrorProcessResolution {



	public void errorResolution(String result,Admin admin, Callable<String> process);
}

package tp.dds.domain.common;

import java.util.concurrent.Callable;

import tp.dds.domain.users.Admin;

public interface ErrorProcessResolution {



	public void errorResolution(String result,Admin admin, Callable<String> process);
}

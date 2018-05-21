package edu.mum.asd.library.model;


/**
 * @author Mafrel
 * @Purpose implementing visitor design pattern for notifying users
 */
public interface Visitable {
	
	public void notifyUser(User user);
}

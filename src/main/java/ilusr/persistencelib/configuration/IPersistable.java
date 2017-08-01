package ilusr.persistencelib.configuration;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IPersistable {
	/**
	 * 
	 * @return The persisted data.
	 */
	<T> T persist();
}

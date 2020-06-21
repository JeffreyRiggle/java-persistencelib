package ilusr.persistencelib.configuration;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IPersistable {
	/**
	 * @param <T> type of object to persist.
	 * @return The persisted data.
	 */
	<T> T persist();
}

package de.paraplu.springspark.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * This class is a helper class for all builder classes that have to inject properties via Spring.
 *
 * @author FRE
 *
 */
@Component
public class SpringBuilder {

	private static AutowireCapableBeanFactory BEAN_FACTORY;

	/**
	 * Autowire all dependencies for the given object.
	 *
	 * @param object
	 *           The object that has properties to be autowired.
	 */
	public static void autowire(Object object) {
		BEAN_FACTORY.autowireBean(object);
	}

	/**
	 * Create a new instance of the given class with all properties autowired.
	 *
	 * @param clazz
	 *           The instance of the required object.
	 * @return The required spring configured object.
	 */
	public static <T> T create(Class<T> clazz) {
		return BEAN_FACTORY.createBean(clazz);
	}

	@Autowired
	protected AutowireCapableBeanFactory beanFactory;

	/**
	 * This method sets the static bean factory so it can be used by non Spring managed classes as well.
	 */
	@PostConstruct
	private void init() {
		BEAN_FACTORY = beanFactory;
	}

}

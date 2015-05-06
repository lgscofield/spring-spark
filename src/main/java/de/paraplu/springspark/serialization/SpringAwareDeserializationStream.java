package de.paraplu.springspark.serialization;

import org.apache.spark.serializer.DeserializationStream;

import scala.collection.Iterator;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag;
import de.paraplu.springspark.util.SpringBuilder;

public class SpringAwareDeserializationStream extends DeserializationStream {

	private final DeserializationStream deserializationStream;

	public SpringAwareDeserializationStream(DeserializationStream deserializationStream) {
		this.deserializationStream = deserializationStream;
	}

	@Override
	public void close() {
		deserializationStream.close();
	}

	@Override
	public <T> T readObject(ClassTag<T> classTag) {
		final T deserializedObject = deserializationStream.readObject(classTag);
		if (deserializedObject instanceof WrappedArray<?>) {
			@SuppressWarnings("unchecked")
			final WrappedArray<Object> wrappedArray = (WrappedArray<Object>) deserializedObject;
			final Iterator<Object> iterator = wrappedArray.iterator();
			while (iterator.hasNext()) {
				final Object object = iterator.next();
				SpringBuilder.autowire(object);
			}

		}
		return deserializedObject;
	}

}

package javax.util.function;

import java.util.Objects;

@FunctionalInterface
public interface QuadrupleConsumer<T, U, V, W> {
	void accept(T t, U u, V v, W w);
	
    default QuadrupleConsumer<T, U, V, W> andThen(QuadrupleConsumer<? super T, ? super U, ? super V, ? super W> after) {
        Objects.requireNonNull(after);

        return (t, u, v, w) -> {
            accept(t, u, v, w);
            after.accept(t, u, v, w);
        };
    }
}
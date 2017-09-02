package pt.pak3nuh.stream.util.function;

import pt.pak3nuh.stream.util.ExStreamRuntimeException;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ToJavaConverter {

    private ToJavaConverter() {
    }

    public static <T,R> Function<T,R> toJavaFunc(ExFunction<? super T,? extends R> func) throws ExStreamRuntimeException {
        return t -> {
            try {
                return func.apply(t);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }

    public static <T> Predicate<T> toJavaFunc(ExPredicate<? super T> func) throws ExStreamRuntimeException {
        return t -> {
            try {
                return func.test(t);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }

    public static <T> Consumer<T> toJavaFunc(ExConsumer<? super T> func) throws ExStreamRuntimeException {
        return t -> {
            try {
                func.consume(t);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }

    public static <T> BinaryOperator<T> toJavaFunc(ExBinaryOperator<T> func) throws ExStreamRuntimeException {
        return (t1, t2) -> {
            try {
                return func.apply(t1, t2);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }


    private static ExStreamRuntimeException getException(Exception cause) {
        return new ExStreamRuntimeException(cause);
    }

}

package pt.pak3nuh.util.exstream.function;

public interface ExFunction<T,R> {
    R apply(T t) throws Exception;
}

package pt.pak3nuh.util.exstream;

public class ExStreamRuntimeException extends RuntimeException {

    public ExStreamRuntimeException(Exception cause) {
        super(cause);
    }

    @Override
    public synchronized Exception getCause() {
        return (Exception) super.getCause();
    }
}

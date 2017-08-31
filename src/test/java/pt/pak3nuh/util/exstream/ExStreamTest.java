package pt.pak3nuh.util.exstream;

import org.junit.Test;

import java.util.stream.Stream;

public class ExStreamTest {

    @Test(expected = Exception.class)
    public void mapWithException() throws Exception {
        ExStreamBuilder.of(Stream.iterate(1, integer -> integer + 1))
                .exMap(i -> {
                    throw new Exception();
                })
                .exFindFirst();
    }

    @Test
    public void filterDoNotThrow() throws Exception {
        assert !ExStreamBuilder.of(Stream.of(1,2,3))
                .exFilter(i -> i%4 == 0)
                .exMap(i -> {
                    throw new Exception();
                }).exFindFirst().isPresent();
    }

    @Test
    public void skipDoNotThrow() throws Exception {
        assert ExStreamBuilder.of(Stream.of(1,2,3))
                .exSkip(1)
                .exMap(i -> {
                    if (i == 1) {
                        throw new Exception();
                    }
                    return i;
                })
                .exFindFirst().isPresent();
    }
}
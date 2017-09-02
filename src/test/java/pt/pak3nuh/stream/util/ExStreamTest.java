package pt.pak3nuh.stream.util;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

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

    @Test(expected = ExStreamRuntimeException.class)
    public void testToStream() throws Exception {
        ExStreamBuilder.of(Stream.of(1,2,3))
                .exMap(i -> {
                    if (i == 1) {
                        throw new Exception();
                    }
                    return i;
                })
                .toStream()
                .findFirst();
    }

    @Test(expected = ExStreamException.class)
    public void testParallel() throws Exception {
        int badNumber = 123_456;
        int maxSize = 100_000_000;
        int value = ExStreamBuilder
                .of(Stream.generate(() -> 1).parallel())
                .exLimit(maxSize)
                .exReduce((i1, i2) -> {
                    if (i1 == badNumber || i2 == badNumber) {
                        throw new Exception("Bad number");
                    }
                    return i1 + i2;
                }).get();
        assertEquals(value, maxSize);
    }

    @Test
    public void useJavaFunctions() throws Exception {
        ExStreamBuilder.of(Stream.of(1))
                .exMap(this::identity)
                .exFindFirst().get();
    }

    private <T> T identity(T t){return t;}
}
package com.orientechnologies.orient.core.storage.index.nkbtree.normalizers;

import com.ibm.icu.text.Collator;
import com.orientechnologies.common.serialization.types.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

import static com.orientechnologies.common.serialization.types.OLongSerializer.LONG_SIZE;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(iterations = 1, batchSize = 1)
@Warmup(iterations = 1, batchSize = 1)
@Fork(1)
public class KeyNormalizerVsSerializerBenchmark {
  KeyNormalizer keyNormalizer;
  ByteOrder byteOrder;

  public static void main(String[] args) throws RunnerException {
    final Options opt = new OptionsBuilder()
        .include("KeyNormalizerVsSerializerBenchmark.*")
        .addProfiler(StackProfiler.class, "detailLine=true;excludePackages=true;period=1")
        .jvmArgs("-server", "-XX:+UseConcMarkSweepGC", "-Xmx4G", "-Xms1G")
        //.result("target" + "/" + "results.csv")
        //.param("offHeapMessages", "true""
        //.resultFormat(ResultFormatType.CSV)
        .build();
    new Runner(opt).run();
  }

  @Setup(Level.Iteration)
  public void setup() {
    keyNormalizer = new KeyNormalizer();
    byteOrder = ByteOrder.nativeOrder();
  }

  /*@Benchmark
  public void booleanSerializer() throws Exception {
    final OBooleanSerializer serializer = new OBooleanSerializer();
    serializer.serialize(true, new byte[1], 0);
  }

  @Benchmark
  public void booleanNormalizer() throws Exception {
    final BooleanKeyNormalizer normalizer = new BooleanKeyNormalizer();
    normalizer.execute(true, byteOrder,0);
  }

  @Benchmark
  public void byteSerializer() throws Exception {
    final OByteSerializer serializer = new OByteSerializer();
    serializer.serialize((byte) 3, new byte[1], 0);
  }

  @Benchmark
  public void byteNormalizer() throws Exception {
    final ByteKeyNormalizer normalizer = new ByteKeyNormalizer();
    normalizer.execute((byte) 3, byteOrder,0);
  }

  @Benchmark
  public void integerSerializer() throws Exception {
    final OIntegerSerializer serializer = new OIntegerSerializer();
    serializer.serialize(5, new byte[4], 0);
  }

  @Benchmark
  public void integerNormalizer() throws Exception {
    final IntegerKeyNormalizer normalizer = new IntegerKeyNormalizer();
    normalizer.execute(5, byteOrder,0);
  }

  @Benchmark
  public void floatSerializer() throws Exception {
    final OFloatSerializer serializer = new OFloatSerializer();
    serializer.serialize(1.5f, new byte[4], 0);
  }

  @Benchmark
  public void floatNormalizer() throws Exception {
    final FloatKeyNormalizer normalizer = new FloatKeyNormalizer();
    normalizer.execute(1.5f, byteOrder,0);
  }

  @Benchmark
  public void doubleSerializer() throws Exception {
    final ODoubleSerializer serializer = new ODoubleSerializer();
    serializer.serialize(1.5d, new byte[8], 0);
  }

  @Benchmark
  public void doubleNormalizer() throws Exception {
    final DoubleKeyNormalizer normalizer = new DoubleKeyNormalizer();
    normalizer.execute(1.5d, byteOrder,0);
  }

  @Benchmark
  public void shortSerializer() throws Exception {
    final OShortSerializer serializer = new OShortSerializer();
    serializer.serialize((short) 3, new byte[2], 0);
  }

  @Benchmark
  public void shortNormalizer() throws Exception {
    final ShortKeyNormalizer normalizer = new ShortKeyNormalizer();
    normalizer.execute((short) 3, byteOrder,0);
  }

  @Benchmark
  public void longSerializer() throws Exception {
    final OLongSerializer serializer = new OLongSerializer();
    serializer.serialize(5L, new byte[LONG_SIZE], 0);
  }*/

  @Benchmark
  public void longNormalizer() throws Exception {
    final LongKeyNormalizer normalizer = new LongKeyNormalizer();
    normalizer.execute(5L, byteOrder, 0);
  }

  /*@Benchmark
  public void stringSerializer() throws Exception {
    final OStringSerializer serializer = new OStringSerializer();
    serializer.serialize("abcd", new byte[16], 0);
  }

  @Benchmark
  public void stringUtf8Serializer() throws Exception {
    final OUTF8Serializer serializer = new OUTF8Serializer();
    serializer.serialize("abcd", new byte[16], 0);
  }

  @Benchmark
  public void stringNormalizer() throws Exception {
    final StringKeyNormalizer normalizer = new StringKeyNormalizer();
    normalizer.execute("abcd", byteOrder, Collator.NO_DECOMPOSITION);
  }*/
}

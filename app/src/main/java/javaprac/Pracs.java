package javaprac;

import java.util.List;

import javaprac.annotations.*;
import javaprac.aop.*;
import javaprac.collections.*;
import javaprac.concurrency.*;
import javaprac.generics.*;
import javaprac.interfaces.*;
import javaprac.io.*;
import javaprac.networks.*;
import javaprac.reflection.*;
import javaprac.security.*;
import javaprac.streams.*;

import javaprac.gof.creational.abstractfactory.*;
import javaprac.gof.creational.builder.*;
import javaprac.gof.creational.factorymethods.*;
import javaprac.gof.creational.prototype.*;
import javaprac.gof.creational.singleton.*;
import javaprac.gof.structural.adapter.*;
import javaprac.gof.structural.bridge.*;
import javaprac.gof.structural.composite.*;
import javaprac.gof.structural.decorator.*;
import javaprac.gof.structural.facade.*;
import javaprac.gof.structural.flyweight.*;
import javaprac.gof.structural.proxy.*;


public class Pracs {

    public static List<Prac> getTestingPracs() {
        return STRUCTURAL_DESIGN_PATTERNS_PRACS;
    }

    public static List<Prac> STRUCTURAL_DESIGN_PATTERNS_PRACS = List.of(
            new StaticProxyPrac(),
            //new FlyweightPrac(),
            //new FacadePrac(),
            //new DecoratorPrac(),
            //new CompositePrac(),
            //new BridgePrac(),
            //new AdapterPrac(),
            new DefaultPrac()
    );

    public static List<Prac> CREATIONAL_DESIGN_PATTERNS_PRACS = List.of(
            //new SingletonPrac(),
            //new PrototypePrac(),
            //new FactoryMethodsPrac(),
            //new BuilderPrac(),
            //new AbstractFactoryPrac()
    );

    public static List<Prac> CONCURRENCY_PRACS = List.of(
            //new AQSPrac(),
            //new ForkJoinPrac(),
            //new ThreadPoolPrac(),
            //new CallableFuturePrac(),
            //new BlockingQueuePrac(),
            //new SynchronizationPrac()
    );

    public static List<Prac> AOP_PRACS = List.of(
            //new AopPrac()
    );

    public static List<Prac> ANNOTATIONS_PRACS = List.of(
            //new AnnotationPrac()
    );

    public static List<Prac> INTERFACES_PRACS = List.of(
            //new ProxyPrac(),
            //new AnonymousInnerClassPrac(),
            //new InnerClassPrac(),
            //new LambdaPrac(),
            //new CallBackPrac(),
            //new ComparatorPrac(),
            //new CloneablePrac()
    );

    public static List<Prac> REFLECTION_PRACS = List.of(
            //new MethodPrac(),
            //new CopyOfPrac(),
            //new ReflectionPrac()
    );

    public static List<Prac> NETWORKS_PRACS = List.of(
            //new URLConnectionPrac(),
            //new EchoServerPrac(),
            //new InetAddressPrac(),
            //new SocketPrac()
    );

    public static List<Prac> STREAMS_PRACS = List.of(
            //new ParallelStreamPrac(),
            //new CollectResultPrac(),
            //new OptionalPrac(),
            //new StreamCreationPrac(),
            //new CountLongWordsPrac()
    );

    public static List<Prac> SECURITY_PRACS = List.of(
            //new RSACryptPrac(),
            //new AESCryptPrac(),
            //new MessageDigestPrac(),
            //new PermissionPrac(),
            //new ClassLoaderPrac()
    );

    public static List<Prac> IO_PRACS = List.of(
            //new MemoryMapPrac(),
            //new ObjectStreamPrac(),
            //new RandomAccessFilePrac(),
            //new TextFilePrac()
    );

    public static List<Prac> COLLECTIONS_PRACS = List.of(
            //new LinkedHashesPrac(),
            //new PriorityQueuePrac(),
            //new HashSetPrac()
    );

    public static List<Prac> GENERICS_PRACS = List.of(
            //new WildCardPrac(),
            //new GenericClassPrac()
    );
}

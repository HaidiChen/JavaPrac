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

import javaprac.gof.behavioral.chainofresponsibility.*;
import javaprac.gof.behavioral.command.*;
import javaprac.gof.behavioral.interpreter.*;
import javaprac.gof.behavioral.iterator.*;
import javaprac.gof.behavioral.mediator.*;
import javaprac.gof.behavioral.memento.*;
import javaprac.gof.behavioral.observer.*;
import javaprac.gof.behavioral.state.*;
import javaprac.gof.behavioral.strategy.*;
import javaprac.gof.behavioral.templatemethod.*;
import javaprac.gof.behavioral.visitor.*;
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


public class Practices {

    public static List<Prac> getTestingPracs() {
        return CONCURRENCY_PRACS;
    }

    private static final List<Prac> BEHAVIORAL_DESIGN_PATTERNS_PRACS = List.of(
            new VisitorPrac(),
            //new TemplateMethodPrac(),
            //new StrategyPrac(),
            //new StatePrac(),
            //new ObserverPrac(),
            //new MementoPrac(),
            //new MediatorPrac(),
            //new IteratorPrac(),
            //new InterpreterPrac(),
            //new CommandPrac(),
            //new ChainOfResponsibilityPrac(),
            new DefaultPrac()
    );

    private static final List<Prac> STRUCTURAL_DESIGN_PATTERNS_PRACS = List.of(
            new StaticProxyPrac(),
            //new FlyweightPrac(),
            //new FacadePrac(),
            //new DecoratorPrac(),
            //new CompositePrac(),
            //new BridgePrac(),
            //new AdapterPrac(),
            new DefaultPrac()
    );

    private static final List<Prac> CREATIONAL_DESIGN_PATTERNS_PRACS = List.of(
            //new SingletonPrac(),
            //new PrototypePrac(),
            //new FactoryMethodsPrac(),
            //new BuilderPrac(),
            //new AbstractFactoryPrac()
    );

    private static final List<Prac> CONCURRENCY_PRACS = List.of(
            new JmmPrac(),
            //new AnotherThreadPoolPrac(),
            //new InterruptionPrac(),
            //new CountDownLatchPrac(),
            //new VisibilityPrac(),
            //new AQSPrac(),
            //new ForkJoinPrac(),
            //new ThreadPoolPrac(),
            //new CallableFuturePrac(),
            //new BlockingQueuePrac(),
            //new SynchronizationPrac()
            new DefaultPrac()
    );

    private static final List<Prac> AOP_PRACS = List.of(
            //new AopPrac()
    );

    private static final List<Prac> ANNOTATIONS_PRACS = List.of(
            //new AnnotationPrac()
    );

    private static final List<Prac> INTERFACES_PRACS = List.of(
            //new ProxyPrac(),
            //new AnonymousInnerClassPrac(),
            //new InnerClassPrac(),
            //new LambdaPrac(),
            //new CallBackPrac(),
            //new ComparatorPrac(),
            //new CloneablePrac()
    );

    private static final List<Prac> REFLECTION_PRACS = List.of(
            //new MethodPrac(),
            //new CopyOfPrac(),
            //new ReflectionPrac()
    );

    private static final List<Prac> NETWORKS_PRACS = List.of(
            //new URLConnectionPrac(),
            //new EchoServerPrac(),
            //new InetAddressPrac(),
            //new SocketPrac()
    );

    private static final List<Prac> STREAMS_PRACS = List.of(
            //new ParallelStreamPrac(),
            //new CollectResultPrac(),
            //new OptionalPrac(),
            //new StreamCreationPrac(),
            //new CountLongWordsPrac()
    );

    private static final List<Prac> SECURITY_PRACS = List.of(
            //new RSACryptPrac(),
            //new AESCryptPrac(),
            //new MessageDigestPrac(),
            //new PermissionPrac(),
            //new ClassLoaderPrac()
    );

    private static final List<Prac> IO_PRACS = List.of(
            //new MemoryMapPrac(),
            //new ObjectStreamPrac(),
            //new RandomAccessFilePrac(),
            //new TextFilePrac()
    );

    private static final List<Prac> COLLECTIONS_PRACS = List.of(
            //new LinkedHashesPrac(),
            //new PriorityQueuePrac(),
            //new HashSetPrac()
    );

    private static final List<Prac> GENERICS_PRACS = List.of(
            //new WildCardPrac(),
            //new GenericClassPrac()
    );
}

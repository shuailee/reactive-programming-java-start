package reactive.programming.java.start.rxjava2.creating;


import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

/**
 * SubjectCase
 * http://reactivex.io/documentation/subject.html
 *
 * @author mark
 * @date 2020/06/09
 */
public class SubjectCase {
    public static void main(String[] args) throws Exception {
        // 只接收订阅之后发射的数据
        System.out.println("---------------PublishSubject---------------");
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("publishSubject:pre1");
        publishSubject.subscribe(item -> System.out.println("1 " + item));
        publishSubject.onNext("publishSubject:after1");

        publishSubject.onNext("publishSubject:pre2");
        publishSubject.subscribe(item -> System.out.println("2 " + item));
        publishSubject.onNext("publishSubject:after2");
        publishSubject.onComplete();
        // onComplete 之后不会再接收任何发送的数据, 因为已经没有订阅者
        publishSubject.onNext("publishSubject:after3");

        // ReplaySubject 接收订阅前后所有发射的数据, 无论何时订阅, 无需调用 onComplete()
        System.out.println("---------------ReplaySubject---------------");
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("replaySubject:pre1");
        replaySubject.onNext("replaySubject:pre2");
        replaySubject.subscribe(item -> System.out.println("1 " + item));
        replaySubject.onNext("replaySubject:after1");
        replaySubject.onNext("replaySubject:after2");

        // AsyncSubject 只接收 onComplete 之前发射的最后一个数据, 无论何时订阅, 需要调用 onComplete()
        System.out.println("---------------AsyncSubject---------------");
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("asyncSubject:pre1");
        asyncSubject.onNext("asyncSubject:pre2");
        asyncSubject.subscribe(item -> System.out.println("1 " + item));
        asyncSubject.onNext("asyncSubject:after1");
        asyncSubject.onNext("asyncSubject:after2");
        asyncSubject.onComplete();

        // BehaviorSubject 先接收订阅之前发射的最后一个数据, 再接收后面发射的数据, 订阅之前没有数据发送, 则发送一个默认空数据, 无需调用 onComplete()
        System.out.println("---------------BehaviorSubject---------------");
        // AsyncSubject 接收 onComplete 之前的最后一个数据
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.onNext("behaviorSubject:pre1");
        behaviorSubject.onNext("behaviorSubject:pre2");
        behaviorSubject.subscribe(item -> System.out.println("1 " + item));
        behaviorSubject.onNext("behaviorSubject:after1");
        behaviorSubject.onNext("behaviorSubject:after2");

        TimeUnit.SECONDS.sleep(1);
    }
}

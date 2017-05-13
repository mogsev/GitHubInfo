package mogsev.com.simpleapp.rxjava;

import org.junit.Assert;
import org.junit.Test;

import io.reactivex.Observable;


/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class RxJavaUnitTest {
    String result = "";

    @Test
    public void returnAValue(){
        result = "";
        Observable<String> observer = Observable.just("Hello"); // provides datea
        observer.subscribe(s -> result = s); // Callable as subscriber
        Assert.assertTrue(result.equals("Hello"));
    }

}

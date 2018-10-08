package android.rezkyauliapratama.com.unittesttraining.Event;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Rezky Aulia Pratama on 8/10/18.
 */
abstract class ApiObservable<LISTENER_CLASS> {

    // thread-safe set of listeners
    private Set<LISTENER_CLASS> mListeners = Collections.newSetFromMap(new ConcurrentHashMap<LISTENER_CLASS, Boolean>(1));

    public Set<LISTENER_CLASS> getListeners(){
        return Collections.unmodifiableSet(mListeners);
    }


    public void registerListener(LISTENER_CLASS listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(LISTENER_CLASS listener) {
        mListeners.remove(listener);
    }

}

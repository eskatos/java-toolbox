package org.codeartisans.java.toolbox.async;

public interface AsyncCallbackWithE<T, E extends Throwable>
{

    void onSuccess(T value);

    void onError(String message, E cause);

}

package org.codeartisans.java.toolbox.async;

public interface AsyncCallback<T>
{

    void onSuccess(T value);

    void onError(String message, Throwable cause);

}

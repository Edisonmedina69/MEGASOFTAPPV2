package py.edison.megasoftappv2.interfaces;

public interface Callback<T> {
    void onSuccess(T result);
    void onError(String error);
}
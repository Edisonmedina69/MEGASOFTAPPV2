package py.edison.megasoftappv2.interfaces;

import py.edison.megasoftappv2.entidades.Flete;

public interface FleteCallback {
    void onSuccess(Flete flete);
    void onError(String errorMessage);

}

package com.tribal.hackathon.tribalhackathon17.Schemes;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;

public interface SchemeCallback {
    void onSuccess(Schemes body);
    void onFailure();
}

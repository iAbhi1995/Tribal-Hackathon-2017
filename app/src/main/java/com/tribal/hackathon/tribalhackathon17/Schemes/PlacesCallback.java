package com.tribal.hackathon.tribalhackathon17.Schemes;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;

/**
 * Created by Abhi on 10-Dec-17.
 */

public interface PlacesCallback {
    void onSuccess(Places body);

    void onFailure();
}

package com.tribal.hackathon.tribalhackathon17.Schemes;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Data;

/**
 * Created by Abhi on 09-Dec-17.
 */

public interface SchemeCallback {
    void onSuccess(Data body);

    void onFailure();
}

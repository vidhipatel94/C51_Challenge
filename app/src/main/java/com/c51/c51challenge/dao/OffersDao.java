package com.c51.c51challenge.dao;

import com.c51.c51challenge.apis.APIHelper;
import com.c51.c51challenge.apis.GetOffersResponse;

import io.reactivex.rxjava3.core.Observable;

public class OffersDao {
    public static Observable<GetOffersResponse> getOffers() {
        return APIHelper.getAPIs().getOffers();
    }
}

package com.c51.c51challenge.apis;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestAPIs {

    @Headers({
            "secret-key: $2b$10$I24vkicoDUL.DxMc.yI1euFZGdK1DbXGO/WbaNEx1.lnuM14pPjeu"
    })
    @GET("b/61b23f1a0ddbee6f8b1a516a")
    Observable<GetOffersResponse> getOffers();
}

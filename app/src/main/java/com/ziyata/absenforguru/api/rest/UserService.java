package com.ziyata.absenforguru.api.rest;

import com.ziyata.absenforguru.api.parameters.DoDaftarRequestAbsenPelajaran;
import com.ziyata.absenforguru.api.parameters.DoDaftarResponseAbsenPelajaran;
import com.ziyata.absenforguru.api.parameters.DoRequestLogin;
import com.ziyata.absenforguru.api.parameters.DoRequestRegister;
import com.ziyata.absenforguru.api.parameters.DoResponseLogin;
import com.ziyata.absenforguru.api.parameters.DoResponseRegister;
import com.ziyata.absenforguru.api.parameters.DoSiswaRequestJson;
import com.ziyata.absenforguru.api.parameters.DoSiswaResponseJson;
import com.ziyata.absenforguru.api.parameters.ListAbsenRequestJson;
import com.ziyata.absenforguru.api.parameters.ListAbsenResponseJson;
import com.ziyata.absenforguru.api.parameters.UtilityResponseJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("http://104.237.9.59/api/v1/siswa/absen/list/all")
    Call<ListAbsenResponseJson> allAbsen (@Body ListAbsenRequestJson request);

    @POST("http://104.237.9.59/api/v1/siswa/list/all")
    Call<DoSiswaResponseJson> listsiswa(@Body DoSiswaRequestJson param);

    @POST("http://104.237.9.59/api/v1/siswa/absen/list/pelajaran/all")
    Call<DoDaftarResponseAbsenPelajaran> listAbsenPelajaran(@Body DoDaftarRequestAbsenPelajaran param);

    @POST("http://104.237.9.59/api/v1/guru/login/")
    Call<DoResponseLogin> login(@Body DoRequestLogin parram);

    @POST("http://104.237.9.59/api/v1/guru/register/")
    Call<DoResponseRegister> register(@Body DoRequestRegister param);

    @POST("http://104.237.9.59/api/v1/guru/utility/")
    Call<UtilityResponseJson> utility();
}

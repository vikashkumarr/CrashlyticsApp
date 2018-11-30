package com.git.crashlytics.core.common;

import com.git.crashlytics.crashlyticsapp.model.CraslyticsIssues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by z018534 on 30-11-2018.
 */

public interface ApiInterface {

    /**
     * Get issues list from GitHub
     *
     * @return get data of issues
     */
    @GET("issues")
    Call<List<CraslyticsIssues>> getIssuesList();
}

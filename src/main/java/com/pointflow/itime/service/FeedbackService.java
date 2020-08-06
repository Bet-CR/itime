package com.pointflow.itime.service;

import com.pointflow.itime.domain.Feedback;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 19:27
 **/
public interface FeedbackService {

    int feedback(Feedback fd);
    Feedback getFeedback(Feedback fd);
    Feedback[] getFeedbacks(Feedback fd);
    Feedback[] getAllFeedbacks(Feedback fd);
}

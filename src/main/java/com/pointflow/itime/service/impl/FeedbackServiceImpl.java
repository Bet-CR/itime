package com.pointflow.itime.service.impl;

import com.pointflow.itime.domain.Feedback;
import com.pointflow.itime.mapper.FeedbackMapper;
import com.pointflow.itime.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 19:30
 **/
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public int feedback(Feedback fd) {
        return feedbackMapper.feedback(fd);
    }

    @Override
    public Feedback getFeedback(Feedback fd) {
        return feedbackMapper.getFeedback(fd);
    }

    @Override
    public Feedback[] getFeedbacks(Feedback fd) {
        return feedbackMapper.getFeedbacks(fd);
    }

    @Override
    public Feedback[] getAllFeedbacks(Feedback fd) {
        return feedbackMapper.getAllFeedbacks(fd);
    }
}

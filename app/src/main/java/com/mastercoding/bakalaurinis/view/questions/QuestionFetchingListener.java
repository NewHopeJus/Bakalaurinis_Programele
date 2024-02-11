package com.mastercoding.bakalaurinis.view.questions;

import com.mastercoding.bakalaurinis.model.Question;

public interface QuestionFetchingListener {
    void onQuestionAvailable(Question question);
}

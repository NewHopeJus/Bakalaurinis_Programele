package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.repository.QuestionRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

// Accesses the Question Repository to retrieve a question.
// A factory is needed, not just a view model, because parameters need to be passed as well.

public class QuestionViewModel extends ViewModel {
    private QuestionRepository questionRepository;
    private MutableLiveData<Question> questionLiveData;
    private LiveData<AnswerSubmitResponse> answerSubmitResponseLiveData;
    private String level;
    private String topic;
    private boolean isAnswered = false;

    public QuestionViewModel(String level, String topic, MineSecurityManager securityManager) {
        questionRepository = new QuestionRepository(securityManager);
        this.level = level;
        this.topic = topic;
    }

    public void getQuestion() {
        questionLiveData = questionRepository.getQuestion(level, topic);
    }

    public LiveData<Question> getQuestionLiveData() {
        return questionLiveData;
    }

    public LiveData<AnswerSubmitResponse> getAnswerSubmitResponseLiveData() {
        return answerSubmitResponseLiveData;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public void submitAnswer(AnswerSubmitRequest answerSubmitRequest) {
        answerSubmitResponseLiveData = questionRepository.getAnswerSubmitResponse(answerSubmitRequest);
    }
}

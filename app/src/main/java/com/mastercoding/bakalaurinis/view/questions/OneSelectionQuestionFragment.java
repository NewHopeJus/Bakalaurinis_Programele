package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentOneSelectionQuestionBinding;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Option;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.security.SecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OneSelectionQuestionFragment extends Fragment {

    private Question question;
    private final Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
    private final QuestionAPI questionService = retrofit.create(QuestionAPI.class);
    private FragmentOneSelectionQuestionBinding fragmentOneSelectionQuestionBinding;
    private QuestionViewModel questionViewModel;
    private String levelName;
    private String topicName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentOneSelectionQuestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one_selection_question, container, false);

        levelName = getActivity().getIntent().getStringExtra("levelName");
        topicName = getActivity().getIntent().getStringExtra("topicName");
        SecurityManager securityManager = new SecurityManager(requireContext());
        questionViewModel = new ViewModelProvider(getActivity(), new QuestionViewModelFactory(levelName, topicName, securityManager)).get(QuestionViewModel.class);

        return fragmentOneSelectionQuestionBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments(); //kad gauti is activity klausimo informacija


        TextView descriptionTextView = view.findViewById(R.id.textViewQuestionDescriptionCorrectAnswer);

        RadioButton radioButton1 = fragmentOneSelectionQuestionBinding.radioOption1;
        RadioButton radioButton2 = fragmentOneSelectionQuestionBinding.radioOption2;
        RadioButton radioButton3 = fragmentOneSelectionQuestionBinding.radioOption3;
        RadioButton radioButton4 = fragmentOneSelectionQuestionBinding.radioOption4;
        RadioGroup radioGroup = fragmentOneSelectionQuestionBinding.radioGroupOneSelQuestion;

        TextView coinsTextView = fragmentOneSelectionQuestionBinding.textViewCoinsOneSelQuestion;
        TextView experienceTextView = fragmentOneSelectionQuestionBinding.textViewExperienceOneSelQuestion;

        Button buttonSubmit = fragmentOneSelectionQuestionBinding.buttonSubmitOneSelQuestion;

        if (args != null) {
            question = args.getParcelable("questionObject");

            if (question != null) {
                List<Option> optionList = question.getOptions();
                descriptionTextView.setText(question.getDescription());

                radioButton1.setText(optionList.get(0).getText());
                radioButton1.setTag(optionList.get(0).getId());

                radioButton2.setText(optionList.get(1).getText());
                radioButton2.setTag(optionList.get(1).getId());


                radioButton3.setText(optionList.get(2).getText());
                radioButton3.setTag(optionList.get(2).getId());

                radioButton4.setText(optionList.get(3).getText());
                radioButton4.setTag(optionList.get(3).getId());


                String coins = question.getCoins().toString();
                coinsTextView.setText(coins);

                String experience = question.getExperience().toString();
                experienceTextView.setText(experience);
            }


        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(getContext(), "Pasirinkite vieną teisingą atsakymą.", Toast.LENGTH_SHORT).show();

                } else {

                    RadioButton selected = radioGroup.findViewById(selectedId);
                    AnswerSubmitRequest answerSubmitRequest = new AnswerSubmitRequest(question.getId(),
                            (String) selected.getText(), (Long) selected.getTag());

                    questionViewModel.submitAnswer(answerSubmitRequest);
                    questionViewModel.setAnswered(true);
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                    questionViewModel.getAnswerSubmitResponseLiveData().observe(requireActivity(), new Observer<AnswerSubmitResponse>() {
                        @Override
                        public void onChanged(AnswerSubmitResponse answerSubmitResponse) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container_question_fragment,
                                            FragmentLoadingService.loadAnswerFragment(answerSubmitResponse, args))
                                    .commit();
                        }
                    });

                }

            }

        });

        Button buttonSkip = fragmentOneSelectionQuestionBinding.buttonSkipOneSelQuestion;
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionViewModel.getQuestion();
            }
        });


    }
}
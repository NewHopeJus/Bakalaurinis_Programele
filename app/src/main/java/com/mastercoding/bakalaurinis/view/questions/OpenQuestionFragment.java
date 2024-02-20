package com.mastercoding.bakalaurinis.view.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentOneSelectionQuestionBinding;
import com.mastercoding.bakalaurinis.databinding.FragmentOpenQuestionBinding;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.security.SecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;


public class OpenQuestionFragment extends Fragment {

    private FragmentOpenQuestionBinding fragmentOpenQuestionBinding;
    private Question question;
    private String levelName;
    private String topicName;
    private QuestionViewModel questionViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentOpenQuestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_open_question, container, false);
        levelName = getActivity().getIntent().getStringExtra("levelName");
        topicName = getActivity().getIntent().getStringExtra("topicName");
        SecurityManager securityManager = new SecurityManager(requireContext());
        questionViewModel = new ViewModelProvider(getActivity(), new QuestionViewModelFactory(levelName, topicName, securityManager)).get(QuestionViewModel.class);
        return fragmentOpenQuestionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        TextView descriptionTextView = fragmentOpenQuestionBinding.textViewOpenQuestionDescription;

        if (args != null) {
            question = args.getParcelable("questionObject");

            if (question != null) {

                String description = question.getDescription();
                descriptionTextView.setText(description);
                if (description != null && description.length() > 50) {
                    descriptionTextView.setTextSize(16);
                }

            }
        }

        Button buttonSubmit = fragmentOpenQuestionBinding.buttonSubmitOpenQuestion;
        EditText editTextAnswer = fragmentOpenQuestionBinding.editTextOpenQuestion;
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = editTextAnswer.getText().toString();
                if (answer.isEmpty()) {
                    Toast.makeText(getContext(), "Įveskite atsakymą.", Toast.LENGTH_SHORT).show();

                } else {
                    AnswerSubmitRequest answerSubmitRequest = new AnswerSubmitRequest(question.getId(),
                            answer, 0l);

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
    }

}
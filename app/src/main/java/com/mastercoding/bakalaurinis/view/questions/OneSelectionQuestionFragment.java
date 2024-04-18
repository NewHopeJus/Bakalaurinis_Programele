package com.mastercoding.bakalaurinis.view.questions;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentOneSelectionQuestionBinding;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Option;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.retrofit.QuestionAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;


public class OneSelectionQuestionFragment extends Fragment {

    private Question question;
    private final Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
    private final QuestionAPI questionService = retrofit.create(QuestionAPI.class);
    private FragmentOneSelectionQuestionBinding fragmentOneSelectionQuestionBinding;
    private QuestionViewModel questionViewModel;
    private String levelName;
    private String topicName;

    private ProgressDialog progressDialog;
    private StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentOneSelectionQuestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one_selection_question, container, false);

        levelName = getActivity().getIntent().getStringExtra("levelName");
        topicName = getActivity().getIntent().getStringExtra("topicName");
        MineSecurityManager securityManager = new MineSecurityManager(requireContext());
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
                if (question.getQuestionTopic().equals("Trupmenos")) {
                    descriptionTextView.setText(Html.fromHtml(question.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    descriptionTextView.setText(question.getDescription());
                }

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
                            (String) selected.getText(), (Long) selected.getTag(), question.getQuestionLevel());

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


        String imageName = question.getImagePath()!=null? question.getImagePath():"1-lygis/atimtis/1L_1T_1K.png";
        storageReference = FirebaseStorage.getInstance().getReference("images/" + imageName + ".png");

        try {
            File localFile = File.createTempFile("tempfile", ".png");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            fragmentOneSelectionQuestionBinding.imageViewDisplayImage.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Nepavyko užkrauti paveiksliuko", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
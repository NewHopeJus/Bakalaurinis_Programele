package com.mastercoding.bakalaurinis.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question implements Parcelable {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("questionType")
    @Expose
    private String questionType;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("options")
    @Expose
    private List<Option> options;

    @SerializedName("questionLevel")
    @Expose
    private String questionLevel;

    @SerializedName("questionTopic")
    @Expose
    private String questionTopic;

    @SerializedName("experience")
    @Expose
    private Integer experience;

    @SerializedName("coins")
    @Expose
    private Integer coins;

    @SerializedName("hint")
    @Expose
    private String hint;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;


    protected Question(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        questionType = in.readString();
        description = in.readString();
        options = in.createTypedArrayList(Option.CREATOR);
        questionLevel = in.readString();
        questionTopic = in.readString();
        if (in.readByte() == 0) {
            experience = null;
        } else {
            experience = in.readInt();
        }
        if (in.readByte() == 0) {
            coins = null;
        } else {
            coins = in.readInt();
        }
        hint = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel;
    }

    public String getQuestionTopic() {
        return questionTopic;
    }

    public void setQuestionTopic(String questionTopic) {
        this.questionTopic = questionTopic;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(questionType);
        dest.writeString(description);
        dest.writeTypedList(options);
        dest.writeString(questionLevel);
        dest.writeString(questionTopic);
        if (experience == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(experience);
        }
        if (coins == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(coins);
        }
        dest.writeString(hint);
    }
}
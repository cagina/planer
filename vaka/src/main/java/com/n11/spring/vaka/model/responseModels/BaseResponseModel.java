package com.n11.spring.vaka.model.responseModels;

public class BaseResponseModel {
    private String result;
    private String allPossiblePossibilities;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAllPossiblePossibilities() {
        return allPossiblePossibilities;
    }

    public void setAllPossiblePossibilities(String allPossiblePossibilities) {
        this.allPossiblePossibilities = allPossiblePossibilities;
    }
}

package com.example.covid19;

class MianModel {

    Integer imagesymptom;
    String symptomname;

    public MianModel(Integer imagesymptom, String symptomname) {
        this.imagesymptom = imagesymptom;
        this.symptomname = symptomname;
    }

    public Integer getImagesymptom(){
        return imagesymptom;
    }

    public String getSymptomname() {
        return symptomname;
    }
}

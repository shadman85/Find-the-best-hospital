package com.example.lab_1;

   public class Hospital {
   private   String hospital_name;
   private String id;
   private String icu;
   private   String icuCost;
   private String surgeryCost;
   private String ecgCost;
   private String xrayCost;
   private String rating;
   private String userRated;
   private String totalRating;

       public Hospital() {

       }

       public Hospital(String hospital_name, String id, String icu, String icuCost, String surgeryCost, String ecgCost, String xrayCost,String rating,String userRated,String totalRating) {
           this.hospital_name = hospital_name;
           this.id = id;
           this.icu = icu;
           this.icuCost = icuCost;
           this.surgeryCost = surgeryCost;
           this.ecgCost = ecgCost;
           this.xrayCost = xrayCost;
           this.rating=rating;
           this.userRated=userRated;
           this.totalRating=totalRating;
       }

       public String getHospital_name() {
           return hospital_name;
       }

       public void setHospital_name(String hospital_name) {
           this.hospital_name = hospital_name;
       }

       public String getId() {
           return id;
       }

       public void setId(String id) {
           this.id = id;
       }

       public String getIcu() {
           return icu;
       }

       public void setIcu(String icu) {
           this.icu = icu;
       }

       public String getIcuCost() {
           return icuCost;
       }

       public void setIcuCost(String icuCost) {
           this.icuCost = icuCost;
       }

       public String getSurgeryCost() {
           return surgeryCost;
       }

       public void setSurgeryCost(String surgeryCost) {
           this.surgeryCost = surgeryCost;
       }

       public String getEcgCost() {
           return ecgCost;
       }

       public void setEcgCost(String ecgCost) {
           this.ecgCost = ecgCost;
       }

       public String getXrayCost() {
           return xrayCost;
       }

       public void setXrayCost(String xrayCost) {
           this.xrayCost = xrayCost;
       }

       public String getRating() {
           return rating;
       }

       public void setRating(String rating) {
           this.rating = rating;
       }

       public String getUserRated() {
           return userRated;
       }

       public void setUserRated(String userRated) {
           this.userRated = userRated;
       }

       public String getTotalRating() {
           return totalRating;
       }

       public void setTotalRating(String totalRating) {
           this.totalRating = totalRating;
       }




   }

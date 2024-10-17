package com.example.vjezba4;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Predmet implements Parcelable {

    private int godina;
    private String ime;
    private String predavac;

    // Empty constructor (required by Parcelable)
    public Predmet() {
    }

    public Predmet(int godina, String ime, String predavac) {
        this.godina = godina;
        this.ime = ime;
        this.predavac = predavac;
    }
    // Constructor used for creating an instance from the parcel
    private Predmet(Parcel in) {
        godina = in.readInt();
        ime = in.readString();
        predavac = in.readString();
    }

    // Getter and Setter methods...
    public int getGodina() {
        return godina;
    }

    // Setter for godina
    public void setGodina(int godina) {
        this.godina = godina;
    }

    // Getter for ime
    public String getIme() {
        return ime;
    }

    // Setter for ime
    public void setIme(String ime) {
        this.ime = ime;
    }

    // Getter for predavac
    public String getPredavac() {
        return predavac;
    }

    // Setter for predavac
    public void setPredavac(String predavac) {
        this.predavac = predavac;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(godina);
        dest.writeString(ime);
        dest.writeString(predavac);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predmet predmet = (Predmet) o;
        return godina == predmet.godina &&
                Objects.equals(ime, predmet.ime) &&
                Objects.equals(predavac, predmet.predavac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(godina, ime, predavac);
    }

    // Creator for Parcelable
    public static final Parcelable.Creator<Predmet> CREATOR = new Parcelable.Creator<Predmet>() {
        @Override
        public Predmet createFromParcel(Parcel in) {
            return new Predmet(in);
        }

        @Override
        public Predmet[] newArray(int size) {
            return new Predmet[size];
        }
    };
}

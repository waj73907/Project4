package uga.edu.cs.project4quiz;

public class Country {
    private int countryID;
    private String countryName;
    private String countryContinent;

    public Country(int countryID, String countryName, String countryContinent) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.countryContinent = countryContinent;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryContinent() {
        return countryContinent;
    }

    public void setCountryContinent(String countryContinent) {
        this.countryContinent = countryContinent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryID=" + countryID +
                ", countryName='" + countryName + '\'' +
                ", countryContinent='" + countryContinent + '\'' +
                '}';
    }
}

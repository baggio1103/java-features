package country;

public class Country {

    public String name;

    private int population;

    public Country() {
    }

    public Country(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public static String[] getDetails() {
        return new String[] { "package : country", "class : COUNTRY" };
    }

    @Override
    public String toString() {
        return "Country [name=" + name + ", population=" + population + "]";
    }

}

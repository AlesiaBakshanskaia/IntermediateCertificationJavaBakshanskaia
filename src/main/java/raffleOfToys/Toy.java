package raffleOfToys;

public class Toy {
    private int idToy;
    private String nameOfToy;
    private int numberOfPrizes;

    public Toy(int idToy, String nameOfToy, int numberOfPrizes) {
        this.idToy = idToy;
        this.nameOfToy = nameOfToy;
        this.numberOfPrizes = numberOfPrizes;
    }

    public String getNameOfToy() {
        return nameOfToy;
    }

    public void setNameOfToy(String nameOfToy) {
        this.nameOfToy = nameOfToy;
    }

    public int getNumberOfPrizes() {
        return numberOfPrizes;
    }

    public void setNumberOfPrizes(int numberOfPrizes) {
        this.numberOfPrizes = numberOfPrizes;
    }

    @Override
    public String toString() {
        return "Игрушка " + idToy +
                " " + nameOfToy +
                "количество  " + numberOfPrizes;
    }
    public void printToy(){
        System.out.println(this);
    }


}

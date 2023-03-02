package printer.functionality;

public class ApplePrinter implements IPrint {

    @Override
    public void print() {
        System.out.println("It an Apple Printer, you can print anything from Apple Products and access remotely");
    }

    @Override
    public String describe() {
        return "Apple Printer version: v1";
    }

}

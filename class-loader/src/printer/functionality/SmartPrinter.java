package printer.functionality;

public class SmartPrinter implements IPrint {

    @Override
    public void print() {
        System.out.println("It is a Smart Printer, you can print anything and access remotely");
    }

    @Override
    public String describe() {
        return "Smart printer version: 1.0";
    }

}

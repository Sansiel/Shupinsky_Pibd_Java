public class HangarAlreadyHaveException extends Exception {
    public HangarAlreadyHaveException(){
        super("В ангаре такой самолет уже есть!");
    }
}
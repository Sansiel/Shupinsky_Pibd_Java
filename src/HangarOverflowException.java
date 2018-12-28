public class HangarOverflowException extends Exception {
    public HangarOverflowException() {
        super(" нет свободных мест");
    }
}
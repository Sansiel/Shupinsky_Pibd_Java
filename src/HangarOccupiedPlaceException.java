public class HangarOccupiedPlaceException extends Exception {
    public HangarOccupiedPlaceException(int i) {
        super("Место " + i + " занято");
    }
}

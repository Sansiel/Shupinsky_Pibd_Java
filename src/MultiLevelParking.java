import java.util.ArrayList;
public class MultiLevelParking {
        ArrayList<Hangar<ITransport>> hangarStages;
        private final int countPlaces = 15;
        public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
        {
            hangarStages = new ArrayList<Hangar<ITransport>>();
            for (int i = 0; i < countStages; ++i)
            {
                hangarStages.add(new Hangar<ITransport>(countPlaces, pictureWidth, pictureHeight));
            }
        }
        public Hangar<ITransport> get(int index)
        {
            if (index > -1 && index < hangarStages.size()) {
                return hangarStages.get(index);
            }
            return null;
        }
    }